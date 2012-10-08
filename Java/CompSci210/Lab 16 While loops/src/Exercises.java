
/**
 * Implements solutions for exercises 3, 4, and 11
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Sep 19, 2011 at 10:12:47 AM
 */
import java.util.Random;

public class Exercises {

  public static void main(String[] args) {
    System.out.println(toBinary(44));
    randomX();
    threeHeads();
  }

  public static String toBinary(int num) {
    return Integer.toBinaryString(num);
  }

  public static void randomX() {
    int numX = 0;
    do {
      numX = (int) (Math.random() * 16 + 5);
      for (int counter = 0; counter < numX; counter++) {
        System.out.print('X');
      }
      System.out.println();
    } while (numX < 16);
  }

  public static void threeHeads() {
    Random myRand = new Random();
    int coin = 0;
    int numHeads = 0;
    do {
      coin = myRand.nextInt(2);
      if (coin == 0) {
        System.out.print("H ");
        numHeads++;
      } else {
        System.out.print("T ");
        numHeads = 0;
      }
    } while (numHeads < 3);
    System.out.println("\nThree heads in a row!");
  }
}
