
/**
 * Description: Draws a die and counts how many times each side shows
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Sep 7, 2011 at 11:50:48 AM
 */
import java.awt.*;

public class RollDice {

  public static void main(String[] args) {
    int dieValue = 0;
    int[] dieNums = new int[]{0, 0, 0, 0, 0, 0};
    DrawingPanel panel = new DrawingPanel(400, 400);

    panel.setBackground(Color.white);

    Graphics2D g = panel.getGraphics();

    panel.setBackground(Color.white);

    for (;;) {
      panel.setBackground(Color.white);
      dieValue = drawDie(g, 100, 100, 100);
      dieNums[dieValue - 1]++;
      for (int counter = 0; counter < 6; counter++) {
        g.drawString(counter + 1 + " has happened " + dieNums[counter] + " times.", 10, 250 + counter * 20);
      }
      panel.copyGraphicsToScreen();
      panel.sleep(500);
    }
  }

  // ------------------------------------------------------------------
  public static int drawDie(Graphics2D g, int x, int y, int rectangleSize) {
    int spacing = rectangleSize / 14;
    int dotSize = spacing * 2;
    int dieValue = (int) (Math.random() * 6 + 1);

    // Draw the die outline
    g.setColor(Color.WHITE);
    g.fillRect(x, y, rectangleSize, rectangleSize);
    g.setColor(Color.BLACK);
    g.drawRect(x, y, rectangleSize, rectangleSize);

    // Draw the die dots
    if (dieValue == 1 || dieValue == 3 || dieValue == 5) {
      // center
      g.fillOval(x + spacing * 6, y + spacing * 6, dotSize, dotSize);
    }

    if (dieValue >= 2) {
      // upper left corner
      g.fillOval(x + spacing * 2, y + spacing * 2, dotSize, dotSize);
      // lower right corner
      g.fillOval(x + spacing * 10, y + spacing * 10, dotSize, dotSize);
    }

    if (dieValue >= 4) {
      // upper right corner
      g.fillOval(x + spacing * 10, y + spacing * 2, dotSize, dotSize);
      // lower left corner
      g.fillOval(x + spacing * 2, y + spacing * 10, dotSize, dotSize);
    }

    if (dieValue == 6) {
      // upper center
      g.fillOval(x + spacing * 6, y + spacing * 2, dotSize, dotSize);
      // lower center
      g.fillOval(x + spacing * 6, y + spacing * 10, dotSize, dotSize);
    }
    return dieValue;
  }
}
