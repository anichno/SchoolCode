
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
public class Romeo {

  public static void main(String[] args) {
    try {
      
      Scanner input = new Scanner(new File("Romeo_and_Juliet.txt"));
      int count = 0;
      int count2 = 0;
      while (input.hasNext()) {
        String word = input.next();
        if (word.equalsIgnoreCase("love"))
          count2++;
        count++;
      }
      System.out.println("total words = " + count);
      System.out.println("love appears " + count2 + " times");
      
    } catch (IOException error) {
      System.out.println("Error in processing the file Romeo_and_Juliet.txt" + error);
    }
  }
}