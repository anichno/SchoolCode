
/**
 * <put a program description here>
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Aug 19, 2011 at 10:45:19 AM
 */
public class Exercise10 {

  public static void main(String[] args) {
    for (int a = 0; a < 6; a++) {
      for (int b = 0; b < 6; b++) {
        System.out.print("         |");
      }
      System.out.println();
      for (int b = 0; b < 6; b++) {
        for (int c = 0; c < 10; c++) {
          System.out.print(c);
        }
      }
      System.out.println();
    }
  }
}
