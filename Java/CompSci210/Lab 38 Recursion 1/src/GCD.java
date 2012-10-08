/**
 * Description: Find the greatest common divisor of two integers.
 *
 * @author  Dr. Brown
 *
 * @version 1.0   Fall 2008
 *
 */

public class GCD {
  
  //-------------------------------------------------------------------
  public static void main(String[] args) {
    System.out.println( "GCD(10,5) = " + gcd(10,5) );
    System.out.println( "GCD(4,12) = " + gcd(4,12) );
    System.out.println( "GCD(1234,45634) = " + gcd(1234,45634) );
    System.out.println( "GCD(15,20) " + gcd(15,20) );
  }
  
  //-------------------------------------------------------------------
  public static int gcd(int x, int y) {    
    while (y > 0) {
      int save = y;
      y = x % y;
      x = save;
    }
    return x;
  }  
}
