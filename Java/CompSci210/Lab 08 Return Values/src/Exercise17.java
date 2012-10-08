
/**
 * Draws a cool box without nested loops or logic statements
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Aug 25, 2011 at 10:39:46 AM
 */
public class Exercise17 {

  static int height = 14;

  public static void main(String[] args) {
    for (int curlevel = 0; curlevel < height; curlevel++) {
      drawSlash1(curlevel);
      drawExclaim(curlevel);
      drawSlash2(curlevel);
      System.out.println();
    }
  }

  public static void drawSlash1(int curlevel) {
    for (int a = 0; a < curlevel * 2; a++) {
      System.out.print("\\");
    }
  }

  public static void drawExclaim(int curlevel) {
    for (int a = 0; a < (height * 4 - 2) - curlevel * 4; a++) {
      System.out.print("!");
    }
  }

  public static void drawSlash2(int curlevel) {
    for (int a = 0; a < curlevel * 2; a++) {
      System.out.print("/");
    }
  }
}
