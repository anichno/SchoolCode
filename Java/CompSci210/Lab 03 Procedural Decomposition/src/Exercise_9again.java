
/**
 * Prints the shapes to the console in a different way
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Aug 10, 2011 at 12:13:10 PM
 */
public class Exercise_9again {

  public static void main(String[] args) {
    top();
    bottom();
    System.out.println();
    divider();
    top();
    bottom();
    System.out.println();
    divider();
    bottom();
    top();
    divider();
    bottom();
  }

  public static void top() {
    System.out.println("  _______");
    System.out.println(" /\t \\");
    System.out.println("/\t  \\");
  }

  public static void divider() {
    System.out.println("-\"-\'-\"-\'-\"-");
  }

  public static void bottom() {
    System.out.println("\\\t  /");
    System.out.println(" \\_______/");
  }

  public static void whole() {
    top();
    bottom();
    System.out.println();
    divider();
  }
}
