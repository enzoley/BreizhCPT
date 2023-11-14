package client

import scala.swing._
import scala.swing.event._
import machine.MachineImpl
import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import javax.swing.SwingUtilities
import javax.swing.ImageIcon
import machine.GenerationParole

object BreizhCPT extends SimpleSwingApplication {

  def top = new MainFrame {
    title = "BreizhCPT"
    preferredSize = new Dimension(2000, 2000)

    val chatPanel = new BoxPanel(Orientation.Vertical)

    val inputField = new TextField {
      columns = 30
      listenTo(keys)
      reactions += { case KeyPressed(_, Key.Enter, _, _) =>
        val message = text.trim
        if (message.nonEmpty) {
          addSentMessage(message)
        }
        text = ""
      }
    }

    val scrollPane =
      new ScrollPane(chatPanel)

    contents = new BorderPanel {
      layout(scrollPane) = BorderPanel.Position.Center
      layout(inputField) = BorderPanel.Position.South
    }

    val clearPannel = new BoxPanel(Orientation.Horizontal) {
      preferredSize = new Dimension(300, 670)
    }

    val imageIcon = new ImageIcon("lib/Breizh.png")
    val preferredWidth = 1100
    val preferredHeight = clearPannel.preferredSize.height
    val scaledImage = imageIcon
      .getImage()
      .getScaledInstance(
        preferredWidth,
        preferredHeight,
        java.awt.Image.SCALE_SMOOTH
      )
    val imageLabel = new Label() {
      icon = new ImageIcon(scaledImage)
      horizontalAlignment = Alignment.Center
    }
    clearPannel.contents += imageLabel
    MachineImpl.reinit()
    chatPanel.contents += clearPannel
    addReceivedMessage(
      "Salut, je suis KerraBot, un robot ultra performant. Que puis-je faire pour t'aider ?"
    )

    def addReceivedMessage(message: String): Unit = {
      val messagePanel = new BoxPanel(Orientation.Horizontal)
      messagePanel.contents += new MessageIA("KerraBot : " + message)
      messagePanel.contents += Swing.HGlue
      chatPanel.contents += messagePanel
      chatPanel.revalidate()
      chatPanel.repaint()
      SwingUtilities.invokeLater(new Runnable() {
        override def run(): Unit = {
          scrollPane.verticalScrollBar.value =
            scrollPane.verticalScrollBar.maximum
        }
      })
      val parole = new GenerationParole()
      parole.generationParole(message)
    }

    def addSentMessage(message: String): Unit = {
      inputField.editable = false
      val messagePanel = new BoxPanel(Orientation.Horizontal)
      messagePanel.contents += Swing.HGlue
      messagePanel.contents += new MessageUtilisateur("Vous : " + message)
      chatPanel.contents += messagePanel
      chatPanel.revalidate()
      chatPanel.repaint()
      SwingUtilities.invokeLater(new Runnable() {
        override def run(): Unit = {
          scrollPane.verticalScrollBar.value =
            scrollPane.verticalScrollBar.maximum
        }
      })
      Future {
        Thread.sleep(500)
        val intermediaire = MachineImpl.ask(message)
        for (s <- intermediaire) {
          addReceivedMessage(s)
        }
        inputField.editable = true
      }
    }
  }

}
