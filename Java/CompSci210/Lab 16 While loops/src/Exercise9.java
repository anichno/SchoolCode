
/**
 * Prints the factors of a number
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Sep 19, 2011 at 10:46:52 AM
 */
public class Exercise9 {

  public static void main(String[] args) {
    printFactors(24);
  }

  public static void printFactors(int num) {
    int divisor = 1;
    do {
      if (num % divisor == 0) {
        System.out.print(divisor + " and ");
      }
      divisor++;
    } while (divisor < num);
    System.out.println(num);
  }
}
