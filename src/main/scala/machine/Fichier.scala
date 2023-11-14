package machine

import java.io.File
import scala.io.Source
import scala.xml.XML
import scala.xml.Elem

trait TraitFichier {

  /** Obtient les données initiales
    *
    * @return
    */
  def extraireDonneesInitiales(): List[(String, String)]

  /** Obtient les données
    *
    * @return
    *   les données
    */
  def extraireDonnees(): List[(String, String)]
}

object Fichier extends TraitFichier {

  /** Obtient le chemin absolue a partir du chemin relatif
    *
    * @param cheminRelatif
    * @return
    *   le chemin absolue
    */
  private def getCheminAbsolu(cheminRelatif: String): String = {
    val fichier = new File(cheminRelatif)
    if (fichier.exists()) {
      fichier.getAbsolutePath()
    } else {
      throw new IllegalArgumentException(
        s"Le fichier $cheminRelatif n'existe pas."
      )
    }
  }

  /** Obtient les données d'un fichier .csv
    *
    * @param cheminRelatif
    * @param separateur
    * @return
    *   les données du fichier .csv
    */
  private def extraireCSV(
      cheminRelatif: String,
      separateur: Char
  ): List[(String, String)] = {
    val fichier = new File(cheminRelatif)
    if (!fichier.exists() || !fichier.isFile()) {
      throw new IllegalArgumentException(
        s"Le fichier $cheminRelatif n'existe pas."
      )
    } else {
      val lignes = Source.fromFile(fichier).getLines().toList
      lignes.map(ligne =>
        (
          ligne.split(separateur).map(_.trim) match {
            case Array(cle, valeur) => (cle, valeur)
          }
        )
      )
    }
  }

  /** Obtient les données du fichier DonneesInitiales
    *
    * @return
    *   les données du fichier DonneesInitiales
    */
  def extraireDonneesInitiales(): List[(String, String)] = {
    val cheminRelatif: String = "doc/DonneesInitiales.txt"
    val separateur: Char = ';'
    extraireCSV(cheminRelatif, separateur)
  }

  /** Obtient les données du fichier vAr.xml
    *
    * @return
    *   les données du fichier vAr.xml
    */
  private def extraireDonneesXML(): List[(String, String)] =
    aux_extraireDonneesXML.filter(c => c._1.length > 0 && c._2.length > 0)

  /** Obtient les données du fichier vAr.xml
    *
    * @return
    *   les données du fichier vAr.xml
    */
  private def aux_extraireDonneesXML(): List[(String, String)] = {
    val cheminRelatif: String = "doc/vAr.xml"
    val fichier = new File(cheminRelatif)
    if (!fichier.exists() || !fichier.isFile()) {
      throw new IllegalArgumentException(
        s"Le fichier $cheminRelatif n'existe pas."
      )
    } else {
      val xml: Elem = XML.loadFile(cheminRelatif)
      (xml \\ "opendata" \\ "answer" \\ "data" \\ "organization")
        .map(organization => {
          val nombreRue: String =
            (organization \\ "addresses" \\ "address" \\ "address" \\ "street" \ "number").text
          val nomRue: String =
            (organization \\ "addresses" \\ "address" \\ "address" \\ "street" \ "name").text
          val city: String =
            (organization \\ "addresses" \\ "address" \\ "address" \ "city").text
          val adresse: String =
            if (nombreRue.length() > 0) nombreRue + ", " + nomRue else nomRue
          val nomOrganozation: String = (organization \ "name").text
          if (
            adresse.length > 0 && city.equals("Rennes")
            && nomOrganozation.length > 0
            && !adresse.contains("?")
          ) {
            (nomOrganozation, adresse)
          } else ("", "")
        })
        .toList
    }
  }

  def extraireDonnees(): List[(String, String)] = extraireDonneesXML
}
