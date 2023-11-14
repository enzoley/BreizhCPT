package machine

// import com.sourcegraph.semanticdb_javac.Result
// import _root_.machine.machine.Resultat

object MachineImpl extends MachineDialogue {
  def ask(s: String): List[String] = {
    println(s)
    if (Resultat.isRechercheInitial) {
      val langueCourante: Langue = GererLangue.getLangueCourante(s)
      val languePrecedante: Langue = GererLangue.getLanguePrecedante
      if (GererLangue.getConfirme) {
        if (langueCourante.equals(languePrecedante))
          Resultat.reponseToString(AnalysePhrase.analyser(s), langueCourante)
        else
          GererLangue.demandeConfirmationChangerLangue(langueCourante)

      } else {
        if (langueCourante.equals(languePrecedante))
          GererLangue.peutConfirmer(s)
        else
          GererLangue.demandeConfirmationChangerLangue(langueCourante)
      }
    } else
      Resultat.choisirUnParmitPlusieur(s)
  }

  // Pour la partie test par le client
  def reinit(): Unit = {
    GererLangue.reinitLangue
    BaseDeDonnees.reinitBDD
    Resultat.reinitResultat
  }
  def test(l: List[String]): List[String] = {
    l match {
      case head :: next => ask(head) ++ test(next)
      case Nil          => Nil
    }
  }
}
