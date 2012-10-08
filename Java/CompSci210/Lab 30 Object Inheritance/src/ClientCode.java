
/**
 * Client code to interact with the GraphicsPoint class
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Nov 1, 2011 at 10:00:21 AM
 */
import java.awt.Color;
import java.awt.Graphics2D;

public class ClientCode {

  public static void main(String[] args) {
    DrawingPanel panel = new DrawingPanel(300, 300);
    panel.setBackground(Color.white);
    Graphics2D g = panel.getGraphics();
    GraphicsPoint p1 = new GraphicsPoint(14, 14);
    p1.setColor(Color.red);
    p1.setSize(5);
    System.out.println(p1.getColor());
    System.out.println(p1.getSize());
    System.out.println("-----");
    System.out.println(p1);
    p1.drawPoint(g);
    panel.copyGraphicsToScreen();
  }
}
