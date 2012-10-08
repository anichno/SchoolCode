
import java.awt.Color;
import java.awt.Graphics;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author anichno
 */
public class TestClient {
    public static void main(String args[]) {
      int size = 700;
      DrawingPanel panel = new DrawingPanel(size,size);
      Graphics g = panel.getGraphics();
      panel.setBackground(Color.LIGHT_GRAY);
      ShannonSwitchingGraph test = new ShannonSwitchingGraph(size,size);
      test.shortWins();
      test.draw(g);
      panel.copyGraphicsToScreen();
      g.setColor(Color.magenta);
      boolean shortTurn = true;
      for(;!test.shortWins() && !test.cutWins();) {
        if (shortTurn) {
          System.out.println("Short's turn");
        }
        else {
          System.out.println("Cut's turn");
        }

        panel.setBackground(Color.LIGHT_GRAY);
        
        g.fillOval(panel.getMouseX(), panel.getMouseY(), 5, 5);
        if (shortTurn) {
//          test.lockEdgeAt(panel.getMouseX(),panel.getMouseY());
          test.makeShortMove();
        }
        else {
           while (!panel.mouseClickHasOccurred(DrawingPanel.LEFT_BUTTON)) {
          panel.sleep(10);
        }
//          panel.waitForMouseClick(panel.LEFT_BUTTON);
          test.cutEdgeAt(panel.getMouseX(), panel.getMouseY());
//         test.makeCutMove();
        }
        shortTurn = !shortTurn;
        System.out.println("Short won: " + test.shortWins());
        System.out.println("Cut won: " + test.cutWins());
        
        test.draw(g);
        panel.copyGraphicsToScreen();
        panel.sleep(500);
      }
    }
    
}
