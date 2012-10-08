
/**
 * Find the maximum root of a quadratic equation.
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Aug 19, 2011 at 12:58:01 PM
 */
public class Quadratic {

  public static void main(String[] args) {
    System.out.println("The largest root of x^2 - 7x + 12 = " + quadratic(1, -7, 12));
    System.out.println("The largest root of x^2 + 3x + 2  = " + quadratic(1, 3, 2));
  }

  //-------------------------------------------------------------------
  // Preconditions: doesn't divide by zero, determinate before taking square root >= 0
  // Postconditions:
  public static double quadratic(double a, double b, double c) throws ArithmeticException {
    if (a == 0) {
      throw new ArithmeticException("Don't divide by zero!");
    }
    if ((b * b - 4 * a * c) < 0) {
      throw new ArithmeticException("Don't take the square root of a negative number!");
    }
    double determinate = Math.sqrt(b * b - 4 * a * c);
    double x1 = (-b + determinate) / (2.0 * a);
    double x2 = (-b - determinate) / (2.0 * a);
    return Math.max(x1, x2);
  }
}
