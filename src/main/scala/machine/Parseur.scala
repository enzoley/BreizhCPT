package machine

import java.io.FileInputStream
import opennlp.tools.postag.POSModel
import opennlp.tools.postag.POSTaggerME

trait TraitParseur {

  /** Une fonction qui divise une phrase en une list d'élement de type String
    *
    * @param phrase
    * @return
    *   une list de String corespondant à une phrase
    */
  def spliter(phrase: String): List[String]
}

object Parseur extends TraitParseur {

  private val determinants: List[String] = List(
    "le",
    "la",
    "les",
    "l",
    "du",
    "des",
    "un",
    "une",
    "des",
    "ce",
    "cet",
    "cette",
    "ces",
    "mon",
    "ma",
    "mes",
    "ton",
    "ta",
    "tes",
    "son",
    "sa",
    "ses",
    "notre",
    "nos",
    "votre",
    "vos",
    "leur",
    "leurs"
  )

  private val pronoms: List[String] = List(
    "je",
    "tu",
    "il",
    "elle",
    "nous",
    "vous",
    "ils",
    "elles",
    "on",
    "me",
    "te",
    "se",
    "nous",
    "vous",
    "le",
    "la",
    "les",
    "lui",
    "leur",
    "y",
    "en",
    "moi",
    "toi",
    "lui",
    "elle",
    "nous",
    "vous",
    "eux",
    "elles",
    "soi"
  )

  private var conjonctions: List[String] = List(
    "mais",
    "et",
    "ou",
    "donc",
    "or",
    "ni",
    "car",
    "que",
    "quoique",
    "bien",
    "malgré",
    "puisque",
    "quand",
    "pendant",
    "tandis",
    "alors",
    "après",
    "avant",
    "jusqu",
    "depuis",
    "parce",
    "afin",
    "pour",
    "quoiqu",
    "somme",
    "néanmoins",
    "toutefois",
    "cependant",
    "ainsi",
    "donc",
    "alors",
    "bref",
    "finalement"
  )

  private val auxiliaires = List(
    "être",
    "es",
    "est",
    "sommes",
    "êtes",
    "sont",
    "ai",
    "as",
    "a",
    "avons",
    "avez",
    "ont"
  )

  private val listSupprimer: List[String] =
    determinants ++ pronoms ++ conjonctions ++ auxiliaires

  private val ponctuations: List[String] = List(
    ".",
    ",",
    ";",
    ":",
    "?",
    "!",
    "-",
    "—",
    "(",
    ")",
    "[",
    "]",
    "{",
    "}",
    "\"",
    "’",
    "'",
    "«",
    "»",
    "(",
    ")",
    "/",
    "\\",
    "@",
    "#",
    "$",
    "%",
    "&",
    "*",
    "+",
    "<",
    ">",
    "=",
    "|",
    "~",
    "^",
    "\"",
    "..."
  )

  def spliter(phrase: String): List[String] = {
    phrase.length() match {
      case 0 => List()
      case _ => {
        // Remplacer toutes les occurrences de chiffres (\\d) par une chaîne vide
        // val phraseSansChiffre: String = phrase.replaceAll("\\d", "")

        // Remplacer toutes les occurrences de ponctuations par un espace
        val regex = "[${ponctuations.mkString}]"
        val phraseSansPonctuation: String =
          ponctuations.foldLeft(phrase) { (res, p) =>
            res.replace(p, " ")
          }

        // Remplacer plusieurs espaces (\\s+) par un seul
        val phraseSansNEspace: String =
          phraseSansPonctuation.replaceAll("\\s+", " ")

        // Mettre toute la phrase en minuscules
        val phraseMinuscules: String = phraseSansNEspace.toLowerCase()

        // Transformer en une liste
        phraseMinuscules
          .split(" ")
          .toList
          .filter(s => s.length > 2 || s == "hi" || s == "wo")
          .filterNot(listSupprimer.contains)
      }
    }
  }
}
