
/**
 * uses a method to solve the quadratic equation
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Aug 23, 2011 at 10:25:49 AM
 */
public class Exercise8 {

  public static void main(String[] args) {
    quadratic(1, -7, 12);
    quadratic(1, 3, 2);
    quadratic(3,5,14);
  }

  public static void quadratic(int a, int b, int c) {
    double solution1, solution2 = 0;

    solution1 = ((b * -1) + Math.sqrt((b * b) - (4 * a * c))) / (2 * a);
    solution2 = ((b * -1) - Math.sqrt((b * b) - (4 * a * c))) / (2 * a);

    System.out.println("x = " + solution1 + ", x = " + solution2);
  }
}
