
/**
 * draws boxes with a diagonal line
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Aug 31, 2011 at 11:05:28 AM
 */
import java.awt.*;

public class Exercise5 {

  public static void main(String[] args) {
    DrawingPanel panel = new DrawingPanel(300, 200);
    panel.setBackground(Color.CYAN);
    Graphics2D g = panel.getGraphics();
    panel.copyGraphicsToScreen();

    g.setColor(Color.RED);
    for (int counter = 0, sizeX = 20, sizeY = 20; counter < 5; counter++, sizeX += 20, sizeY += 20) {
      g.drawRect(50, 50, sizeX, sizeY);
    }
    g.setColor(Color.BLACK);
    g.drawLine(50, 50, 150, 150);

    panel.copyGraphicsToScreen();
  }
}
