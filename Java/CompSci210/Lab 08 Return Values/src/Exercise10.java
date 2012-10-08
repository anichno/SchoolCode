
/**
 * Uses a method to expand scientific notation
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Aug 25, 2011 at 10:55:33 AM
 */
public class Exercise10 {

  public static void main(String[] args) {
    System.out.println("What is 6.23x10^5?\t" + scientific(6.23, 5));
    System.out.println("What is 1.9x10^-2?\t" + scientific(1.9, -2));
    System.out.println("What is 1.4x10^1?\t" + scientific(1.4, 1));
  }

  public static double scientific(double base, int exponent) {
    return base * (Math.pow(10, exponent));
  }
}
