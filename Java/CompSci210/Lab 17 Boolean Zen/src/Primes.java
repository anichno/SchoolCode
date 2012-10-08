
/**
 * Returns whether a number is prime
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Sep 19, 2011 at 3:14:40 PM
 */
public class Primes {

  public static void main(String[] args) {
    // test the isPrime method
    for (int j = 2; j <= 20; j++) {
      System.out.printf("Is %d prime?  %b\n", j, isPrime(j));
    }
  }

  //-------------------------------------------------------------------
  public static boolean isPrime(int n) {
    for (int i = 2; i < Math.min(n, 10); i++) {
      if (n % i == 0) {
        return false;
      }
    }
    return true;
  }
}
