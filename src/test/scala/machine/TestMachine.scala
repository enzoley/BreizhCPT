package machine
import org.junit.Test
import org.junit.Assert._

class TestIntegration {

  // initialisation des objets sous test
  val m = MachineImpl
  m.reinit

  // tests
  @Test
  def testIntegration_1 {
    assertEquals(
      List("L'adresse de Mairie de Rennes est : Place de la Mairie"),
      m.test(List("Où est donc la Mairie de Rennes?"))
    )
  }

  @Test
  def testIntegration_2 {
    assertEquals(
      List(
        "L'adresse de Mairie de Rennes est : Place de la Mairie",
        "L'adresse de Gare SNCF est : 19, Place de la Gare",
        "Je ne comprends pas votre demande"
      ),
      m.test(List("Je cherche la Mairie", "et la Gare?", "je cherche"))
    )
  }

  @Test
  def testIntegration_3 {
    assertEquals(
      List("L'adresse de Mairie de Rennes est : Place de la Mairie"),
      m.test(List("l'hotel de ville"))
    )
  }

  @Test
  def testIntegration_4 {
    assertEquals(
      List("L'adresse de Mairie de Rennes est : Place de la Mairie"),
      m.test(List("hotl de valle"))
    )
  }
}
