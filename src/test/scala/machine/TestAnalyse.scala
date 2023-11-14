package machine
import org.junit.Test
import org.junit.Assert._

class TestAnalyse {

  val a = AnalysePhrase
  BaseDeDonnees.reinitBDD

  val mairie: Reponse = Contenu("Mairie de Rennes", "Place de la Mairie")
  val paillette: Reponse =
    Contenu("Théâtre La Paillette", "2, Rue du Pré de Bris")
  val tnb: Reponse =
    Contenu("Théâtre National de Bretagne", "1, Rue Saint-Hélier")
  val gare: Reponse = Contenu("Gare SNCF", "19, Place de la Gare")

  val piscine_Bréquigny: Reponse =
    Contenu("Piscine Bréquigny", "10, Boulevard Albert 1er")

  // tests

  // @Test
  // def test_uniformiser_1 {
  //   assertEquals(List(), AnalysePhrase.uniformiser(List()))
  // }

  // @Test
  // def test_uniformiser_2 {
  //   assertEquals(
  //     List("mairie"),
  //     AnalysePhrase.uniformiser(List("hôtel", "ville"))
  //   )
  // }

  @Test
  def test_analyse_1 {
    val phrase1: String = "Où est donc la Mairie de Rennes?"
    val rep: List[Reponse] =
      List(mairie)
    assertEquals(rep, a.analyser(phrase1))
  }

  @Test
  def test_analyse_2 {
    val phrase2: String = "Où se trouve le Théâtre National de Bretagne?"
    val rep: List[Reponse] =
      List(tnb)
    assertEquals(rep, a.analyser(phrase2))
  }

  @Test
  def test_analyse_3 {
    val phrase3: String = "Où est la Mairie?"
    val rep: List[Reponse] =
      List(mairie)
    assertEquals(rep, a.analyser(phrase3))
  }

  @Test
  def test_analyse_4 {
    val phrase4: String = "Je recherche le Théâtre de la Paillette"
    val rep: List[Reponse] =
      List(paillette)
    assertEquals(rep, a.analyser(phrase4))
  }

  @Test
  def test_analyse_5 {
    val phrase5: String = "Où est le TNB?"
    val rep: List[Reponse] =
      List(tnb)
    assertEquals(rep, a.analyser(phrase5))
  }

  @Test
  def test_analyse_6 {
    val phrase6: String = "ou trouver?"
    val rep: List[Reponse] =
      List(Erreur)
    assertEquals(rep, a.analyser(phrase6))
  }

  @Test
  def test_analyse_7 {
    val phrase7: String = "Je cherche"
    val rep: List[Reponse] =
      List(Erreur)
    assertEquals(rep, a.analyser(phrase7))
  }

  @Test
  def test_analyse_8 {
    val phrase8: String = "askdhlkajh"
    val rep: List[Reponse] =
      List(Erreur)
    assertEquals(rep, a.analyser(phrase8))
  }

  @Test
  def test_analyse_9 {
    val phrase9: String = "Où est la Gare?"
    val rep: List[Reponse] =
      List(gare)
    assertEquals(rep, a.analyser(phrase9))
  }

  @Test
  def test_analyse_10 {
    val phrase10: String = "Où est donc la Mairie Rennes?"
    val rep: List[Reponse] =
      List(mairie)
    assertEquals(rep, a.analyser(phrase10))
  }

  @Test
  def test_analyse_11 {
    val phrase11: String = "hôtel ville?"
    val rep: List[Reponse] =
      List(mairie)
    assertEquals(rep, a.analyser(phrase11))
  }

  @Test
  def test_analyse_12 {
    val phrase12: String = "Je recherche le théâtre paillette"
    val rep: List[Reponse] =
      List(paillette)
    assertEquals(rep, a.analyser(phrase12))
  }

  @Test
  def test_analyse_13 {
    val phrase13: String = "Où est donc l'htel de ville?"
    val rep: List[Reponse] =
      List(mairie)
    assertEquals(rep, a.analyser(phrase13))
  }

  @Test
  def test_analyse_14 {
    val phrase14: String = "Où est donc l'hotil de ville?"
    val rep: List[Reponse] =
      List(mairie)
    assertEquals(rep, a.analyser(phrase14))
  }

  @Test
  def test_analyse_15 {
    val phrase15: String = "Où est donc l'otel de ville?"
    val rep: List[Reponse] =
      List(mairie)
    assertEquals(rep, a.analyser(phrase15))
  }

  @Test
  def test_analyse_16 {
    val phrase16: String = "Où est donc l'hote de ville?"
    val rep: List[Reponse] =
      List(mairie)
    assertEquals(rep, a.analyser(phrase16))
  }

  @Test
  def test_analyse_17 {
    val phrase17: String = "Où est donc l'hote de valle?"
    val rep: List[Reponse] =
      List(mairie)
    assertEquals(rep, a.analyser(phrase17))
  }

  @Test
  def test_analyse_18 {
    val phrase18: String = "Je recherche le teatre paillete"
    val rep: List[Reponse] =
      List(paillette)
    assertEquals(rep, a.analyser(phrase18))
  }

  @Test
  def test_analyse_19 {
    val phrase19: String = "Où se trouve le théâtre de bretagne?"
    val rep: List[Reponse] =
      List(tnb)
    assertEquals(rep, a.analyser(phrase19))
  }

  @Test
  def test_analyse_20 {
    val phrase20: String = "bonjour"
    val rep: List[Reponse] =
      List(Bonjour)
    assertEquals(rep, a.analyser(phrase20))
  }

  @Test
  def test_analyse_21 {
    val phrase21: String = "bonjour je cherche le tnb"
    val rep: List[Reponse] =
      List(Bonjour, tnb)
    assertEquals(rep, a.analyser(phrase21))
  }

  @Test
  def test_analyse_22 {
    val phrase22: String = "bonour"
    val rep: List[Reponse] =
      List(Bonjour)
    assertEquals(rep, a.analyser(phrase22))
  }

  @Test
  def test_analyse_23 {
    val phrase23: String = "bojour je cherche le tnb"
    val rep: List[Reponse] =
      List(Bonjour, tnb)
    assertEquals(rep, a.analyser(phrase23))
  }

  @Test
  def test_analyse_24 {
    val phrase24: String = "bojour je cherche le hgshg"
    val rep: List[Reponse] =
      List(Bonjour, Erreur)
    assertEquals(rep, a.analyser(phrase24))
  }

  @Test
  def test_analyse_25 {
    val phrase25: String = "bijour djkslfdsklj"
    val rep: List[Reponse] =
      List(Erreur)
    assertEquals(rep, a.analyser(phrase25))
  }

  @Test
  def test_analyse_26 {
    val phrase26: String = "bonour"
    val rep: List[Reponse] =
      List(Bonjour)
    assertEquals(rep, a.analyser(phrase26))
  }

  @Test
  def test_analyse_27 {
    val phrase27: String = "salut, je cherche le tnb"
    val rep: List[Reponse] =
      List(Bonjour, tnb)
    assertEquals(rep, a.analyser(phrase27))
  }

  @Test
  def test_analyse_28 {
    val phrase28: String = "yo busco hotel de vile"
    val rep: List[Reponse] =
      List(mairie)
    assertEquals(rep, a.analyser(phrase28))
  }

  @Test
  def test_analyse_29 {
    val phrase29: String = "bonjour"
    val rep: List[Reponse] =
      List(Bonjour)
    assertEquals(rep, a.analyser(phrase29))
  }

  @Test
  def test_analyse_30 {
    val phrase30: String = "hola"
    val rep: List[Reponse] =
      List(Bonjour)
    assertEquals(rep, a.analyser(phrase30))
  }

  @Test
  def test_analyse_31 {
    val phrase: String = "si"
    val rep: List[Reponse] =
      List()
    assertEquals(rep, a.analyser(phrase))
  }
  @Test
  def test_analyse_32 {
    val phrase: String = "bonjour"
    val rep: List[Reponse] =
      List(Bonjour)
    assertEquals(rep, a.analyser(phrase))
  }
  @Test
  def test_analyse_33 {
    val phrase: String = "hola"
    val rep: List[Reponse] =
      List(Bonjour)
    assertEquals(rep, a.analyser(phrase))
  }
  @Test
  def test_analyse_34 {
    val phrase: String = "as"
    val rep: List[Reponse] =
      List()
    assertEquals(rep, a.analyser(phrase))
  }
  @Test
  def test_analyse_35 {
    val phrase: String = "hola"
    val rep: List[Reponse] =
      List(Bonjour)
    assertEquals(rep, a.analyser(phrase))
  }
  @Test
  def test_analyse_36 {
    val phrase: String = "si"
    val rep: List[Reponse] =
      List()
    assertEquals(rep, a.analyser(phrase))
  }
  @Test
  def test_analyse_37 {
    val phrase: String = "busco gare"
    val rep: List[Reponse] =
      List(gare)
    assertEquals(rep, a.analyser(phrase))
  }
  @Test
  def test_analyse_38 {
    val phrase: String = "suche theatre paillette"
    val rep: List[Reponse] =
      List(paillette)
    assertEquals(rep, a.analyser(phrase))
  }
  @Test
  def test_analyse_39 {
    val phrase: String = "cerco hotel de ville"
    val rep: List[Reponse] =
      List(mairie)
    assertEquals(rep, a.analyser(phrase))
  }
  @Test
  def test_analyse_40 {
    val phrase: String = "si"
    val rep: List[Reponse] =
      List()
    assertEquals(rep, a.analyser(phrase))
  }
  @Test
  def test_analyse_41 {
    val phrase: String = "bongiorno"
    val rep: List[Reponse] =
      List(Bonjour)
    assertEquals(rep, a.analyser(phrase))
  }
  @Test def test_analyse_42 {
    val phrase: String = "wo ist teatre paillette"
    val rep: List[Reponse] =
      List(paillette)
    assertEquals(rep, a.analyser(phrase))
  }
  @Test def test_analyse_43 {
    val phrase: String = "ja"
    val rep: List[Reponse] =
      List()
    assertEquals(rep, a.analyser(phrase))
  }
  @Test def test_analyse_44 {
    val phrase: String = "wo ist teatre paillette"
    val rep: List[Reponse] =
      List(paillette)
    assertEquals(rep, a.analyser(phrase))
  }
  @Test def test_analyse_45 {
    val phrase: String = "gutten tag"
    val rep: List[Reponse] =
      List(Bonjour, Erreur)
    assertEquals(rep, a.analyser(phrase))
  }

  @Test def test_analyse_46 {
    val phrase: String = "ja"
    val rep: List[Reponse] =
      List()
    assertEquals(rep, a.analyser(phrase))
  }
  @Test def test_analyse_47 {
    val phrase: String = "hallo"
    val rep: List[Reponse] =
      List(Bonjour)
    assertEquals(rep, a.analyser(phrase))
  }
  @Test def test_analyse_48 {
    val phrase: String = "wo ist gare"
    val rep: List[Reponse] =
      List(gare)
    assertEquals(rep, a.analyser(phrase))
  }
  @Test def test_analyse_49 {
    val phrase: String = "ou est la gare?"
    val rep: List[Reponse] =
      List(gare)
    assertEquals(rep, a.analyser(phrase))
  }
  @Test def test_analyse_50 {
    val phrase: String = "hallo"
    val rep: List[Reponse] =
      List(Bonjour)
    assertEquals(rep, a.analyser(phrase))
  }
  @Test def test_analyse_51 {
    val phrase: String = "ja"
    val rep: List[Reponse] =
      List()
    assertEquals(rep, a.analyser(phrase))
  }
  @Test def test_analyse_52 {
    val phrase: String = "donde esta el tnb?"
    val rep: List[Reponse] =
      List(tnb)
    assertEquals(rep, a.analyser(phrase))
  }
  @Test def test_analyse_53 {
    val phrase: String = "si"
    val rep: List[Reponse] =
      List()
    assertEquals(rep, a.analyser(phrase))
  }
  @Test def test_analyse_54 {
    val phrase: String = "tnb?"
    val rep: List[Reponse] =
      List(tnb)
    assertEquals(rep, a.analyser(phrase))
  }
  @Test def test_analyse_55 {
    val phrase: String = "italiano?"
    val rep: List[Reponse] =
      List(Erreur)
    assertEquals(rep, a.analyser(phrase))
  }
  @Test def test_analyse_56 {
    val phrase: String = "si"
    val rep: List[Reponse] =
      List()
    assertEquals(rep, a.analyser(phrase))
  }
  @Test def test_analyse_57 {
    val phrase: String = "cerco paillette"
    val rep: List[Reponse] =
      List(paillette)
    assertEquals(rep, a.analyser(phrase))
  }
  @Test def test_analyse_58 {
    val phrase: String = "bonjour where ist gare"
    val rep: List[Reponse] =
      List(Bonjour, gare)
    assertEquals(rep, a.analyser(phrase))
  }
  @Test def test_analyse_59 {
    val phrase: String = "hola"
    val rep: List[Reponse] =
      List(Bonjour)
    assertEquals(rep, a.analyser(phrase))
  }
  @Test def test_analyse_60 {
    val phrase: String = "buongiorno"
    val rep: List[Reponse] =
      List(Bonjour)
    assertEquals(rep, a.analyser(phrase))
  }
  @Test def test_analyse_61 {
    val phrase: String = "si"
    val rep: List[Reponse] =
      List()
    assertEquals(rep, a.analyser(phrase))
  }
  @Test def test_analyse_62 {
    val phrase: String = "cerco"
    val rep: List[Reponse] =
      List(Erreur)
    assertEquals(rep, a.analyser(phrase))
  }
  @Test def test_analyse_63 {
    val phrase: String = "hallo"
    val rep: List[Reponse] =
      List(Bonjour)
    assertEquals(rep, a.analyser(phrase))
  }
  @Test def test_analyse_64 {
    val phrase: String = "bonjour"
    val rep: List[Reponse] =
      List(Bonjour)
    assertEquals(rep, a.analyser(phrase))
  }
  @Test def test_analyse_65 {
    val phrase: String = "oui"
    val rep: List[Reponse] =
      List()
    assertEquals(rep, a.analyser(phrase))
  }
  @Test def test_analyse_66 {
    val phrase: String = "ciao"
    val rep: List[Reponse] =
      List(Bonjour)
    assertEquals(rep, a.analyser(phrase))
  }
  @Test def test_analyse_67 {
    val phrase: String = "bonjour"
    val rep: List[Reponse] =
      List(Bonjour)
    assertEquals(rep, a.analyser(phrase))
  }
  @Test def test_analyse_68 {
    val phrase: String = "oui"
    val rep: List[Reponse] =
      List()
    assertEquals(rep, a.analyser(phrase))
  }
  @Test def test_analyse_69 {
    val phrase: String = "tnb"
    val rep: List[Reponse] =
      List(tnb)
    assertEquals(rep, a.analyser(phrase))
  }
  @Test def test_analyse_70 {
    val phrase: String = "ciao"
    val rep: List[Reponse] =
      List(Bonjour)
    assertEquals(rep, a.analyser(phrase))
  }
  @Test def test_analyse_71 {
    val phrase: String = "si"
    val rep: List[Reponse] =
      List()
    assertEquals(rep, a.analyser(phrase))
  }
  @Test def test_analyse_72 {
    val phrase: String = "hallo"
    val rep: List[Reponse] =
      List(Bonjour)
    assertEquals(rep, a.analyser(phrase))
  }
  @Test def test_analyse_73 {
    val phrase: String = "ja"
    val rep: List[Reponse] =
      List()
    assertEquals(rep, a.analyser(phrase))
  }
  @Test def test_analyse_74 {
    val phrase: String = "so"
    val rep: List[Reponse] =
      List()
    assertEquals(rep, a.analyser(phrase))
  }
  @Test def test_analyse_75 {
    val phrase: String = "do you speak english?"
    val rep: List[Reponse] =
      List(Erreur)
    assertEquals(rep, a.analyser(phrase))
  }

  @Test def test_analyse_77 {
    val phrase: String = "I am looking for tnb"
    val rep: List[Reponse] =
      List(tnb)
    assertEquals(rep, a.analyser(phrase))
  }
  @Test def test_analyse_78 {
    val phrase: String = "hablas espanol?"
    val rep: List[Reponse] =
      List(Erreur)
    assertEquals(rep, a.analyser(phrase))
  }
  @Test def test_analyse_79 {
    val phrase: String = "si"
    val rep: List[Reponse] =
      List()
    assertEquals(rep, a.analyser(phrase))
  }
  @Test def test_analyse_80 {
    val phrase: String = "busco gare"
    val rep: List[Reponse] =
      List(gare)
    assertEquals(rep, a.analyser(phrase))
  }
  @Test def test_analyse_81 {
    val phrase: String = "deutch?"
    val rep: List[Reponse] =
      List(Erreur)
    assertEquals(rep, a.analyser(phrase))
  }
  @Test def test_analyse_82 {
    val phrase: String = "ja"
    val rep: List[Reponse] =
      List()
    assertEquals(rep, a.analyser(phrase))
  }
  @Test def test_analyse_83 {
    val phrase: String = "tnb?"
    val rep: List[Reponse] =
      List(tnb)
    assertEquals(rep, a.analyser(phrase))
  }
  @Test def test_analyse_84 {
    val phrase: String = "espanol?"
    val rep: List[Reponse] =
      List(Erreur)
    assertEquals(rep, a.analyser(phrase))
  }
  @Test def test_analyse_85 {
    val phrase: String = "gloubi"
    val rep: List[Reponse] =
      List(Erreur)
    assertEquals(rep, a.analyser(phrase))
  }
  @Test def test_analyse_86 {
    val phrase: String = "gar"
    val rep: List[Reponse] =
      List(gare)
    assertEquals(rep, a.analyser(phrase))
  }
  @Test def test_analyse_87 {
    val phrase: String = "donde esta el hotel de ville?"
    val rep: List[Reponse] =
      List(mairie)
    assertEquals(rep, a.analyser(phrase))
  }
  @Test def test_analyse_88 {
    val phrase: String = "hello"
    val rep: List[Reponse] =
      List(Bonjour)
    assertEquals(rep, a.analyser(phrase))
  }

  @Test def test_analyse_90 {
    val phrase: String = "I lokk for la gare"
    val rep: List[Reponse] =
      List(gare)
    assertEquals(rep, a.analyser(phrase))
  }
  @Test def test_analyse_91 {
    val phrase: String = "Je cherche la Piscine de brequigny"
    val rep: List[Reponse] =
      List(piscine_Bréquigny)
    assertEquals(rep, a.analyser(phrase))
  }
  @Test def test_analyse_92 {
    val phrase: String = "je cherche la piscine de brequigny"
    val rep: List[Reponse] =
      List(piscine_Bréquigny)
    assertEquals(rep, a.analyser(phrase))
  }
  @Test def test_analyse_94 {
    val phrase: String = "ou est le 40mcube?"
    val rep: List[Reponse] =
      List(Contenu("40mcube", "48, AVENUE SERGENT MAGINOT"))
    assertEquals(rep, a.analyser(phrase))
  }
  @Test def test_analyse_95 {
    val phrase: String = "clair obscur?"
    val rep: List[Reponse] =
      List(Contenu("Clair Obscur", "5, RUE DE LORRAINE,RUE DE LORRAINE"))
    assertEquals(rep, a.analyser(phrase))
  }
}
