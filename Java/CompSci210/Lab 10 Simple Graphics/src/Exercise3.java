
/**
 * draws multiple concentric boxes
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Aug 31, 2011 at 10:18:19 AM
 */
import java.awt.*;

public class Exercise3 {

  public static void main(String[] args) {
    DrawingPanel panel = new DrawingPanel(200, 200);
    panel.setBackground(Color.WHITE);
    Graphics2D g = panel.getGraphics();
    panel.copyGraphicsToScreen();

    g.setColor(Color.BLACK);
    showDesign(g);
    panel.copyGraphicsToScreen();
  }

  public static void showDesign(Graphics g) {
    for (int counter = 0, position = 20, size = 160; counter < 4; counter++, position += 20, size -= 40) {
      g.drawRect(position, position, size, size);
    }
  }
}
