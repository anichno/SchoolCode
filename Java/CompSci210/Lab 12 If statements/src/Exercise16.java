
/**
 * Returns the quadrant of the point or zero if it is on an axis
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Sep 7, 2011 at 10:16:17 AM
 */
public class Exercise16 {

  public static void main(String[] args) {
    System.out.println(quadrant(5, 3));
    System.out.println(quadrant(-5, 3));
    System.out.println(quadrant(-5, -5));
    System.out.println(quadrant(5, -4));
    System.out.println(quadrant(5, 0));
  }

  public static int quadrant(int X, int Y) {
    if (X > 0 && Y > 0) {
      return 1;
    } else if (X < 0 && Y > 0) {
      return 2;
    } else if (X < 0 && Y < 0) {
      return 3;
    } else if (X > 0 && Y < 0) {
      return 4;
    } else {
      return 0;
    }
  }
}
