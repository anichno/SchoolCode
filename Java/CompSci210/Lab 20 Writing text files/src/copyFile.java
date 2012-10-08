
/**
 * An example program that writes data to a file.
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Sep 27, 2011 at 12:53:06 PM
 */
import java.io.*;
import java.util.Scanner;

public class copyFile {

  public static void main(String[] args) {

    String inputFile = "hamlet.txt";
    String outputFile = "testoutput.txt";
    try {
      Scanner input = new Scanner(new File(inputFile));
      PrintStream output = new PrintStream(new File(outputFile));
      makeCopy(input, output);
    } catch (IOException error) {
      System.out.println("An I/O error occurred: " + error);
    }
  }

  public static void makeCopy(Scanner input, PrintStream output) {
    while (input.hasNextLine()) {
      output.println(input.nextLine());
    }
  }
}
