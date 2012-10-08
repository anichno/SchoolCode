
/**
 * Prints a range of numbers given
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Sep 7, 2011 at 10:05:39 AM
 */
public class Exercise5 {

  public static void main(String[] args) {
    printRange(2, 7);
    printRange(19, 11);
    printRange(5, 5);
  }

  public static void printRange(int start, int end) {
    System.out.print("[");
    for (int counter = 0; counter < Math.max(start, end) - Math.min(start, end); counter++) {
      if (start > end) {
        System.out.print(start - counter + ", ");
      } else {
        System.out.print(start + counter + ", ");
      }
    }
    System.out.println(end + "]");
  }
}
