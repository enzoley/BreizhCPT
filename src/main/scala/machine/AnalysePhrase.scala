package machine

import scala.collection.immutable
import webf7.AnalyseUrl

trait TraitAnalysePhrase {

  /** Obtenir la list des reponses à l'imput phrase
    *
    * @param mots
    * @return
    *   une liste de reponse
    */
  def analyser(phrase: String): List[Reponse]
}

object AnalysePhrase extends TraitAnalysePhrase {

  def analyser(phrase: String): List[Reponse] = {
    val phraseToMots: List[String] = Parseur.spliter(phrase)
    val motsCorriger: List[String] = Tolerance.corriger(phraseToMots)
    val motsUniformisesBonjour: List[String] = uniformiserBonjour(motsCorriger)
    val motsUniformises: List[String] = uniformiser(motsUniformisesBonjour)
    if (getMotsApresRestau(motsUniformises) != Nil) {
      trierOccurance(
        netoyer(
          AnalyseUrl.getListContenu(getMotsApresRestau(motsUniformises))
        )
      )
    } else trierOccurance(netoyer(analyser(motsUniformises)))
  }

  /** List de string des elements après restaurant sinon list vide
    *
    * @param mots
    * @return
    *   List[String]
    */
  private def getMotsApresRestau(mots: List[String]): List[String] = {
    mots match {
      case Nil => Nil
      case ("restaurant" | "ristorante" | "pizzeria" | "creperie" |
          "restaurante" | "pizzéria") :: tail =>
        tail
      case head :: tail => getMotsApresRestau(tail)
    }
  }

  /** Tous les bonjours ( "bonjour", "salut", "hi", "hello", "hola"...)
    * devienent "bonjour"
    *
    * @param l
    * @return
    *   l en uniformisant les Bonjours
    */
  private def uniformiserBonjour(l: List[String]): List[String] = l match {
    case head :: next =>
      if (BaseDeDonnees.getListBonjours.contains(head))
        "bonjour" :: uniformiserBonjour(next)
      else head :: uniformiserBonjour(next)
    case Nil => Nil
  }

  /** Remplacer les synonymes
    *
    * @param list
    */
  private def uniformiser(l: List[String]): List[String] = {
    var list: List[String] = l
    val map: Map[List[String], List[String]] = BaseDeDonnees.getMapSynonymes
    var i: Int = 0
    for ((sousListe, listRemplacement) <- map) {
      val chaine: String = list.mkString(" ")
      val sousChaineRecherche: String = sousListe.mkString(" ")
      val sousChaineRemplacement: String = listRemplacement.mkString(" ")
      list = Parseur.spliter(
        chaine.replace(sousChaineRecherche, sousChaineRemplacement)
      )
    }
    list
  }

  /** Pour chaque mots rechercher la valeur de la cle qui correspond au mot. cf
    * RechercherMotCle
    *
    * @param mots
    * @return
    *   une liste de reponse
    */
  private def analyser(mots: List[String]): List[Reponse] = mots match {
    case head :: tail => RechercheMotCle.reponse(head) ++ analyser(tail)
    case Nil          => Nil
  }

  /** Netoye la liste de Reponse
    *
    * @param listeReponse
    * @return
    */
  private def netoyer(listeReponse: List[Reponse]): List[Reponse] = {
    supprimeDoublon(listeReponse) match {
      case Bonjour :: next => Bonjour :: netoyer(next)
      case Erreur :: Contenu(nom, adresse) :: next =>
        Contenu(nom, adresse) :: netoyer(next)
      case Contenu(nom, adresse) :: Erreur :: next =>
        Contenu(nom, adresse) :: netoyer(next)
      case Contenu(nom, adresse) :: next =>
        Contenu(nom, adresse) :: netoyer(next)
      case Erreur :: next => Erreur :: netoyer(next)
      case Nil            => Nil
    }
  }

  /** Suprime les doubons Bonjour et Erreur
    *
    * @param listeReponse
    * @return
    *   listeReponse sans doubons Bonjour et Erreur
    */
  private def supprimeDoublon(
      listeReponse: List[Reponse]
  ): List[Reponse] = {
    listeReponse match {
      case Bonjour :: Bonjour :: next => Bonjour :: supprimeDoublon(next)
      case Bonjour :: next            => Bonjour :: supprimeDoublon(next)
      case Erreur :: Erreur :: next   => Erreur :: supprimeDoublon(next)
      case Erreur :: next             => Erreur :: supprimeDoublon(next)
      case Contenu(nom, adresse) :: next =>
        Contenu(nom, adresse) :: supprimeDoublon(next)
      case Nil => Nil
    }
  }

  /** Permet de ne garder que les elements Reponse utile avec notament un
    * systeme de scorage
    *
    * @param listReponse:
    * @return
    *   listReponse filtre
    */
  private def trierOccurance(listReponse: List[Reponse]): List[Reponse] = {
    if (listReponse.isEmpty) Nil
    else {
      val listSansBonjour: List[Reponse] = listReponse.filter(_ != Bonjour)
      var list: List[Reponse] = List()
      if (!listSansBonjour.isEmpty) {
        val grouper =
          listSansBonjour
            .groupBy(identity)
            .mapValues(_.size)
        val maxOccurrence = grouper.values.max
        list = filtrer(grouper.toMap, maxOccurrence)
      }
      if (listReponse.size > listSansBonjour.size) {
        Bonjour :: list
      } else list
    }
  }

  /** Obtenir la liste des reponses qui sont de type Erreur ou qui coresponde au
    * nombre d'occurence souhaité
    *
    * @param map
    * @param maxOccurrence
    * @return
    *   List[Reponse] de type Erreur ou qui coresponde au nombre d'occurence
    *   souhaité
    */
  private def filtrer(
      map: Map[Reponse, Int],
      maxOccurrence: Int
  ): List[Reponse] = {
    var list: List[Reponse] = List()
    for ((reponse, nbOcc) <- map) {
      if (reponse == Erreur || nbOcc == maxOccurrence) list = list :+ reponse
    }
    list
  }
}
