package machine
import org.junit.Test
import org.junit.Assert._

class TestParseur {

  // initialisation des objets sous test
  val parseur = Parseur

  // tests
  @Test
  def test_spliter_1 {
    val phrase: String =
      ""
    assertEquals(
      List(),
      parseur.spliter(phrase)
    )
  }
  @Test
  def test_spliter_2 {
    val phrase: String = "Je vais à l'école, mais il pleut."
    assertEquals(
      List("vais", "école", "pleut"),
      parseur.spliter(phrase)
    )
  }

  @Test
  def test_spliter_3 {
    val phrase: String = "2024 :     n "
    println(parseur.spliter(phrase))
    assertEquals(
      List("2024"),
      parseur.spliter(phrase)
    )
  }

  @Test
  def test_spliter_4 {
    val phrase: String = "Il est 10h30min et 30 seconde"
    assertEquals(
      List("10h30min", "seconde"),
      parseur.spliter(phrase)
    )
  }

  @Test
  def test_spliter_5 {
    val phrase: String = "l'hotel de ville"
    assertEquals(
      List("hotel", "ville"),
      parseur.spliter(phrase)
    )
  }

  @Test
  def test_spliter_6 {
    val phrase: String = "l'hôtel de ville"
    assertEquals(
      List("hôtel", "ville"),
      parseur.spliter(phrase)
    )
  }
}
