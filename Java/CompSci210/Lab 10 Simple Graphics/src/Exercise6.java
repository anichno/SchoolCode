
/**
 * uses a method to draw multiple exercise 5 shapes
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Aug 31, 2011 at 11:13:04 AM
 */
import java.awt.*;

public class Exercise6 {

  public static void main(String[] args) {
    DrawingPanel panel = new DrawingPanel(400, 300);
    panel.setBackground(Color.CYAN);
    Graphics2D g = panel.getGraphics();
    panel.copyGraphicsToScreen();

    drawPattern(g, 50, 50);
    drawPattern(g, 250, 10);
    drawPattern(g, 180, 115);

    panel.copyGraphicsToScreen();
  }

  public static void drawPattern(Graphics g, int x, int y) {
    g.setColor(Color.RED);
    for (int counter = 0, rectsize = 20; counter < 5; counter++, rectsize += 20) {
      g.drawRect(x, y, rectsize, rectsize);
    }
    g.setColor(Color.BLACK);
    g.drawLine(x, y, x + 100, y + 100);
  }
}
