
/**
 * Demonstrates labeling of loops and how to break from a specific one
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Dec 9, 2011 at 10:48:20 AM
 */
public class FunLulz {

  public static void main(String[] args) {
    for (int counter = 0; counter < 10; counter++) {
      daloop:
      for (int i = 1;; i++) {
        for (int j = 0;; j++) {
          if (j > 10) {
            System.out.println("Breaking round " + (counter + 1));
            break daloop;
          }
          System.out.println(j);
        }
      }
    }
  }
}
