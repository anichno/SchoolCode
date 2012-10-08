
/**
 * Animates googly eyes
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Dec 1, 2011 at 9:59:50 AM
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Problem8 implements MouseMotionListener {

  JFrame frame;
  static JPanel sketchPad;
  static int mouseLocation = 110;
  static Graphics2D g;

  public static void main(String[] args) throws InterruptedException {
    new Problem8();
    g = (Graphics2D) sketchPad.getGraphics();
  }

  public Problem8() {
    frame = new JFrame();
    frame.setForeground(Color.WHITE);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocation(10, 50);
    frame.setSize(400, 300);
    frame.setTitle("The eyes have it");
    frame.setLayout(new BorderLayout());

    sketchPad = new JPanel();
    frame.add(sketchPad, BorderLayout.CENTER);
    sketchPad.setBackground(Color.gray);
    sketchPad.addMouseMotionListener(this);

    frame.setVisible(true);
  }

  @Override
  public void mouseDragged(MouseEvent e) {
  }

  @Override
  public void mouseMoved(MouseEvent e) {
    if (e.getY() <= 75) {
      mouseLocation = 75;
    } else if (e.getY() >= 150) {
      mouseLocation = 150;
    } else {
      mouseLocation = 110;
    }

    if (g != null) {
      drawEyes();
    }
  }

  public static void drawEyes() {
    g.setColor(Color.white);
    g.fillOval(100, 75, 100, 100);
    g.fillOval(200, 75, 100, 100);

    g.setColor(Color.BLACK);
    g.fillOval(140, mouseLocation, 25, 25);
    g.fillOval(240, mouseLocation, 25, 25);
  }
}
