
/**
 * Count the words in Hamlet
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Sep 20, 2011 at 5:12:08 PM
 */
// Counts the number of words in Hamlet.
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Exercise1 {

  public static void main(String[] args) {
    try {

      Scanner input = new Scanner(new File("boyGirl.txt"));
      int boys = 0;
      int boySum = 0;
      int girls = 0;
      int girlSum = 0;
      while (input.hasNext()) {
        input.next();
        boys++;
        boySum += input.nextInt();
        if (input.hasNext()) {
          input.next();
          girls++;
          girlSum += input.nextInt();
        }
      }
      int totSum = Math.abs(boySum - girlSum);
      System.out.printf("%d boys, %d girls\n", boys, girls);
      System.out.printf("Difference between boys' and girls' sums: %d\n", totSum);

    } catch (IOException error) {
      System.out.println("Error in processing the file boyGirl.txt" + error);
    }
  }
}
