
/**
 * Exercise 3
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Aug 17, 2011 at 10:28:08 AM
 */
public class Exercise3 {

  public static void main(String[] args) {
    int a = 1;
    int prev1 = 0;
    int prev2 = 0;
    for (int b = 0; b < 12; b++) {
      System.out.print(a + " ");
      prev1 = a;
      a = prev1 + prev2;
      prev2 = prev1;
    }
  }
}
