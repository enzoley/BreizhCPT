package machine

import scala.xml.dtd.impl.Base

trait TraitGererLangue {

  // Permet de reinitialiser les variables
  def reinitLangue(): Unit

  // Geteur pour savair quelle était la langue utilisée lors de la dernière phrase
  def getLanguePrecedante(): Langue

  // Geteur pour savair si la langue est confirmée
  def getConfirme(): Boolean

  /** Obtient la langue de la phrase
    *
    * @param phrase
    * @return
    *   Langue
    */
  def getLangueCourante(phrase: String): Langue

  /** Cela permet de faire un changement de langue cyclique
    *
    * @param langue
    * @return
    */
  def getLangueSuivante(langue: Langue): Langue

  /** Procédure pour changer de langue
    *
    * @param langue
    * @return
    */
  def demandeConfirmationChangerLangue(langue: Langue): List[String]

  /** Procédure pour confirmer le changement de langue
    *
    * @param phrase
    * @return
    */
  def peutConfirmer(phrase: String): List[String]
}

object GererLangue extends TraitGererLangue {
  private var languePrecedante: Langue = Francais
  private var confirme: Boolean = true
  def reinitLangue(): Unit = {
    languePrecedante = Francais
    confirme = true
  }
  def getLanguePrecedante(): Langue = languePrecedante
  def getConfirme(): Boolean = confirme
  def getLangueCourante(phrase: String): Langue = {
    if (
      phraseContientUndesMots(
        phrase,
        BaseDeDonnees.getListMotsFrancais
      )
    ) Francais
    else if (
      phraseContientUndesMots(
        phrase,
        BaseDeDonnees.getListMotsAnglais
      )
    ) Anglais
    else if (
      phraseContientUndesMots(
        phrase,
        BaseDeDonnees.getListMotsEspagnol
      )
    ) Espagnol
    else if (
      phraseContientUndesMots(
        phrase,
        BaseDeDonnees.getListMotsAllemand
      )
    ) Allemand
    else if (
      phraseContientUndesMots(
        phrase,
        BaseDeDonnees.getListMotsItalien
      )
    ) Italien
    else languePrecedante
  }

  private def corrigerRequete(phrase: List[String]): List[String] =
    Tolerance.corriger(phrase)

  private def phraseContientUndesMots(
      phrase: String,
      listMots: List[String]
  ): Boolean = aux_phraseContientUndesMots(
    corrigerRequete(Parseur.spliter(phrase)),
    listMots
  )

  private def aux_phraseContientUndesMots(
      listMotsPhraseParser: List[String],
      listMots: List[String]
  ): Boolean = listMotsPhraseParser match {
    case head :: next =>
      listMots.exists(_.equals(head)) || aux_phraseContientUndesMots(
        next,
        listMots
      )
    case Nil => false
  }

  def getLangueSuivante(langue: Langue): Langue = langue match {
    case Francais => Anglais
    case Anglais  => Espagnol
    case Espagnol => Allemand
    case Allemand => Italien
    case Italien  => Francais
  }

  def demandeConfirmationChangerLangue(langue: Langue): List[String] = {
    confirme = false
    languePrecedante = langue
    languePrecedante match {
      case Francais => List("Parlez-vous français?")
      case Anglais  => List("Do you speak english?")
      case Espagnol => List("Hablas español?")
      case Allemand => List("Sprechen Sie Deutsch?")
      case Italien  => List("Parli italiano?")
    }
  }

  def peutConfirmer(phrase: String): List[String] = {
    confirme = true
    languePrecedante match {
      case Francais => {
        if ((phrase.toLowerCase()).trim.equals("oui")) {
          List("D'accord, quelle est votre demande?")
        } else
          demandeConfirmationChangerLangue(
            getLangueSuivante(getLangueCourante(phrase))
          )
      }
      case Anglais => {
        if ((phrase.toLowerCase()).trim.equals("yes"))
          List("OK, what is your query?")
        else
          demandeConfirmationChangerLangue(
            getLangueSuivante(getLangueCourante(phrase))
          )
      }
      case Espagnol => {
        if ((phrase.toLowerCase()).trim.equals("si"))
          List("Está bien, cuál es tu petición?")
        else
          demandeConfirmationChangerLangue(
            getLangueSuivante(getLangueCourante(phrase))
          )

      }
      case Allemand => {
        if ((phrase.toLowerCase()).trim.equals("ja")) {
          List("Okay, was ist Ihr Wunsch?")
        } else {
          demandeConfirmationChangerLangue(
            getLangueSuivante(getLangueCourante(phrase))
          )
        }
      }
      case Italien => {
        if ((phrase.toLowerCase()).trim.equals("si"))
          List("Va bene, qual è la tua richiesta?")
        else
          demandeConfirmationChangerLangue(
            getLangueSuivante(getLangueCourante(phrase))
          )

      }
    }
  }
}
