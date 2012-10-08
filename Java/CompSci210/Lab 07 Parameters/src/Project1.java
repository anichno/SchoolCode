
/**
 * prints tree declared by size
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Aug 23, 2011 at 11:00:26 AM
 */
public class Project1 {

  public static void main(String[] args) {
    tree(2, 5);
  }

  public static void tree(int segments, int height) {

    for (int counter = 0; counter < segments; counter++) { // tree segments
      for (int counter2 = counter; counter2 < height; counter2++) { // tree height
        for (int spaces = height; spaces > counter2; spaces--) {
          System.out.print(" ");

        }

        System.out.print("*");
        for (int counter4 = 0; counter4 < counter2; counter4++) { // internal * per level
          System.out.print("*");
        }
        for (int counter4 = 1; counter4 < counter2; counter4++) { // internal * per level
          System.out.print("*");
        }
        if (counter2 > 0) {
          System.out.println("*");
        } else {
          System.out.println();
        }
      }
    }
    System.out.println("     *");
    System.out.println("     *");
    System.out.println("  *******");
  }
}
