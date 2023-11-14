package machine
import org.junit.Test
import org.junit.Assert._

class TestRechercheMotCle {

  // initialisation des objets sous test
  val r = RechercheMotCle
  BaseDeDonnees.reinitBDD

  val mairie: Reponse = Contenu("Mairie de Rennes", "Place de la Mairie")
  val paillette: Reponse =
    Contenu("Théâtre La Paillette", "2, Rue du Pré de Bris")
  val tnb: Reponse =
    Contenu("Théâtre National de Bretagne", "1, Rue Saint-Hélier")
  val gare: Reponse = Contenu("Gare SNCF", "19, Place de la Gare")

  // // tests
  @Test
  def test_reponse_Erreur_1 {
    assertEquals(List(Erreur), RechercheMotCle.reponse(""))
  }

  @Test
  def test_reponse_Erreur_2 {
    assertEquals(List(Erreur), RechercheMotCle.reponse("degsrtpo"))
  }
  def test_reponse_Bonjour {
    assertEquals(
      List(Bonjour),
      r.reponse("bonjour")
    )
  }

  @Test
  def test_reponse_mairie_1 {
    val rep = List(mairie)
    assertEquals(rep, RechercheMotCle.reponse("mairie"))
  }

  @Test
  def test_reponse_mairie_2 {
    assertTrue(RechercheMotCle.reponse("rennes").contains(mairie))
  }

  @Test
  def test_reponse_gare_1 {
    val rep = List(gare)
    assertEquals(rep, RechercheMotCle.reponse("gare"))
  }

  @Test
  def test_reponse_gare_2 {
    assertTrue(RechercheMotCle.reponse("sncf").contains(gare))
  }

  @Test
  def test_reponse_tnb_1 {
    assertTrue(RechercheMotCle.reponse("national").contains(tnb))
  }

  @Test
  def test_reponse_tnb_2 {
    assertTrue(RechercheMotCle.reponse("bretagne").contains(tnb))
  }

  @Test
  def test_reponse_paillette {
    val rep = List(paillette)
    assertEquals(rep, RechercheMotCle.reponse("paillette"))
  }

  @Test
  def test_reponse_théâtre {
    assertTrue(
      RechercheMotCle.reponse("théâtre").contains(tnb) && RechercheMotCle
        .reponse("théâtre")
        .contains(paillette)
    )
  }

}
