
/**
 * Calculate the perimeter of a triangle
 * 
 * @author Wayne.Brown, fixed by Anthony Canino
 * 
 * @version 1.0 - Aug 23, 2011 at 10:19:50 AM
 */
public class CalculateDistance {

  public static void main(String[] args) {
    double x1 = 0.0;
    double y1 = 0.0;
    double x2 = 1.0;
    double y2 = 0.0;
    double x3 = 0.0;
    double y3 = 1.0;

    double perimeter = distance(x1, y1, x2, y2)
                       + distance(x2, y2, x3, y3)
                       + distance(x3, y3, x1, y1);
    // Answer should be 3.414213562373095
    System.out.println("Perimeter of triangle = " + perimeter);

    x1 = 3.5;
    y1 = 12.5;
    x2 = 6.0;
    y2 = 37.2;
    x3 = -3.4;
    y3 = 12.7;

    // Answer should be 57.970473321371195
    System.out.println("Perimeter of triangle = " + (distance(x1, y1, x2, y2)
                                                     + distance(x2, y2, x3, y3)
                                                     + distance(x3, y3, x1, y1)));

    x1 = 4.5;
    y1 = 14;
    x2 = 3.0;
    y2 = 34;
    x3 = -6.4;
    y3 = 28.7;

    // Answer should be 49.14764446075084
    System.out.println("Perimeter of triangle = " + (distance(x1, y1, x2, y2)
                                                     + distance(x2, y2, x3, y3)
                                                     + distance(x3, y3, x1, y1)));
  }

  public static double distance(double x1, double y1, double x2, double y2) {
    double distance = 0.0;

    distance = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
    return distance;
  }
}
