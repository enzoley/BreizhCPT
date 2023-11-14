package machine

import org.junit.Test
import org.junit.Assert._

class TestResultat {

  // initialisation des objets sous test
  val r = Resultat
  val erreur: String = "Je ne comprends pas votre demande"

  // tests
  @Test
  def test_reponseToString_1: Unit = {
    val reponses: List[Reponse] = List()
    assertEquals(
      List(erreur),
      r.reponseToString(reponses, Francais)
    )
  }

  // @Test
  // def test_reponseToString_2: Unit = {
  //   val mot1: String = "aaaa"
  //   val mots: List[String] = List(mot1)
  //   val reponses: List[Reponse] =
  //     List(Texte(mot1))
  //   assertEquals(
  //     mots,
  //     r.reponseToString(reponses, Francais)
  //   )
  // }

  // @Test
  // def test_reponseToString_3: Unit = {
  //   val reponses: List[Reponse] = List(Erreur)
  //   assertEquals(
  //     List(erreur),
  //     r.reponseToString(reponses, Francais)
  //   )
  // }

  // @Test
  // def test_reponseToString_4: Unit = {
  //   val mot1: String = "aaaa"
  //   val mot2: String = "bbbb"
  //   val mot3: String = "cccc"
  //   val mot4: String = "dddd"
  //   val mots: List[String] = List(mot1, mot2, mot3, mot4)
  //   val reponses: List[Reponse] =
  //     List(Texte(mot1), Texte(mot2), Texte(mot3), Texte(mot4))
  //   assertEquals(
  //     mots,
  //     r.reponseToString(reponses, Francais)
  //   )
  // }

  // @Test
  // def test_reponseToString_5: Unit = {
  //   val mot1: String = "aaaa"
  //   val mot2: String = "bbbb"
  //   val mot3: String = "cccc"
  //   val mot4: String = "dddd"
  //   val mots: List[String] = List(erreur, mot1, mot2, mot3, mot4)
  //   val reponses: List[Reponse] =
  //     List(Erreur, Texte(mot1), Texte(mot2), Texte(mot3), Texte(mot4))
  //   assertEquals(
  //     mots,
  //     r.reponseToString(reponses, Francais)
  //   )
  // }

  // @Test
  // def test_reponseToString_6: Unit = {
  //   val mot1: String = "aaaa"
  //   val mot2: String = "bbbb"
  //   val mot3: String = "cccc"
  //   val mot4: String = "dddd"
  //   val mots: List[String] = List(mot1, mot2, mot3, mot4, erreur)
  //   val reponses: List[Reponse] =
  //     List(Texte(mot1), Texte(mot2), Texte(mot3), Texte(mot4), Erreur)
  //   assertEquals(
  //     mots,
  //     r.reponseToString(reponses, Francais)
  //   )
  // }

  // @Test
  // def test_reponseToString_7: Unit = {
  //   val mot1: String = "aaaa"
  //   val mot2: String = "bbbb"
  //   val mot3: String = "cccc"
  //   val mot4: String = "dddd"
  //   val mots: List[String] = List(erreur, mot1, mot2, mot3, mot4, erreur)
  //   val reponses: List[Reponse] =
  //     List(Erreur, Texte(mot1), Texte(mot2), Texte(mot3), Texte(mot4), Erreur)
  //   assertEquals(
  //     mots,
  //     r.reponseToString(reponses, Francais)
  //   )
  // }
  // @Test
  // def test_reponseToString_8: Unit = {
  //   val mot1: String = "aaaa"
  //   val mot2: String = "bbbb"
  //   val mot3: String = "cccc"
  //   val mot4: String = "dddd"
  //   val mots: List[String] =
  //     List(erreur, erreur, mot1, mot2, mot3, mot4, erreur)
  //   val reponses: List[Reponse] =
  //     List(
  //       Erreur,
  //       Erreur,
  //       Texte(mot1),
  //       Texte(mot2),
  //       Texte(mot3),
  //       Texte(mot4),
  //       Erreur
  //     )
  //   assertEquals(
  //     mots,
  //     r.reponseToString(reponses, Francais)
  //   )
  // }
  // @Test
  // def test_reponseToString_9: Unit = {
  //   val mot1: String = "aaaa"
  //   val mot2: String = "bbbb"
  //   val mot3: String = "cccc"
  //   val mot4: String = "dddd"
  //   val mots: List[String] =
  //     List(erreur, erreur, mot1, mot2, erreur, mot3, mot4, erreur, erreur)
  //   val reponses: List[Reponse] =
  //     List(
  //       Erreur,
  //       Erreur,
  //       Texte(mot1),
  //       Texte(mot2),
  //       Erreur,
  //       Texte(mot3),
  //       Texte(mot4),
  //       Erreur,
  //       Erreur
  //     )
  //   assertEquals(
  //     mots,
  //     r.reponseToString(reponses, Francais)
  //   )
  // }
  // @Test
  // def test_reponseToString_10: Unit = {
  //   val mots: List[String] =
  //     List(erreur, erreur, erreur, erreur, erreur, erreur, erreur, erreur)
  //   val reponses: List[Reponse] =
  //     List(
  //       Erreur,
  //       Erreur,
  //       Erreur,
  //       Erreur,
  //       Erreur,
  //       Erreur,
  //       Erreur,
  //       Erreur
  //     )
  //   assertEquals(
  //     mots,
  //     r.reponseToString(reponses, Francais)
  //   )
  // }

  // @Test
  // def test_reponseToString_11: Unit = {
  //   val reponses: List[Reponse] = List()
  //   assertEquals(
  //     List(erreur),
  //     r.reponseToString(reponses, Francais)
  //   )
  // }

  // @Test
  // def test_reponseToString_12: Unit = {
  //   val mot1: String = "bonjour"
  //   val mots: List[String] = List(mot1)
  //   val reponses: List[Reponse] =
  //     List(Texte(mot1))
  //   assertEquals(
  //     mots,
  //     r.reponseToString(reponses, Francais)
  //   )
  // }

  // @Test
  // def test_reponseToString_13: Unit = {
  //   val reponses: List[Reponse] = List(Erreur)
  //   assertEquals(
  //     List(erreur),
  //     r.reponseToString(reponses, Francais)
  //   )
  // }

  // @Test
  // def test_reponseToString_14: Unit = {
  //   val mot1: String = "bonjour"
  //   val mot2: String = "bbbb"
  //   val mot3: String = "cccc"
  //   val mot4: String = "dddd"
  //   val mots: List[String] = List(mot1, mot2, mot3, mot4)
  //   val reponses: List[Reponse] =
  //     List(Texte(mot1), Texte(mot2), Texte(mot3), Texte(mot4))
  //   assertEquals(
  //     mots,
  //     r.reponseToString(reponses, Francais)
  //   )
  // }

  // @Test
  // def test_reponseToString_15: Unit = {
  //   val mot1: String = "bonjour"
  //   val mot2: String = "bbbb"
  //   val mot3: String = "cccc"
  //   val mot4: String = "dddd"
  //   val mots: List[String] = List(erreur, mot1, mot2, mot3, mot4)
  //   val reponses: List[Reponse] =
  //     List(Erreur, Texte(mot1), Texte(mot2), Texte(mot3), Texte(mot4))
  //   assertEquals(
  //     mots,
  //     r.reponseToString(reponses, Francais)
  //   )
  // }

  // @Test
  // def test_reponseToString_16: Unit = {
  //   val mot1: String = "bonjour"
  //   val mot2: String = "bbbb"
  //   val mot3: String = "cccc"
  //   val mot4: String = "dddd"
  //   val mots: List[String] = List(mot1, mot2, mot3, mot4, erreur)
  //   val reponses: List[Reponse] =
  //     List(Texte(mot1), Texte(mot2), Texte(mot3), Texte(mot4), Erreur)
  //   assertEquals(
  //     mots,
  //     r.reponseToString(reponses, Francais)
  //   )
  // }

  // @Test
  // def test_reponseToString_17: Unit = {
  //   val mot1: String = "bonjour"
  //   val mot2: String = "bbbb"
  //   val mot3: String = "cccc"
  //   val mot4: String = "dddd"
  //   val mots: List[String] = List(erreur, mot1, mot2, mot3, mot4, erreur)
  //   val reponses: List[Reponse] =
  //     List(Erreur, Texte(mot1), Texte(mot2), Texte(mot3), Texte(mot4), Erreur)
  //   assertEquals(
  //     mots,
  //     r.reponseToString(reponses, Francais)
  //   )
  // }

  // @Test
  // def test_reponseToString_18: Unit = {
  //   val mot1: String = "bonjour"
  //   val mot2: String = "bbbb"
  //   val mot3: String = "cccc"
  //   val mot4: String = "dddd"
  //   val mots: List[String] =
  //     List(erreur, erreur, mot1, mot2, mot3, mot4, erreur)
  //   val reponses: List[Reponse] =
  //     List(
  //       Erreur,
  //       Erreur,
  //       Texte(mot1),
  //       Texte(mot2),
  //       Texte(mot3),
  //       Texte(mot4),
  //       Erreur
  //     )
  //   assertEquals(
  //     mots,
  //     r.reponseToString(reponses, Francais)
  //   )
  // }

  // @Test
  // def test_reponseToString_19: Unit = {
  //   val mot1: String = "bonjour"
  //   val mot2: String = "bbbb"
  //   val mot3: String = "cccc"
  //   val mot4: String = "dddd"
  //   val mots: List[String] =
  //     List(erreur, erreur, mot1, mot2, erreur, mot3, mot4, erreur, erreur)
  //   val reponses: List[Reponse] =
  //     List(
  //       Erreur,
  //       Erreur,
  //       Texte(mot1),
  //       Texte(mot2),
  //       Erreur,
  //       Texte(mot3),
  //       Texte(mot4),
  //       Erreur,
  //       Erreur
  //     )
  //   assertEquals(
  //     mots,
  //     r.reponseToString(reponses, Francais)
  //   )
  // }

  // @Test
  // def test_reponseToString_21: Unit = {
  //   val mot1: String = "Bonjour"
  //   val mots: List[String] = List(mot1, erreur)
  //   val reponses: List[Reponse] =
  //     List(
  //       Bonjour,
  //       Erreur
  //     )
  //   assertEquals(
  //     mots,
  //     r.reponseToString(reponses, Francais)
  //   )
  // }

  // @Test
  // def test_reponseToString_22: Unit = {
  //   val mot1: String = "Bonjour"
  //   val mots: List[String] = List(mot1, erreur)
  //   val reponses: List[Reponse] =
  //     List(
  //       Bonjour,
  //       Erreur,
  //       Erreur,
  //       Erreur,
  //       Erreur,
  //       Erreur,
  //       Erreur,
  //       Erreur
  //     )
  // }

  // @Test
  // def test_reponseToString_23: Unit = {
  //   val mot1: String = "Bonjour"
  //   val mots: List[String] = List(mot1, mot1, erreur)
  //   val reponses: List[Reponse] =
  //     List(
  //       Bonjour,
  //       Bonjour,
  //       Erreur
  //     )
  //   assertEquals(
  //     mots,
  //     r.reponseToString(reponses, Francais)
  //   )
  // }

  // @Test
  // def test_reponseToString_24: Unit = {
  //   val mot1: String = "Bonjour"
  //   val mots: List[String] = List(mot1, mot1, erreur, mot1)
  //   val reponses: List[Reponse] =
  //     List(
  //       Bonjour,
  //       Bonjour,
  //       Erreur,
  //       Bonjour
  //     )
  //   assertEquals(
  //     mots,
  //     r.reponseToString(reponses, Francais)
  //   )
  // }

  //   // TODO : compléter
}
