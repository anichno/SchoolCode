
/**
 * <put a program description here>
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Sep 23, 2011 at 10:34:48 AM
 */
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Project4 {

  public static void main(String[] args) {
    try {
      Scanner user = new Scanner(System.in);
      Scanner input = new Scanner(new File("project4.txt"));


      System.out.println("This program allows you to search through the\n"
                         + "datat from the Social Security Administration\n"
                         + "to see how popular a particular name has been\n");
      System.out.print("Name: ");
      String name = user.next();

      // Create the window
      DrawingPanel panel = new DrawingPanel(500, 500);
      panel.setBackground(Color.white);
      Graphics2D g = panel.getGraphics();
      g.setColor(Color.black);

      String dataName = null;
      int prevPopularity = 0;
      int prevYear = 80;
      int curYear = 0;
      int curPopularity = 0;
      while (input.hasNext()) {
        dataName = input.next();
        if (dataName.equalsIgnoreCase(name)) {
          System.out.println("\nStatistics on name \"" + dataName + "\"");
          for (int year = 1900, popularity = 0; input.hasNextInt(); year += 10) {
            popularity = input.nextInt();
            curPopularity = (1000 - popularity) / 4;
            curYear = 3 * (year - 1900) + 80;

            System.out.printf("\t%d: %d\n", year, popularity);
            if (prevPopularity == 0) {
              prevYear = curYear;
              prevPopularity = curPopularity;
            }
            g.drawLine(prevYear, prevPopularity, curYear, curPopularity);
            g.fillOval((int) (curYear - 2.5), (int) (curPopularity - 2.5), 5, 5);
            panel.copyGraphicsToScreen();
            prevPopularity = curPopularity;
            prevYear = curYear;
          }
        }
      }

    } catch (IOException error) {
      System.out.println("Error in processing the file project4.txt" + error);
    }
  }
}
