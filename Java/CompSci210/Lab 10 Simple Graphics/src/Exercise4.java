
/**
 * same as exercise 3 but with console input and scaling
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Aug 31, 2011 at 10:24:52 AM
 */
import java.awt.*;
import java.util.Scanner;

public class Exercise4 {

  public static void main(String[] args) {
    Scanner user = new Scanner(System.in);
    System.out.print("What is the width of the window? ");
    int sizeX = user.nextInt();
    System.out.print("What is the height of the window? ");
    int sizeY = user.nextInt();
    DrawingPanel panel = new DrawingPanel(sizeX, sizeY);
    panel.setBackground(Color.WHITE);
    Graphics2D g = panel.getGraphics();
    panel.copyGraphicsToScreen();

    g.setColor(Color.BLACK);
    showDesign(g, sizeX, sizeY);
    panel.copyGraphicsToScreen();
  }

  public static void showDesign(Graphics g, int sizeX, int sizeY) {
    int positioner = 0;
    int sizer = Math.min(sizeX, sizeY);
    positioner = Math.min(sizeX, sizeY);
    for (int counter = 0, position = positioner / 10, widthsize = sizeX - 2 * (sizer / 10),
            heightsize = sizeY - 2 * (sizer / 10); counter < 4; counter++, position = position + positioner / 10, widthsize = widthsize - 2 * (sizer / 10), heightsize = heightsize - 2 * (sizer / 10)) {
      g.drawRect(position, position, widthsize, heightsize);
    }
  }
}
