
/**
 * Draws multiple stamps of varying opacity
 * 
 * @author Anthony.Canino
 * 
 * @version 1.0 - Sep 1, 2011 at 8:25:06 PM
 */
import java.awt.*;

public class Project2 {

  public static void main(String[] args) {
    DrawingPanel panel = new DrawingPanel(200, 200);
    panel.setBackground(Color.white);
    Graphics2D g = panel.getGraphics();
    panel.copyGraphicsToScreen();

    //Draw row 1
    drawEyes(g, 10, 10, 25);
    drawCoolRectangles(g, 60, 10, 50);
    drawJava(g, 110, 10, 255);

    //Draw row 2
    drawCoolRectangles(g, 10, 60, 255);
    drawJava(g, 60, 60, 25);
    drawEyes(g, 110, 60, 50);

    //Draw row 3
    drawJava(g, 10, 110, 50);
    drawEyes(g, 60, 110, 255);
    drawCoolRectangles(g, 110, 110, 25);

    panel.copyGraphicsToScreen();
  }

  public static void drawEyes(Graphics g, int X, int Y, int fade) {
    g.setColor(Color.black);
    g.drawRect(X, Y, 50, 50);
    g.setColor(new Color(0, 0, 0, fade));
    g.drawOval(X + 5, Y + 5, 15, 40);
    g.drawOval(X + 30, Y + 5, 15, 40);
    g.fillOval(X + 5, Y + 25, 15, 15);
    g.fillOval(X + 30, Y + 25, 15, 15);
  }

  public static void drawCoolRectangles(Graphics g, int X, int Y, int fade) {
    g.setColor(Color.black);
    g.drawRect(X, Y, 50, 50);
    g.setColor(new Color(0, 0, 0, fade));
    for (int counter = 0, pos = 0, ysize = 45; counter < 5; counter++, pos += 5, ysize -= 10) {
      g.drawRect(X + pos, Y + pos, 5, ysize);
    }
    for (int counter = 0, pos = 0, xsize = 45; counter < 5; counter++, pos += 5, xsize -= 10) {
      g.drawRect(X + 5 + pos, Y + pos, xsize, 5);
    }
    for (int counter = 0, pos = 0, xsize = 45; counter < 5; counter++, pos -= 5, xsize -= 10) {
      g.drawRect(X + (-1 * pos), Y + 45 + pos, xsize, 5);
    }
    for (int counter = 0, pos = 0, ysize = 45; counter < 5; counter++, pos -= 5, ysize -= 10) {
      g.drawRect(X + 45 + pos, Y + 5 + (-1 * pos), 5, ysize);
    }
  }

  public static void drawJava(Graphics g, int X, int Y, int fade) {
    g.setColor(Color.black);
    g.drawRect(X, Y, 50, 50);
    g.setColor(new Color(0, 0, 0, fade));
    g.drawString("I", X + 25, Y + 15);
    g.drawString("Love", X + 12, Y + 30);
    g.drawString("Java", X + 12, Y + 45);
  }
}
