
/**
 * <put a program description here>
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Dec 5, 2011 at 10:03:50 AM
 */
public class printSequenceIterative {

  public static void main(String[] args) {
    printSequence(1, 10);
    printSequence(5, 15);
  }
  
  public static void printSequence(int start, int end) {
    for (int i = start; i <= end; i++) {
      System.out.print(i + " ");
    }
    System.out.println();
  }
}
