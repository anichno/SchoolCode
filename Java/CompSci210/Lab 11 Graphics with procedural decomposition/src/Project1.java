
/**
 * Draws targets of varying size and repetitions
 * 
 * @author Anthony.Canino
 * 
 * @version 1.0 - Sep 1, 2011 at 7:15:34 PM
 */
import java.awt.*;

public class Project1 {

  public static void main(String[] args) {
    DrawingPanel panel = new DrawingPanel(400, 400);
    panel.setBackground(Color.CYAN);
    Graphics2D g = panel.getGraphics();
    panel.copyGraphicsToScreen();

    drawPattern(g, 0, 0, 1, 5, 100);
    drawPattern(g, 10, 120, 5, 4, 24);
    drawPattern(g, 150, 20, 6, 5, 40);
    drawPattern(g, 130, 275, 3, 3, 36);

    panel.copyGraphicsToScreen();
  }

  public static void drawPattern(Graphics g, int X, int Y, int num, int numCircles, int scale) {
    
    // Draw row
    for (int row = 0, ypos = Y; row < num; row++, ypos += scale) {
      
      // Draw boxes in row
      for (int counter = 0, xpos = X; counter < num; counter++, xpos += scale) {
        // Draw green box
        g.setColor(Color.green);
        g.fillRect(xpos, ypos, scale, scale);
        
        // Draw box outline
        g.setColor(Color.black);
        g.drawRect(xpos, ypos, scale, scale);
        
        // Draw yellow circle
        g.setColor(Color.yellow);
        g.fillOval(xpos , ypos, scale, scale);
        
        // Draw concentric circle
        g.setColor(Color.black);
        for (int counter2 = 0, scaleCircle = scale, positioner = 0; counter2 < numCircles; counter2++, scaleCircle -= (scale / numCircles), positioner += scale / (numCircles * 2)) {
          g.drawOval(xpos + positioner, ypos + positioner, scaleCircle, scaleCircle);
        }
        
        // Draw cross
        g.drawLine(xpos + scale / 2, ypos, xpos + scale / 2, ypos + scale);
        g.drawLine(xpos, ypos + scale / 2, xpos + scale, ypos + scale / 2);
      }
    }
  }
}
