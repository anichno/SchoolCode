
/**
 * GR 2: Problem 2          Limit your time to approximately 12 minutes
 *
 * Grade:         / 16
 */
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Prob2 {

  public static void main(String[] args) {
    int numUnderscore = 0;
    int numTokens = 0;
    try {
      Scanner input = new Scanner(new File("Wonderland.txt"));
      while (input.hasNext()) {
        if (input.next().matches(".*_.*")) {    //  .* is regex for any char repeating >= 0
          numUnderscore++;
        }
        numTokens++;
      }
      System.out.println(numUnderscore + " tokens contain underscores (about "
                         + Math.max(numUnderscore / numTokens, 1)
                         + " percent of all tokens).");
    } catch (IOException error) {
      System.out.println("Error opening the file Wonderland.txt" + error);
    }
  }
}
