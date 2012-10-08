
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

    GraphicsPoint p2 = new GraphicsPoint();
    p2.setSize(5);
    p2.drawPoint(g);

    GraphicsPoint p3 = new GraphicsPoint(20, 20, 5, Color.green);
    p3.drawPoint(g);

    GraphicsPoint p4 = new GraphicsPoint(p3);
    System.out.println("p3 is equal to p4: " + p3.equals(p4) + "\n");
    p4.setXY(100, 100);
    p4.drawPoint(g);

    GraphicsPoint p5 = new GraphicsPoint(50, 50, 1, Color.blue);
    p5.translate(10, 10);
    System.out.println("-----");
    System.out.println(p5);
    p5.drawPoint(g);

    panel.copyGraphicsToScreen();
  }
}
