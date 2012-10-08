
/**
 * Description: Raise an integer to an integer power using recursion.
 *
 * @author  C14Anthony.Canino
 *
 * @version 1.0
 *
 */
public class Power_Recursion {
  //-------------------------------------------------------------------

  public static void main(String[] args) {
    System.out.println("2^5 = " + power(2, 5));
    System.out.println("10^3 = " + power(10, 3));
    System.out.println("3^12 = " + power(3, 12));
    System.out.println("5^5 = " + power(5, 5));
  }

  //-------------------------------------------------------------------
  // Precondition: exponent must be non-negative
  public static int power(int base, int exponent) {
    if (exponent > 1) {
      base *= power(base, exponent - 1);
    }
    return base;
  }
}
