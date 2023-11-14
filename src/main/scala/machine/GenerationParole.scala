package machine

import javax.sound.sampled.AudioInputStream;
import marytts.LocalMaryInterface;
import marytts.MaryInterface;
import marytts.exceptions.MaryConfigurationException;
import marytts.exceptions.SynthesisException;
import marytts.util.data.audio.AudioPlayer;
import scala.concurrent.ExecutionContext
import javax.sound.sampled.AudioSystem

trait TraitGenerationParole {

  var voiceName: String = "upmc-pierre-hsmm"
  val ap: AudioPlayer = new AudioPlayer()
  val marytts: MaryInterface = {
    val m = new LocalMaryInterface()
    m.setVoice(voiceName)
    m
  }

  /** @param mots
    * @return
    *   une liste de reponse
    */
  def generation(phrase: String)(
      execution: ExecutionContext
  ): Unit = {
    val audioStream = marytts.generateAudio(phrase)
    execution.execute(new AudioPlayer(audioStream))
  }
}

class GenerationParole extends TraitGenerationParole {

  private def defLangue(langue: Langue) = langue match {
    case Anglais  => "cmu-slt-hsmm"
    case Allemand => "dfki-pavoque-neutral-hsmm"
    case Italien  => "istc-lucia-hsmm"
    case Francais => "upmc-pierre-hsmm"
    case Espagnol => ""

  }

  def generationParole(phrase: String) {
    var langue: Langue = GererLangue.getLangueCourante(phrase)
    if (!(defLangue(langue).length() == 0)) {
      changeLangue(langue)
      generation(phrase)
    }
  }

  def changeLangue(langue: Langue): Unit = {
    println("ChangeLangue " + langue)
    voiceName = defLangue(langue)
    marytts.setVoice(voiceName)
    println("Fini")
  }

  def generation(phrase: String): Unit = {
    println("Generation " + phrase)
    say(phrase)
    println("Fini")
  }

  def say(input: String): Unit = {
    println("Say " + input)
    try {
      println("Try")
      println(voiceName)
      val audio: AudioInputStream = marytts.generateAudio(input);
      ap.setAudio(audio)
      ap.run()
      ap.cancel()
    } catch {
      case e: Throwable => println(e)
    }
    println("Fini")
  }
}
