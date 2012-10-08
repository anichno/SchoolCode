
/**
 * Uses a method to take a base and a maximum power and prints out all the numbers
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Aug 23, 2011 at 10:16:46 AM
 */
public class Exercise3 {

  public static void main(String[] args) {
    printPowersOfN(-2, 8);
  }

  public static void printPowersOfN(int multiplier, int total) {
    int startnum = 1;
    System.out.print(startnum + " ");
    for (int a = 0; a < total; a++) {
      startnum = startnum * multiplier;
      System.out.print(startnum + " ");
    }
    System.out.println();
  }
}
