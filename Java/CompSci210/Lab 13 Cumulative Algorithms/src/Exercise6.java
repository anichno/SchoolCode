
/**
 * Identifies smallest and largest number of user input
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Sep 9, 2011 at 9:51:24 AM
 */
import java.util.Scanner;

public class Exercise6 {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    smallestLargest(input);
  }

  public static void smallestLargest(Scanner input) {
    int high = 0;
    int low = 0;
    int userIn = 0;
    System.out.printf("How many numbers do you want to enter? ");
    int totNums = input.nextInt();
    for (int counter = 0; counter < totNums; counter++) {
      System.out.printf("Number %1$d: ", counter + 1);
      userIn = input.nextInt();
      if (userIn > high) {
        high = userIn;
      }
      if (userIn < low) {
        low = userIn;
      }
    }
    System.out.printf("Smallest = %1$d\n", low);
    System.out.printf("Largest = %1$d\n", high);
  }
}
