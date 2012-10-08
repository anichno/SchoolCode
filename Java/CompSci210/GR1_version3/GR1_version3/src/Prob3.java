//
// GR 1: Prolblem 3          Limit your time to approximately 12 minutes
//
// Grade: _____ / 16 
//
public class Prob3 {

  public static void main(String[] args) {

    String joe = "This is an example of a string.";

    String martin = "In the course of human history, things have happened.";

    String sue = "Print every other letter.";

    System.out.println(joe.toUpperCase());
    System.out.println("human is at position " + martin.indexOf("human") + "\ttotal length = " + martin.length());
    char[] sueArray = sue.toCharArray();
    for (int counter = 0; counter < sue.length(); counter += 2) {
      System.out.print(sueArray[counter]);
    }
    System.out.println();
  }
}
