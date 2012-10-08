
/**
 * Uses a method to return the largest root in the quadratic equation
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Aug 25, 2011 at 10:28:23 AM
 */
public class Exercise8 {

  public static void main(String[] args) {
    System.out.println("Which root of the quadratic function is larger? 7x^2 + -2x - 11?\t" + quadratic(7, -2, -11));
    System.out.println("Which root of the quadratic function is larger? 1x - 7x + 12?\t\t" + quadratic(1, -7, 12));
    System.out.println("Which root of the quadratic function is larger? -1x + 12 + 2?\t\t" + quadratic(-1, 12, 2));
  }

  public static double quadratic(int num1, int num2, int num3) {
    double root1 = 0;
    double root2 = 0;

    root1 = ((num2 * -1) + Math.sqrt((num2 * num2) - (4 * num1 * num3))) / (2 * num1);
    root2 = ((num2 * -1) - Math.sqrt((num2 * num2) - (4 * num1 * num3))) / (2 * num1);

    if (root1 >= root2) {
      return root1;
    } else {
      return root2;
    }
  }
}
