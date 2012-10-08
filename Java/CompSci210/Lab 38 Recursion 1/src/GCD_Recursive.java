
/**
 * Description: Find the greatest common divisor of two integers using recursion.
 *
 * @author  C14Anthony.Canino
 *
 * @version 1.0
 *
 */
public class GCD_Recursive {

  //-------------------------------------------------------------------
  public static void main(String[] args) {
    System.out.println("GCD(10,5) = " + gcd(10, 5));
    System.out.println("GCD(4,12) = " + gcd(4, 12));
    System.out.println("GCD(1234,45634) = " + gcd(1234, 45634));
    System.out.println("GCD(15,20) " + gcd(15, 20));
  }

  //-------------------------------------------------------------------
  public static int gcd(int x, int y) {
    if (y > 0) {
      x = gcd(y, x % y);
    }
    return x;
  }
}
