package machine

import scala.collection.immutable
trait TraitResultat {

  // Geteur pour savoir si il s'agit d'une recherche initial ou si il faut faire un choix parmit plusieurs réponses
  def isRechercheInitial: Boolean

  // Permet de reinitialiser les variables
  def reinitResultat: Unit

  /** Nous dit si pour une reqête, représentée par une liste de Reponse, il y a
    * plusieurs réponses possible
    *
    * @param listeReponse
    * @return
    */
  def aPlusieursReponses(listeReponse: List[Reponse]): Boolean

  /** Réponse dans le cas ou il y a plusieurs réponses
    *
    * @param listeReponseMultiple
    * @param langue
    * @return
    */
  def getReponseSiAPlusieursReponses(
      listeReponseMultiple: List[Reponse],
      langue: Langue
  ): List[String]

  /** Réponse dans le cas ou l'utilsateur à choisi une reponse parmit plusieurs
    * lorsque la réponse précedante est a choix multiple (= donne plusieurs
    * réponses)
    *
    * @param phrase
    * @return
    */
  def choisirUnParmitPlusieur(phrase: String): List[String]

  /** Convertie la liste de Reponse en liste de String
    *
    * @param listeReponse
    * @return
    *   list de string
    */
  def reponseToString(listeReponse: List[Reponse], langue: Langue): List[String]
}

object Resultat extends TraitResultat {

  private var rechercheInitial: Boolean = true

  private var mapNombreToString: Map[Int, String] = Map()

  def isRechercheInitial: Boolean = rechercheInitial

  def reinitResultat: Unit = {
    rechercheInitial = true
    mapNombreToString = Map()
  }

  /** Récupérer seulement les éléments de type Contenu
    *
    * @param listeReponse
    * @return
    */
  private def getListContenu(listeReponse: List[Reponse]): List[Contenu] =
    listeReponse.collect { case c: Contenu => c }

  def aPlusieursReponses(listeReponse: List[Reponse]): Boolean =
    getListContenu(listeReponse).size > 1

  def getReponseSiAPlusieursReponses(
      listeReponseMultiple: List[Reponse],
      langue: Langue
  ): List[String] = {
    rechercheInitial = false
    var listOrdonée: List[Contenu] = ordeonnerListContenu(
      getListContenu(listeReponseMultiple)
    )
    mapNombreToString = aux_reponseToString(listOrdonée, langue).zipWithIndex
      .map(_.swap)
      .map(c => (c._1 + 1, c._2))
      .toMap
    listOrdonée
    val bonjour: String =
      BaseDeDonnees.getMapBonjours.get(langue).getOrElse("Bonjour")
    val nombreReponsesPossibles: String =
      BaseDeDonnees.getMapReponsesPossibles
        .get(langue)
        .getOrElse(
          "J’ai XXX réponses possibles :"
            .replace("XXX", listOrdonée.size.toString)
        )
        .replace("XXX", listOrdonée.size.toString)
    val choix: String =
      BaseDeDonnees.getMapChoix
        .get(langue)
        .getOrElse("Quel est votre choix?")
    val liste: List[String] = contenuToString(listOrdonée, 1)
    if (listeReponseMultiple.size.equals(listOrdonée.size))
      List(
        nombreReponsesPossibles
      ) ++ liste ++ List(choix)
    else
      List(
        bonjour,
        nombreReponsesPossibles
      ) ++ liste ++ List(choix)
  }

  /** Récupère tous les nombre d'une phrase
    *
    * @param phrase
    * @return
    */
  private def getNombre(phrase: String): List[Int] = {
    "\\d+".r.findAllIn(phrase).toList.map(_.toInt)
  }

  def choisirUnParmitPlusieur(phrase: String): List[String] = {
    rechercheInitial = true
    val langue: Langue = GererLangue.getLanguePrecedante
    val erreur: String =
      BaseDeDonnees.getMapErreurs
        .get(langue)
        .getOrElse("Je ne comprends pas votre demande")
    val listInt: List[Int] =
      getNombre(phrase)
    if (listInt.isEmpty) {
      List(erreur)
    } else {
      List(mapNombreToString.get(listInt.head).getOrElse(erreur))
    }
  }

  /** Ordonner la liste de contenu par sont attribut nom de manière
    * lexicographique
    *
    * @param listeContenu
    * @return
    */
  private def ordeonnerListContenu(
      listeContenu: List[Contenu]
  ): List[Contenu] = listeContenu.sortBy(_.nom)

  /** Permet d'afficher le réultat dans la cas d'une réponse multiple
    *
    * @param listeContenu
    * @param indice
    * @return
    */
  private def contenuToString(
      listeContenu: List[Contenu],
      indice: Int
  ): List[String] = listeContenu match {
    case Contenu(nom, address) :: next =>
      indice.toString + ") " + nom :: contenuToString(next, indice + 1)
    case Nil => Nil
  }

  def reponseToString(
      listeReponse: List[Reponse],
      langue: Langue
  ): List[String] = {
    val erreur: String =
      BaseDeDonnees.getMapErreurs
        .get(langue)
        .getOrElse("Je ne comprends pas votre demande")
    if (listeReponse.isEmpty) erreur :: Nil
    else if (aPlusieursReponses(listeReponse))
      getReponseSiAPlusieursReponses(listeReponse, langue)
    else aux_reponseToString(listeReponse, langue)
  }

  /** Convertie la liste de Reponse en liste de String
    *
    * @param listeReponse
    * @param langue
    * @return
    */
  private def aux_reponseToString(
      listeReponse: List[Reponse],
      langue: Langue
  ): List[String] = {
    val erreur: String =
      BaseDeDonnees.getMapErreurs
        .get(langue)
        .getOrElse("Je ne comprends pas votre demande")
    val bonjour: String =
      BaseDeDonnees.getMapBonjours.get(langue).getOrElse("Bonjour")
    val res = listeReponse match {
      case Bonjour :: next => bonjour :: aux_reponseToString(next, langue)
      case Erreur :: next  => erreur :: aux_reponseToString(next, langue)
      case Contenu(nom, adresse) :: next =>
        getReponseHumaine(nom, adresse, langue) :: aux_reponseToString(
          next,
          langue
        )
      case Nil => Nil
    }
    res
  }

  /** A partir du nom, de l'adresse et de la langue on obtient une réponse
    * humaine en recompérer le résultat attendu pour la langue donnée, en
    * remplacant XXX par le nom et en mettant enssuite l'adresse
    * @param nom
    * @param adresse
    * @param langue
    * @return
    */
  private def getReponseHumaine(
      nom: String,
      adresse: String,
      langue: Langue
  ): String = {
    val intro: String = BaseDeDonnees.getMapResultats.get(langue).getOrElse("")
    intro.replace("XXX", nom) + " : " + adresse
  }
}
