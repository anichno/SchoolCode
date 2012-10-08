
/**
 * Prints the number of duplicates per line in a file
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Sep 27, 2011 at 11:06:26 AM
 */
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Exercise12 {

  public static void main(String[] args) {
    try {
      Scanner input = new Scanner(new File("Exercise12_data.txt"));
      printDuplicates(input);
    } catch (IOException error) {
      System.out.println("Error opening the file Exercise12_data.txt" + error);
    }
  }

  public static void printDuplicates(Scanner input) {
    String line = null;
    String duplicate = null;
    Scanner text = null;
    int counter = 1;

    while (input.hasNextLine()) {
      line = input.nextLine();
      text = new Scanner(line);
      while (text.hasNext()) {
        duplicate = text.next();
        if (line.lastIndexOf(duplicate) != line.indexOf(duplicate)) {
          while (text.hasNext() && text.next().equals(duplicate)) {
            counter++;
          }
        }
        if (counter > 1) {
          System.out.print(duplicate + "*" + counter);
          counter = 1;
        }
      }
      System.out.println();
    }
  }
}
