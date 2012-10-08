
/**
 * same as exercise 6 but scales the figures
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Aug 31, 2011 at 11:17:09 AM
 */
import java.awt.*;

public class Exercise7 {

  public static void main(String[] args) {
    DrawingPanel panel = new DrawingPanel(400, 300);
    panel.setBackground(Color.CYAN);
    Graphics2D g = panel.getGraphics();
    panel.copyGraphicsToScreen();

    drawPattern(g, 50, 50, 100);
    drawPattern(g, 250, 10, 50);
    drawPattern(g, 180, 115, 180);

    panel.copyGraphicsToScreen();
  }

  public static void drawPattern(Graphics g, int x, int y, int size) {
    g.setColor(Color.RED);
    for (int counter = 0, rectsize = size / 5; counter < 5; counter++, rectsize += size / 5) {
      g.drawRect(x, y, rectsize, rectsize);
    }
    g.setColor(Color.BLACK);
    g.drawLine(x, y, x + size, y + size);
  }
}
