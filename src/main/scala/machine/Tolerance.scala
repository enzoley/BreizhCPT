package machine

trait TraitTolerance {

  /** Corrige les mots de la liste avec une marge d'erreur relative si ce n'est
    * pas possible, on ne le change pas.
    *
    * @param liste
    *   une liste de mot
    * @return
    *   une liste de mot corrigé
    */
  def corriger(liste: List[String]): List[String]

  /** Corrige le mot avec une marge d'erreur sinon ""
    *
    * @param mot
    *   le mot à corriger
    * @return
    *   le mot corrigé
    */
  def corrigerMot(mot: String): String
}

object Tolerance extends TraitTolerance {

  val dicKeys =
    BaseDeDonnees.getListMotsImportants // Une liste des clés de la map de la base de donnée des mots

  /** Renvoie le charactère sans accent et s'il n'en a pas, le renvoie tel quel
    * .
    *
    * @param lettre
    *   la lettre dont on enlève l'accent si possible.
    * @return
    *   la lettre sans accent.
    */
  private def accents(lettre: Char): Char = {
    lettre match {
      case 'é' | 'è' | 'ê' | 'ë' => 'e'
      case 'á' | 'à' | 'â' | 'ä' => 'a'
      case 'ú' | 'ù' | 'û' | 'ü' => 'u'
      case 'ó' | 'ò' | 'ô' | 'ö' => 'o'
      case 'í' | 'ì' | 'î' | 'ï' => 'i'
      case 'ç'                   => 'c'
      case 'ñ'                   => 'n'
      case _                     => lettre
    }
  }

  /** Renvoie la distance de Levenshtein entre deux mots.
    *
    * @param s1
    *   un mot
    * @param s2
    *   un mot
    * @return
    *   la distance de Levenshtein
    */
  private def levenshteinDistance(s1: String, s2: String): Int = {
    val m = s1.length
    val n = s2.length
    val d = Array.ofDim[Int](m + 1, n + 1)

    for (i <- 0 to m) {
      d(i)(0) = i
    }

    for (j <- 0 to n) {
      d(0)(j) = j
    }

    for (j <- 1 to n; i <- 1 to m) {
      val substitutionCost =
        if (accents(s1(i - 1)) == accents(s2(j - 1))) 0 else 1
      d(i)(j) = List(
        d(i - 1)(j) + 1, // deletion
        d(i)(j - 1) + 1, // insertion
        d(i - 1)(j - 1) + substitutionCost // substitution or match
      ).min
    }

    d(m)(n)
  }

  /** Renvoie une liste de mot corrigé.
    *
    * @param liste
    *   la liste de mot à corriger.
    * @return
    *   la liste corrigée.
    */
  def corriger(liste: List[String]): List[String] = {
    liste.map(corrigerMot(_))
  }

  /** Corrige le mot.
    *
    * @param mot
    *   le mot à corriger si possible
    * @return
    *   renvoie le mot corrigé si possible
    */
  def corrigerMot(mot: String): String = {
    corrigerMotRec(mot, dicKeys)
  }

  /** Renvoie un mot corrigé si possible. La correction se base sur un
    * dictionnaire.
    *
    * @param mot
    *   le mot à corriger.
    * @param dico
    *   la liste de string de mot bien orthographié.
    * @return
    *   le mot corrigé si possible.
    */
  private def corrigerMotRec(mot: String, dico: List[String]): String = {
    if (dico.contains(mot)) mot
    else
      dico match {
        case Nil => mot
        case cle :: reste => {
          if ((cle.size - mot.size).abs <= 1.00)
            levenshteinDistance(mot, cle) match {
              case 0 => cle
              case 1 => cle
              case _ => corrigerMotRec(mot, reste)
            }
          else corrigerMotRec(mot, reste)
        }
      }
  }
}
