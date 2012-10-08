
import java.awt.Color;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import pex4guibuilder.GameCanvas;


/**
 * <put a program description here>
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Nov 14, 2011 at 11:10:32 AM
 */
public class test {
  public static JFrame frame;

  public static void main(String[] args) {
    frame = new JFrame();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 400);
    GameCanvas canvas = new GameCanvas();
    canvas.setSize(400, 400);
    canvas.setupGameCanvas();
    frame.add(canvas);
    //canvas.setBackground(Color.blue);
    
    Graphics2D fun = canvas.getGraphics();
//    fun.setColor(Color.red);
//    fun.fillOval(50, 50, 50, 50);
    canvas.paint(fun);
    canvas.saveGraphics("test.jpg");
    frame.setVisible(true);
  }
}
