package machine

import scala.collection.immutable

trait TraitRechercheMotCle {

  /** Pour chaque mot renvoi la valeur de la cle qui correspond le mieux au mot.
    * Attention que ce passe-il s'il y a plusieurs cles = mot ?
    *
    * @param mot
    * @return
    */
  def reponse(mot: String): List[Reponse]
}

object RechercheMotCle extends TraitRechercheMotCle {
  def reponse(mot: String): List[Reponse] =
    getReponses(mot, BaseDeDonnees.getMap)

  /** Converti une liste de String en liste de Reponse
    *
    * @param listvaleurs
    * @return
    */
  private def getReponses(
      mot: String,
      baseDeDonnees: Map[String, List[(String, String)]]
  ): List[Reponse] = {
    mot match {
      case "bonjour" => Bonjour :: Nil
      case mot => {
        val listNomAdresse: List[(String, String)] =
          baseDeDonnees.get(mot).getOrElse(List())
        if (listNomAdresse.isEmpty) List(Erreur)
        else listNomAdresse.map { case (nom, adresse) => Contenu(nom, adresse) }
      }
    }
  }
}
