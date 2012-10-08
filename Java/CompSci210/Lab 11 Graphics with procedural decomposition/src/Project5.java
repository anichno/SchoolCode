
/**
 * Draws the "Cafe Wall" illusion
 * 
 * @author Anthony.Canino
 * 
 * @version 1.0 - Sep 1, 2011 at 9:21:32 PM
 */
import java.awt.*;

public class Project5 {

  public static void main(String[] args) {
    DrawingPanel panel = new DrawingPanel(650, 400);
    panel.setBackground(Color.CYAN);
    Graphics2D g = panel.getGraphics();
    panel.copyGraphicsToScreen();

    // Rows
    drawIllusion(g, 0, 0, 1, 4, 0, 20);
    drawIllusion(g, 50, 70, 1, 5, 0, 30);

    // Grids
    drawIllusion(g, 10, 150, 8, 4, 0, 25);
    drawIllusion(g, 250, 200, 6, 3, 10, 25);
    drawIllusion(g, 425, 180, 10, 5, 10, 20);
    drawIllusion(g, 400, 20, 4, 2, 35, 35);

    panel.copyGraphicsToScreen();
  }

  public static void drawIllusion(Graphics g, int X, int Y, int numReps, int numPairs, int offset, int size) {
    for (int counter = 0, ypos = Y, boxOffset = 0; counter < numReps; counter++, ypos += size + 1) {
      for (int counter2 = 0, xpos = X + boxOffset; counter2 < numPairs; counter2++, xpos += size * 2) {
        g.setColor(Color.black);
        g.fillRect(xpos, ypos, size, size);
        g.setColor(Color.gray);
        g.drawLine(xpos, ypos, xpos + size, ypos + size);
        g.drawLine(xpos + size, ypos, xpos, ypos + size);
        g.setColor(Color.white);
        g.fillRect(xpos + size, ypos, size, size);
      }
      if (boxOffset == offset) {
        boxOffset = 0;
      } else {
        boxOffset = offset;
      }
    }
  }
}
