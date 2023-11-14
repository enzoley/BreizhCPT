package machine
import org.junit.Test
import org.junit.Assert._

class TestGenerationParole {
  val genP = new GenerationParole()

  @Test
  def gen1 {
    genP.generationParole("Hello This is a test sentence")
    assertTrue(true)
  }

  @Test
  def gen2 {
    genP.generationParole("Ciao facciamo una frase di test")
    assertTrue(true)
  }
}
