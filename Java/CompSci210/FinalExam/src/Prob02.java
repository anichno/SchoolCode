
/**
 * Final Exam: Problem 2     Limit your time to approximately 18 minutes
 *
 * Grade:         / 20
 */
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;

public class Prob02 {

  public static final int START = 0;
  public static final int END = 100;

  public static void main(String[] args) {
    int columnSpacer = Integer.toString(END).length();  //  This allows the indent before the columns to be as small as possible
    try {
      PrintStream output = new PrintStream(new File("Problem2.txt"));

      for (int j = START; j <= END; j++) {
        output.printf("%" + columnSpacer + "d  %.10f\r\n", j, (float) j / (j + 1));
      }
    } catch (IOException error) {
      System.out.println("An I/O error occurred: " + error);
    }
  }
}
