
/**
 * GR 2: Problem 6          Limit your time to approximately 15 minutes
 *
 * Grade:           / 21
 */
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

public class Prob6 {

  public static void main(String[] args) {
    final int WINDOW_SIZE = 230;

    DrawingPanel panel = new DrawingPanel(WINDOW_SIZE, WINDOW_SIZE);
    Graphics2D g = panel.getGraphics();
    Point dot = new Point(WINDOW_SIZE / 2, WINDOW_SIZE / 2);
    g.setColor(Color.white);

    while (dot.getX() >= 0 && dot.getX() <= WINDOW_SIZE
           && dot.getY() >= 0 && dot.getY() <= WINDOW_SIZE) {
      g.fillOval((int) dot.getX(), (int) dot.getY(), 2, 2);

      //  set new X location
      if ((int) (Math.random() * 2 + 1) % 2 == 0) {
        dot.setLocation(dot.getX() + (int) (Math.random() * 5 + 1), dot.getY());
      } else {
        dot.setLocation(dot.getX() + -1 * (int) (Math.random() * 5 + 1), dot.getY());
      }

      //  set new Y location
      if ((int) (Math.random() * 2 + 1) % 2 == 0) {
        dot.setLocation(dot.getX(), dot.getY() + (int) (Math.random() * 5 + 1));
      } else {
        dot.setLocation(dot.getX(), dot.getY() + -1 * (int) (Math.random() * 5 + 1));
      }

      panel.copyGraphicsToScreen();
    }
  }
}
