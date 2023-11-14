package client
import scala.swing._
import java.awt.Color
import java.awt.Font
import java.awt.font.FontRenderContext
import java.awt.geom.AffineTransform
import javax.swing.ImageIcon
import java.awt.Graphics2D
import java.awt.image.BufferedImage
import java.awt.geom.Ellipse2D

class MessageUtilisateur(message: String) extends FlowPanel {

  override val font = new Font("Arial", Font.PLAIN, 12)
  val fontRenderContext =
    new FontRenderContext(new AffineTransform(), true, true)
  val textWidth = 50

  val imageIcon = new ImageIcon("lib/uti.png")
  val originalImage = imageIcon.getImage()
  val image =
    originalImage.getScaledInstance(textWidth, 45, java.awt.Image.SCALE_SMOOTH)
  val scaledImageIcon = new ImageIcon(image)

  val mask = new Ellipse2D.Double(
    0,
    0,
    scaledImageIcon.getIconWidth(),
    scaledImageIcon.getIconHeight()
  )

  val maskedImage = new BufferedImage(
    mask.getWidth.toInt,
    mask.getHeight.toInt,
    BufferedImage.TYPE_INT_ARGB
  )
  val g = maskedImage.createGraphics()
  g.setClip(mask)
  g.drawImage(scaledImageIcon.getImage(), 0, 0, null)
  g.dispose()

  val imageLabel = new Label() {
    preferredSize =
      new Dimension(maskedImage.getWidth(), maskedImage.getHeight())
    icon = new ImageIcon(maskedImage)
    background = new Color(52, 133, 247)
    foreground = Color.WHITE
    border = Swing.EmptyBorder(0, 0, 0, 0)
    iconTextGap = 0
  }

  val textLabel = new Label(message) {
    opaque = true
    background = new Color(52, 133, 247)
    foreground = Color.WHITE
    border = Swing.EmptyBorder(5, 5, 5, 5)
  }

  contents += textLabel
  contents += imageLabel

}
