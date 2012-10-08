
/**
 * sketchpad for drawing and stuff
 *
 * @author C14Anthony.Canino
 *
 * @version 1.0   Fall 2008
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SimpleFrame implements MouseListener, KeyListener {

  JFrame frame;
  static JPanel sketchPad;
  static Point p1;
  static Point p2;

  public SimpleFrame() {
    frame = new JFrame();
    frame.setForeground(Color.WHITE);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setLocation(10, 50);
    frame.setSize(300, 300);
    frame.setTitle("A frame");
    frame.setLayout(new BorderLayout());
    frame.addKeyListener(this);

    sketchPad = new JPanel();
    frame.add(sketchPad, BorderLayout.CENTER);
    sketchPad.setBackground(Color.blue);
    sketchPad.addMouseListener(this);

    frame.setVisible(true);
  }

  //-------------------------------------------------------------------
  // Client code
  public static void main(String[] args) {
    new SimpleFrame();
    Graphics2D g = (Graphics2D) sketchPad.getGraphics();
    g.setColor(Color.red);
    for (;;) {
      if (p1 != null && p2 != null) {
        g.drawLine((int) p1.getX(),
                   (int) p1.getY(),
                   (int) p2.getX(),
                   (int) p2.getY());
        p1 = null;
        p2 = null;
      }
    }
  }

  @Override
  public void mouseClicked(MouseEvent e) {
    //System.out.println("Mouse Clicked");
  }

  @Override
  public void mousePressed(MouseEvent e) {
    //System.out.println("Mouse Pressed");
    p1 = e.getPoint();
  }

  @Override
  public void mouseReleased(MouseEvent e) {
    //System.out.println("Mouse Released");
    p2 = e.getPoint();
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    //System.out.println("Mouse Entered");
  }

  @Override
  public void mouseExited(MouseEvent e) {
    //System.out.println("Mouse Exited");
  }

  //----------------------
  //  Key Listener
  //----------------------
  @Override
  public void keyTyped(KeyEvent e) {
    //System.out.println(e.getKeyChar() + " was typed");
  }

  @Override
  public void keyPressed(KeyEvent e) {
    //System.out.println("Key Pressed");
  }

  @Override
  public void keyReleased(KeyEvent e) {
    //System.out.println("Key Released");
  }
}
