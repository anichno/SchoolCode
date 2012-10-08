
/**
 * Animates a moving line
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Dec 1, 2011 at 10:35:52 AM
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Problem9 {

  JFrame frame;
  static JPanel sketchPad;
  static Graphics2D g;

  public static void main(String[] args) throws InterruptedException {
    int Xpos = 50;
    boolean increasing = true;
    new Problem9();
    g = (Graphics2D) sketchPad.getGraphics();

    for (;;) {
      g.setColor(Color.white);
      g.fillRect(0, 0, 300, 300);
      g.setColor(Color.red);
      g.drawLine(Xpos, 0, 300 - Xpos, 260);

      if (increasing) {
        Xpos++;
      } else {
        Xpos--;
      }

      if (Xpos > 300 || Xpos < 0) {
        increasing = !increasing;
      }
      Thread.sleep(10);
    }
  }

  public Problem9() {
    frame = new JFrame();
    frame.setForeground(Color.WHITE);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocation(10, 50);
    frame.setSize(300, 300);
    frame.setTitle("Moving Line");
    frame.setLayout(new BorderLayout());

    sketchPad = new JPanel();
    frame.add(sketchPad, BorderLayout.CENTER);
    sketchPad.setBackground(Color.white);

    frame.setVisible(true);
  }
}
