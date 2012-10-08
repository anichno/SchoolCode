
/**
 * GR 2: Problem 3          Limit your time to approximately 7 minutes
 *
 * Grade:           / 10
 */
import java.util.Scanner;

public class Prob3 {

  public static void main(String[] args) {
    String line = null;
    String declare =
           "The right of the people to be secure in their persons, "
           + "houses, papers, and effects, against unreasonable searches "
           + "and seizures, shall not be violated, and no Warrants shall "
           + "issue, but upon probable cause, supported by Oath or "
           + "affirmation, and particularly describing the place to be "
           + "searched, and the persons or things to be seized.";
    Scanner input = new Scanner(declare);
    input.useDelimiter(", ");
    while (input.hasNext()) {
      line = input.next();
      if (!line.matches(".+\\.")) {
        line += ",";
      }
      System.out.println(line);
    }
  }
}
