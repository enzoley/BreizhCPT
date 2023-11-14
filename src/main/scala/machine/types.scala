package machine

sealed trait Reponse {
  def canEqual(other: Any): Boolean = other.isInstanceOf[Reponse]

  override def equals(other: Any): Boolean = other match {
    case that: Reponse =>
      (that canEqual this) &&
      this.toString == that.toString
    case _ => false
  }

  override def hashCode(): Int = toString.hashCode
}
case class Contenu(nom: String, adresse: String) extends Reponse
case object Erreur extends Reponse
case object Bonjour extends Reponse
sealed trait Langue {
  def canEqual(other: Any): Boolean = other.isInstanceOf[Langue]

  override def equals(other: Any): Boolean = other match {
    case that: Langue =>
      (that canEqual this) &&
      this.toString == that.toString
    case _ => false
  }

  override def hashCode(): Int = toString.hashCode
}
case object Francais extends Langue
case object Anglais extends Langue
case object Espagnol extends Langue
case object Allemand extends Langue
case object Italien extends Langue
