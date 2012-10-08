
/**
 * Concatenates a string based upon the defined number of repetitions
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Sep 7, 2011 at 9:57:12 AM
 */
public class Exercise2 {

  public static void main(String[] args) {
    System.out.println(rep1("Hello World ", 4));
  }

  public static String rep1(String input, int reps) {
    String output = "";
    if (reps < 0) {
      return output;
    }
    for (int counter = 0; counter < reps; counter++) {
      output = output + input;
    }
    return output;
  }
}
