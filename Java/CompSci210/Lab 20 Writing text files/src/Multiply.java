
/**
 * An example program that writes data to a file.
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Sep 27, 2011 at 12:53:06 PM
 */
import java.io.*;

public class Multiply {

  public static void main(String[] args) {

    try {
      PrintStream output = new PrintStream(new File("MultiplicationTable.txt"));

      output.print("\t");
      for (int counter = 0; counter <= 10; counter++) {
        output.printf("%3d:", counter);
      }
      output.println();
      for (int counter = 0; counter <= 10; counter++) {
        output.printf("%3d:", counter);
        for (int counter2 = 0; counter2 <= 10; counter2++) {
          output.printf("%4d", counter * counter2);
        }
        output.println();
      }
    } catch (IOException error) {
      System.out.println("An I/O error occurred: " + error);
    }
  }
}
