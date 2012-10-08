
/**
 * An example program that writes data to a file.
 * 
 * @author Wayne.Brown
 * 
 * @version 1.0 - Sep 27, 2011 at 12:53:06 PM
 */
import java.io.*;

public class FileWritingExample {

  public static void main(String[] args) {

    try {
      PrintStream output = new PrintStream(new File("test.txt"));

      for (int j = 0; j <= 10; j++) {
        output.printf("%2d: %4d\n", j, j * j);
      }
    } catch (IOException error) {
      System.out.println("An I/O error occurred: " + error);
    }

  }
}
