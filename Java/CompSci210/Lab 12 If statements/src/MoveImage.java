
/**
 * Draws a flower whose position can be controlled by the arrow keys
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Sep 2, 2011 at 1:46:55 PM
 */
import java.awt.*;
import java.awt.image.BufferedImage;

public class MoveImage {

  public static void main(String[] args) throws InterruptedException {

    int imageX = 100;
    int imageY = 100;

    // Create the window
    DrawingPanel panel = new DrawingPanel(400, 400);
    panel.setBackground(Color.white);
    Graphics2D g = panel.getGraphics();

    // Read the image into memory
    BufferedImage image = panel.loadBitmap("Flower.jpg");
    g.setColor(Color.white);
    for (;;) {
      g.fillRect(0, 0, 400, 400);

      if (panel.keyIsDown(DrawingPanel.DOWN_ARROW_KEY)) {
        imageY += 1;
      } else {
        if (panel.keyIsDown(DrawingPanel.UP_ARROW_KEY)) {
          imageY -= 1;
        } else {
          if (panel.keyIsDown(DrawingPanel.LEFT_ARROW_KEY)) {
            imageX -= 1;
          } else {
            if (panel.keyIsDown(DrawingPanel.RIGHT_ARROW_KEY)) {
              imageX += 1;
            }
          }
        }
      }
      g.drawImage(image, imageX, imageY, null);
      panel.copyGraphicsToScreen();
      Thread.sleep(10);
    }
  }
}
