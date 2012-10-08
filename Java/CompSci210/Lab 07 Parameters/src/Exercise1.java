
/**
 * uses a method to print up to the desired number enclosed in brackets
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Aug 23, 2011 at 10:12:00 AM
 */
public class Exercise1 {

  public static void main(String[] args) {
    printNumbers(14);
  }

  public static void printNumbers(int total) {
    for (int a = 1; a <= total; a++) {
      System.out.print("[" + a + "] ");
    }
    System.out.println();
  }
}
