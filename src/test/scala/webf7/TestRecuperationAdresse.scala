package webf7

import org.junit.Test
import org.junit.Assert._

class TestRecuperationAdresse {
  val truc = AnalyseUrl

  // @Test
  // def testAdr1 {
  //   assertEquals(
  //     "18, rue Saint Georges",
  //     AnalyseUrl.analyseurAdresse(
  //       OutilsWebObjet.obtenirHtml(
  //         AnalyseUrl.analyseurUrl(
  //           OutilsWebObjet.obtenirHtml(
  //             "https://www.linternaute.com/restaurant/guide/ville-rennes-35000/?name=tomate"
  //           )
  //         )
  //       )
  //     )
  //   )
  // }

  // @Test
  // def testAdr2 {
  //   assertEquals(
  //     "Centre Commercial Colombia",
  //     AnalyseUrl.analyseurAdresse(
  //       OutilsWebObjet.obtenirHtml(
  //         AnalyseUrl.analyseurUrl(
  //           OutilsWebObjet.obtenirHtml(
  //             "https://www.linternaute.com/restaurant/guide/ville-rennes-35000/?name=mcdonalds"
  //           )
  //         )
  //       )
  //     )
  //   )
  // }

  // @Test
  // def testAdrListe1 {
  //   val motsNom: List[String] = List("la", "tomate")
  //   assertEquals(
  //     "https://www.linternaute.com/restaurant/guide/ville-rennes-35000/?name=la+tomate",
  //     AnalyseUrl.generationURL(List("la", "tomate"))
  //   )
  // }

  // @Test
  // def testAdrListe2 {
  //   val motsNom: List[String] = List("Saint", "Georges")
  //   assertEquals("38, Rue Saint georges", AnalyseUrl.getAdrFromNom(motsNom))
  // }
}
