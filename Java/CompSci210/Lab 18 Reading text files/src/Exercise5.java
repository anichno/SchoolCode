
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

public class Exercise5 {

  public static void main(String[] args) {
    System.out.println(readEntireFile("Hamlet.txt"));
  }

  public static String readEntireFile(String fileName) {
    try {

      Scanner input = new Scanner(new File(fileName));
      String output = "";
      while (input.hasNextLine()) {
        output = output + input.nextLine() + '\n';
      }
      return output;

    } catch (IOException error) {
      System.out.println("Error in processing the file " + error);
      return null;
    }
  }
}
