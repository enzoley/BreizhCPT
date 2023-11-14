package machine
import org.junit.Test
import org.junit.Assert._

class TestBaseDeDonnees {

  // initialisation des objets sous test
  val b = BaseDeDonnees

  // tests
  @Test
  def test_getMap_size {
    BaseDeDonnees.reinitBDD
    assertEquals(1934, b.getMap.size)
  }
}
