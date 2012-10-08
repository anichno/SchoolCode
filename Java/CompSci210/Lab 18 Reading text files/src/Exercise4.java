
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

public class Exercise4 {

  public static void main(String[] args) {
    try {

      Scanner input = new Scanner(new File("exercise4.txt"));
      String output = "";
      while (input.hasNext()) {
        output = output + input.next() + " ";
      }
      System.out.println(output);

    } catch (IOException error) {
      System.out.println("Error in processing the file exercise4.txt" + error);
    }
  }
}
