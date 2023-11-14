package machine
import org.junit.Test
import org.junit.Assert._

class TestFichier {

  // initialisation des objets sous test
  val f = Fichier

  // tests
  // @Test
  // def test_getCheminAbsolu_fail {
  //   val cheminAbsolu = ""
  //   val cheminRelatif = ""

  //   try {
  //     f.getCheminAbsolu(cheminRelatif);
  //     fail();
  //   } catch {
  //     case exn: IllegalArgumentException =>
  //       () // Rattrape uniquement l'exception déclarée, et levée explicitement
  //     case exn: MatchError =>
  //       () // Rattrape l'exception matchError levée implicitement
  //   }
  // }

  // tests
  @Test
  def test_extraireDonnees_ligne_cle_valeur {
    for ((cle, valeur) <- f.extraireDonnees()) {
      assertTrue(
        cle.length > 0 && valeur.length > 0
      )
    }
  }

  @Test
  def test_extraireDonnees_nombre {
    assertTrue(
      f.extraireDonnees.size > 1600
    )
  }

}
