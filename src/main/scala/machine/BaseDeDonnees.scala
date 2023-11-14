package machine

import scala.collection.immutable

trait TraitBaseDeDonnees {

  // Permet de reinitialiser la BDD
  def reinitBDD(): Unit

  /** Getteur : Il extraire les donnees (cf: Fichier.extraireDonnees()) et
    * transformer la liste de liste de String en une map lien un cle composée
    * d'un seul mot. Soit ligne = List[String], Fichier.extraireDonnees() est
    * une liste de ligne avec 2 elements par ligne, une cle et une valeur. Pour
    * chaque liste, le premier element est une phrase cle. Il faut la trier (cf:
    * AnalysePhrase.trier(Parseur.spliter(phrase cle))) chauque element (String)
    * obtenu doit être associer à le valeur. A voir si il faut gérer les
    * synonimes
    *
    * @return
    *   une map cle => valeur avec cle un mot et valeur la phrase reponse
    *   associer
    */
  def getMap(): Map[String, List[(String, String)]]

  // Getteur : Map cle => valeur avec cle le string à remplacer et la valeur le string de remplacement
  def getMapSynonymes(): Map[List[String], List[String]]

  // Getteur : Map qui assosie le message d'erreur à la langue : langue => message d'erreur
  def getMapErreurs(): Map[Langue, String]

  // Getteur : Map qui assosie le message bonjour à la langue : langue => message bonjour
  def getMapBonjours(): Map[Langue, String]

  // Getteur : Map qui assosie le message résulat à la langue : langue => message résulat
  def getMapResultats(): Map[Langue, String]

  // Getteur : Map qui assosie le message de reponses possibles à la langue : langue => message de reponses possibles
  def getMapReponsesPossibles(): Map[Langue, String]

  // Getteur : Map qui assosie le message de choix à la langue : langue => message de choix
  def getMapChoix(): Map[Langue, String]

  // Getteur : List des mots bonjour
  def getListBonjours(): List[String]

  // Getteur : List des mots Francais
  def getListMotsFrancais(): List[String]

  // Getteur : List des mots Anglais
  def getListMotsAnglais(): List[String]

  // Getteur : List des mots Espagnol
  def getListMotsEspagnol(): List[String]

  // Getteur : List des mots Allemand
  def getListMotsAllemand(): List[String]

  // Getteur : List des mots Italien
  def getListMotsItalien(): List[String]

  // La liste des mots importants, cela corespondant au dictionnaire
  def getListMotsImportants(): List[String]

}

object BaseDeDonnees extends TraitBaseDeDonnees {
  private var mapCleValeur: Map[String, String] = Map()
  private var mapDonnees: Map[String, List[(String, String)]] = Map()
  private val mapSynonymes: Map[String, String] = Map(
    "Hôtel de ville" -> "Mairie",
    "TNB" -> "Théâtre National de Bretagne",
    "Théâtre de Bretagne" -> "Théâtre National de Bretagne",
    "Paillette" -> "Théâtre la Paillette",
    "Gare" -> "Gare SNCF"
  )
  private val mapErreurs: Map[Langue, String] =
    Map(
      Francais -> "Je ne comprends pas votre demande",
      Anglais -> "I do not understand",
      Espagnol -> "No comprendo",
      Allemand -> "Ich verstehe nicht",
      Italien -> "No capisco"
    )
  private val mapBonjours: Map[Langue, String] =
    Map(
      Francais -> "Bonjour",
      Anglais -> "Hello",
      Espagnol -> "Hola",
      Allemand -> "Hallo",
      Italien -> "Buongiorno"
    )
  private val mapResultats: Map[Langue, String] =
    Map(
      Francais -> "L'adresse de XXX est",
      Anglais -> "The address of XXX is",
      Espagnol -> "La dirección de XXX es",
      Allemand -> "Die adresse von XXX ist",
      Italien -> "Indirizzo di XXX è"
    )
  private val mapReponsesPossibles: Map[Langue, String] = Map(
    Francais -> "J'ai XXX réponses possibles :",
    Anglais -> "I found XXX answers :",
    Espagnol -> "Tengo XXX opciones :",
    Allemand -> "Ich habe XXX Antworten :",
    Italien -> "Ho XXX risposte :"
  )
  private val mapChoix: Map[Langue, String] = Map(
    Francais -> "Quel est votre choix?",
    Anglais -> "What is your choice?",
    Espagnol -> "Cuál es su elección?",
    Allemand -> "Was ist Ihre Wahl?",
    Italien -> "Qual è la vostra scelta?"
  )
  private val listBonjours: List[String] = List(
    "bonjour",
    "salut",
    "hi",
    "hello",
    "hola",
    "buenos",
    "hallo",
    "guten",
    "buongiorno",
    "ciao",
    "salve",
    "buon"
  )
  private val listMotsFrancais: List[String] = List(
    "bonjour",
    "salut",
    "bonsoir",
    "recherche",
    "cherche",
    "ou",
    "est",
    "donc",
    "trouve",
    "trouver",
    "français"
  )
  private val listMotsAnglais: List[String] = List(
    "hi",
    "hello",
    "morning",
    "evening",
    "afternoon",
    "hey",
    "seek",
    "seeking",
    "search",
    "searching",
    "look",
    "looking",
    "where",
    "find",
    "english"
  )
  private val listMotsEspagnol: List[String] = List(
    "hola",
    "buenos",
    "dias",
    "donde",
    "esta",
    "busco",
    "buscando",
    "español"
  )
  private val listMotsAllemand: List[String] = List(
    "hallo",
    "guten",
    "morgen",
    "tag",
    "abend",
    "wo",
    "ist",
    "suche",
    "suchen",
    "deutsch"
  )
  private val listMotsItalien: List[String] = List(
    "buongiorno",
    "ciao",
    "salve",
    "buon",
    "pomeriggio",
    "buonasera",
    "incantato",
    "dove",
    "trova",
    "cerco",
    "cercando",
    "italiano"
  )

  def reinitBDD(): Unit = {
    val donnees: List[(String, String)] = Fichier.extraireDonnees
    mapCleValeur ++= donnees.map { case (k, v) =>
      k -> v
    }.toMap
    var map: Map[String, List[(String, String)]] = Map()

    for (ligne <- donnees) {
      var s: String = ligne._1
      var valeur: String = ligne._2
      val listCle: List[String] = Parseur.spliter(s)
      for (cle <- listCle) {
        if (map.contains(cle)) {
          val list: List[(String, String)] = map(cle)
          val nouvelleList: List[(String, String)] = list :+ (s, valeur)
          map = map.updated(cle, nouvelleList.distinct)
        } else map += (cle -> List((s, valeur)))
      }
      map += ("cramptés" -> List(
        ("Cramptés", "Apagnan, QUOICOUBEH QUOICOUBEH")
      ))
    }
    mapDonnees ++= map
  }

  def getMap(): Map[String, List[(String, String)]] = mapDonnees

  def getMapSynonymes(): Map[List[String], List[String]] =
    mapSynonymes.map { case (cle, valeur) =>
      (Parseur.spliter(cle), Parseur.spliter(valeur))
    }

  def getMapErreurs(): Map[Langue, String] = mapErreurs

  def getMapBonjours(): Map[Langue, String] = mapBonjours

  def getMapResultats(): Map[Langue, String] = mapResultats

  def getMapReponsesPossibles(): Map[Langue, String] = mapReponsesPossibles

  def getMapChoix(): Map[Langue, String] = mapChoix

  def getListBonjours(): List[String] = listBonjours

  def getListMotsFrancais(): List[String] = listMotsFrancais

  def getListMotsAnglais(): List[String] = listMotsAnglais

  def getListMotsEspagnol(): List[String] = listMotsEspagnol

  def getListMotsAllemand(): List[String] = listMotsAllemand

  def getListMotsItalien(): List[String] = listMotsItalien

  // Récupère la liste de tous les mots cle du fichier DonneesInitiales
  private def getListDonneesInitiales(): List[String] = {
    val listMotCle: List[String] = Fichier.extraireDonneesInitiales
      .map(
        _._1
      )
    var res: List[String] = List()
    for (motCle <- listMotCle) {
      res = res ++ Parseur.spliter(motCle)
    }
    res
  }

  // Récupère la liste de tous les mots synonymes
  private def getListMotsSynonymes(): List[String] = {
    var list: List[String] = getMapSynonymes.toList.flatMap {
      case (cle, valeur) => cle ++ valeur
    }
    list.distinct
  }

  def getListMotsImportants(): List[String] =
    (getListDonneesInitiales ++ getListMotsSynonymes ++ listBonjours ++ listMotsFrancais ++ listMotsAnglais ++ listMotsEspagnol ++ listMotsAllemand ++ listMotsItalien ++ List(
      "accompagnement",
      "femmes",
      "enceintes",
      "bréquigny",
      "piscine",
      "collège",
      "for"
    ) ++ getMap.map(_._1)).distinct.filter(_ != "ou")
}
