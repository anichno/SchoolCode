
/**
 * Final Exam: Problem 1     Limit your time to approximately 18 minutes
 *
 * Grade: _____ / 20
 */
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Prob01 {

  public static void main(String[] args) {
    try {
      Scanner input = new Scanner(new File("Optics.txt"));
      printUppercase(input);
    } catch (IOException error) {
      System.out.println("Error opening the file Optics.txt" + error);
    }
  }

  public static void printUppercase(Scanner input) {
    boolean hadMatches = false;
    while (input.hasNextLine()) {
      String line = input.nextLine();

      Scanner parseLine = new Scanner(line);
      while (parseLine.hasNext()) {
        String temp = parseLine.next();
        if (temp.substring(0, 1).matches("[A-Z]+")) {
          System.out.print(temp + " ");
          hadMatches = true;
        }
      }
      if (hadMatches) {
        System.out.println();
        hadMatches = false;
      } else {
        System.out.println("---");
      }
    }
  }
}
