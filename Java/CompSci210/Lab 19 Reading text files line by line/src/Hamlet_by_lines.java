
/**
 * Displays the average number of tokens per line in hamlet
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Sep 20, 2011 at 5:12:08 PM
 */
import java.io.*;
import java.util.*;

public class Hamlet_by_lines {

  public static void main(String[] args) {
    try {
      Scanner input = new Scanner(new File("hamlet.txt"));
      displayAvgTokens(input);
    } catch (IOException error) {
      System.out.println("Error opening the file hamlet.txt" + error);
    }
  }

  public static void displayAvgTokens(Scanner input) {
    int numberLines = 0;
    int totTokens = 0;
    double avgTokens = 0;
    while (input.hasNextLine()) {
      String line = input.nextLine();
      numberLines++;

      Scanner parseLine = new Scanner(line);
      int countTokens = 0;
      while (parseLine.hasNext()) {
        parseLine.next();
        countTokens++;
      }
      totTokens += countTokens;
    }
    avgTokens = totTokens / numberLines;
    System.out.printf("total lines = %d\n", numberLines);
    System.out.printf("average tokens = %f\n", avgTokens);
  }
}