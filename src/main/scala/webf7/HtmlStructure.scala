package webf7

/** Les documents HTML, vus comme des objets de type Html sont structurés de la
  * façon suivante: Un noeud HTML de la forme: <NomTag att1="val1" att2="val2">
  * n1 n2 ... </NomTag>, sera représenté par un objet
  * Tag("NomTag",List(("att1","val1"),("att2","val2")),List(n1,n2,...))
  *
  * Un élément de texte simple sera représenté par un objet Text(s) où s est une
  * chaîne de caractères
  */

sealed trait Html
case class Tag(
    name: String,
    attributes: List[(String, String)],
    children: List[Html]
) extends Html
case class Texte(content: String) extends Html
