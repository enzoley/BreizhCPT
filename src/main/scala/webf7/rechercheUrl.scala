package webf7
import webf7.AnalysePage
import javax.swing.text.AbstractDocument.Content
import machine.Contenu
import java.text.Normalizer

object AnalyseUrl extends AnalysePage {
  var cpt = 0

  def getListContenu(mots: List[String]): List[Contenu] = {
    val url: String = generationURL(mots)
    val html: Html =
      OutilsWebObjet.obtenirHtml(analyseurUrl(OutilsWebObjet.obtenirHtml(url)))
    var nom: String = getNom(html)
    if (nom == "") nom = getNom2(html)
    nom = nom.replace("&#039;", "'")
    Contenu(nom, analyseurAdresse(html)) :: Nil
  }

  private def removeAccents(mots: List[String]): List[String] = {
    mots.map { s =>
      Normalizer
        .normalize(s, Normalizer.Form.NFD)
        .replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
    }
  }

  private def getKeyWords(mots: List[String]): String = removeAccents(
    mots
  ) match {
    case Nil          => throw new Exception("Liste de mot est vide")
    case head :: Nil  => head
    case head :: next => head + "+" + getKeyWords(next)
  }

  private def generationURL(mots: List[String]): String =
    "https://www.linternaute.com/restaurant/guide/ville-rennes-35000/?name=" + getKeyWords(
      mots
    )

  private def analyseurUrl(html: Html): String = {
    cpt = 0
    recupererUrl(html)
  }

  private def recupererUrl(html: Html): String = {
    html match {
      case Tag(
            "h2",
            List(
              (
                "class",
                "bu_restaurant_title_2"
              )
            ),
            List(
              Tag(
                "a",
                List(("href", url)),
                List(Texte(texte))
              )
            )
          ) => {
        cpt = cpt + 1
        "https://www.linternaute.com" + url
      }
      case Tag(name, attributes, children) => {
        var suite: String = ""
        for (c <- children) {
          if (cpt == 0) {
            suite = recupererUrl(c)
          }
        }
        suite
      }
      case _ => ""
    }
  }

  private def analyseurAdresse(html: Html): String = {
    cpt = 0
    recupererAdresse(html)
  }
  private def recupererAdresse(html: Html): String = {
    html match {
      case Tag(
            "meta",
            List(
              ("content", texte),
              (
                "property",
                "restaurant:contact_info:street_adress"
              )
            ),
            _
          ) => {
        cpt = cpt + 1
        texte
      }
      case Tag(name, attributes, children) => {
        var suite: String = ""
        for (c <- children) {
          if (cpt == 0) {
            suite = recupererAdresse(c)
          }
        }
        suite
      }
      case _ => ""
    }
  }

  private def getNom(html: Html): String = {
    html match {
      case Tag(
            "h1",
            List(("class", "bu_restaurant_title_xl")),
            Texte(nom) :: reste
          ) =>
        nom
      case Tag(name, attributes, children) => {
        var suite: String = ""
        for (c <- children) {
          suite = suite + getNom(c)
        }
        suite
      }
      case _ => ""
    }
  }

  private def getNom2(html: Html): String = {
    html match {
      case Tag(
            "div",
            List(("class", "grid_left w80")),
            children
          ) =>
        aux_getNom(children)
      case Tag(name, attributes, children) => {
        var suite: String = ""
        for (c <- children) {
          suite = suite + getNom2(c)
        }
        suite
      }
      case _ => ""
    }
  }

  private def aux_getNom(htmls: List[Html]): String = htmls match {
    case head :: next =>
      head match {
        case Tag("h1", _, Texte(nom) :: Nil) => nom
        case _                               => aux_getNom(next)
      }
    case Nil => ""
  }
}
