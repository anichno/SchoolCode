
/**
 * Prints the fancy pattern
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Aug 19, 2011 at 11:03:45 AM
 */
public class Project2 {

  public static void main(String[] args) {
    bars();
    for (int a = 0; a < 2; a++) {
      for (int b = 2; b >= 0; b--) {
        top(b);
      }
    }
    bars();
    for (int a = 0; a < 2; a++) {
      for (int c = 0; c <= 2; c++) {
        bottom(c);
      }
    }
    bars();
  }

  public static void bars() {
    System.out.println("+------+");
  }

  public static void top(int a) {
    System.out.print("|");
    for (int b = a; b > 0; b--) {
      System.out.print(" ");
    }
    System.out.print("^");
    if (a != 0) {
      for (int b = (a % 2) * 2; b > 0; b--) {
        System.out.print(" ");
      }
    } else {
      System.out.print("    ");
    }
    System.out.print("^");
    for (int b = a; b > 0; b--) {
      System.out.print(" ");
    }
    System.out.println("|");
  }

  public static void bottom(int a) {
    System.out.print("|");
    for (int b = a; b > 0; b--) {
      System.out.print(" ");
    }
    System.out.print("V");
    if (a != 0) {
      for (int b = (a % 2) * 2; b > 0; b--) {
        System.out.print(" ");
      }
    } else {
      System.out.print("    ");
    }
    System.out.print("V");
    for (int b = a; b > 0; b--) {
      System.out.print(" ");
    }
    System.out.println("|");
  }
}
