
/**
 * draws two ovals and a box
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Aug 31, 2011 at 9:52:56 AM
 */
import java.awt.*;

public class Exercise1 {

  public static void main(String[] args) {
    DrawingPanel panel = new DrawingPanel(220, 150);
    panel.setBackground(Color.YELLOW);
    Graphics2D g = panel.getGraphics();
    panel.copyGraphicsToScreen();

    g.setColor(Color.BLUE);
    g.fillOval(50, 25, 40, 40);
    g.fillOval(130, 25, 40, 40);
    g.setColor(Color.RED);
    g.fillRect(70, 45, 80, 80);
    panel.copyGraphicsToScreen();
  }
}
