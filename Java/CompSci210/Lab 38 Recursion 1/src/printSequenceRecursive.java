
/**
 * Prints a sequence of numbers using recursion.
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Dec 5, 2011 at 10:08:06 AM
 */
public class printSequenceRecursive {

  public static void main(String[] args) {
    printSequence(1, 10);
  }

  public static void printSequence(int start, int end) {
    if (start <= end) {
      System.out.print(start + " ");
      printSequence(start + 1, end);
    } else {
      System.out.println();
    }
  }
}
