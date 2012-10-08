
/**
 * uses a method to draw multiple figures
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Aug 31, 2011 at 10:11:28 AM
 */
import java.awt.*;

public class Exercise2 {

  public static void main(String[] args) {
    DrawingPanel panel = new DrawingPanel(450, 150);
    panel.setBackground(Color.YELLOW);
    Graphics2D g = panel.getGraphics();
    drawFigure(g, 50, 25);
    drawFigure(g, 250, 45);
    panel.copyGraphicsToScreen();
  }

  public static void drawFigure(Graphics g, int x, int y) {
    g.setColor(Color.BLUE);
    g.fillOval(x, y, 40, 40);
    g.fillOval(x + 80, y, 40, 40);
    g.setColor(Color.RED);
    g.fillRect(x + 20, y + 20, 80, 80);
  }
}
