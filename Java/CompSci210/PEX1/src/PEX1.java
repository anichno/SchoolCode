
/**
 * Prints the Seattle Space Needle scaled by a variable
 * 
 * @author Anthony Canino
 * 
 * @version 1.0 - Aug 27, 2011 at 10:23:43 PM
 * Documentation Statement: I recieved no help on this assignment
 */
public class PEX1 {

  static int SIZE = 4;

  public static void main(String[] args) {
    spear();
    upperhalf();
    divider();
    lowerhalf();
    spear();
    body();
    upperhalf();
    divider();
  }

  public static void spear() {

    //prints the || bars
    for (int counter = 0; counter < SIZE; counter++) {
      for (int counter2 = 0; counter2 < SIZE * 3; counter2++) {
        System.out.print(" ");
      }
      System.out.println("||");
    }
  }

  public static void upperhalf() {

    //prints the upper half of the top section and is reused with the base
    for (int counter = 0; counter < SIZE; counter++) {
      for (int counter2 = SIZE - counter; counter2 > 1; counter2--) {
        System.out.print("   ");
      }
      System.out.print("__/");
      for (int counter3 = 0; counter3 < counter; counter3++) {
        System.out.print(":::");
      }
      System.out.print("||");
      for (int counter3 = 0; counter3 < counter; counter3++) {
        System.out.print(":::");
      }
      System.out.println("\\__");
    }
  }

  public static void divider() {

    //prints the divider between upper and lower halves
    System.out.print("|");
    for (int counter = 0; counter < SIZE * 6; counter++) {
      System.out.print("\"");
    }
    System.out.println("|");
  }

  public static void lowerhalf() {

    //prints the lower half of the top section
    for (int counter = 0; counter < SIZE; counter++) {
      for (int counter2 = counter; counter2 > 0; counter2--) {
        System.out.print("  ");
      }
      System.out.print("\\_");
      for (int counter2 = 1; counter2 < SIZE * 3 - counter * 2; counter2++) {
        System.out.print("/\\");
      }
      System.out.println("_/");
    }
  }

  public static void body() {

    //prints the section dividing the top and base of the structure
    for (int counter = 0; counter < SIZE * 4; counter++) {
      for (int counter2 = 1; counter2 < SIZE; counter2++) {
        System.out.print("   ");
      }
      System.out.println("|%%||%%|");   // Im not sure if this part should expand or not, since i consider it like the spear
    }
  }
}
