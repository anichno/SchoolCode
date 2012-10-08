
/**
 * Prints a super amazing pattern that scales
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Aug 29, 2011 at 10:55:06 AM
 */
public class Project1 {

  static int SIZE = 7;

  public static void main(String[] args) {
    for (int counter = SIZE; counter > 0; counter--) {
      printStars(counter);
      printSlashes(counter);
      printEndStars(counter);
      System.out.println();
    }

  }

  public static void printStars(int curcounter) {
    for (int counter = 0; counter < curcounter - 1; counter++) {
      System.out.print("*");
    }
    for (int counter = SIZE; counter > curcounter - 1; counter--) {
      System.out.print(" ");
    }
  }

  public static void printSlashes(int curcounter) {
    for (int counter = 0; counter < curcounter * 2 - 2; counter++) {
      System.out.print("/");
    }
    for (int counter = 0; counter < (SIZE * 2) - 2 - (curcounter * 2 - 2); counter++) {
      System.out.print("\\");
    }
  }

  public static void printEndStars(int curcounter) {
    for (int counter = SIZE; counter > curcounter - 1; counter--) {
      System.out.print(" ");
    }
    for (int counter = 0; counter < curcounter - 1; counter++) {
      System.out.print("*");
    }
  }
}
