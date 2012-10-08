//
// GR 1: Prolblem 5 Part A          Limit your time to approximately 15 minutes
//
// Grade: _____ / 21 
//
// GR 1: Prolblem 6 Part B          Limit your time to approximately 15 minutes
//
// Grade: _____ / 21 
//
import java.awt.*;

public class Prob5_Part_A_and_Part_B {

  public static void main(String[] args) {
    DrawingPanel panel = new DrawingPanel(400, 400);
    panel.setBackground(Color.WHITE);
    Graphics2D g = panel.getGraphics();
    panel.copyGraphicsToScreen();

//    drawSplitRect(g, 30,100, 50,50);
//    drawSplitRect(g, 150,25, 100,100);
//    drawSplitRect(g, 50,200, 300,10);
//    drawSplitRect(g, 100,100,10,250);

    for (int counter = 0, X = 0; counter < 20; counter++, X += 20) {
      drawSplitRect(g, 10, 50, X, 200);
    }
    panel.copyGraphicsToScreen();
  }

  public static void drawSplitRect(Graphics2D g, int width, int height, int X, int Y) {
    g.setColor(Color.cyan);
    g.fillRect(X, Y, width, height);
    g.setColor(Color.red);
    if (width > height) {
      int lineX = X + width / 2;
      g.drawLine(lineX, Y, lineX, Y + height);
    } else {
      int lineY = Y + height / 2;
      g.drawLine(X, lineY, X + width, lineY);
    }
  }
}
