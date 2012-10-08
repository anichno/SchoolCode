
/**
 * Prints a person on the number of stairs specified
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Aug 17, 2011 at 10:43:24 AM
 */
public class Project4 {

  static int numstairs = 14;

  public static void main(String[] args) {

    for (int a = numstairs - 1; a >= 0; a--) {
      stairs(a);
    }
    for (int a = 0; a < ((numstairs + 1) * 5); a++) {
      System.out.print("*");
    }
    System.out.print("**");
    System.out.println();
  }

  public static void stairs(int num) {

    // Head
    for (int a = num; a > 0; a--) {
      System.out.print("     ");
    }
    System.out.print("  o  ******");
    for (int a = numstairs - 1; a > num; a--) {
      System.out.print("     ");
    }
    System.out.println("*");

    // Arms
    for (int a = num; a > 0; a--) {
      System.out.print("     ");
    }
    System.out.print(" /|\\ *");
    for (int a = numstairs; a > num; a--) {
      System.out.print("     ");
    }
    System.out.println("*");

    // Legs
    for (int a = num; a > 0; a--) {
      System.out.print("     ");
    }
    System.out.print(" / \\ *");
    for (int a = numstairs; a > num; a--) {
      System.out.print("     ");
    }
    System.out.println("*");
  }
}