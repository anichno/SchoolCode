
/**
 * Prints Hamlet with a max of 30 characters per line
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Sep 20, 2011 at 5:12:08 PM
 */
import java.io.*;
import java.util.*;

public class lineWrap {

  public static void main(String[] args) {
    try {
      Scanner input = new Scanner(new File("hamlet.txt"));
      lineWrap30(input);
    } catch (IOException error) {
      System.out.println("Error opening the file hamlet.txt" + error);
    }
  }

  public static void lineWrap30(Scanner input) {
    String hamlet = "";
    String wrapHamlet = "";
    String nextToken = "";
    while (input.hasNext()) {
      hamlet = hamlet + " " + input.next();
    }
    Scanner wrap = new Scanner(hamlet);

    while (wrap.hasNext()) {
      nextToken = wrap.next();
      if (wrapHamlet.length() + nextToken.length() < 30) {
        wrapHamlet = wrapHamlet + " " + nextToken;
      } else {
        System.out.println(wrapHamlet);
        wrapHamlet = nextToken;
      }
    }
    System.out.println(wrapHamlet);
  }
}
