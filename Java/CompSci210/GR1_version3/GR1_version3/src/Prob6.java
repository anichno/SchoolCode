//
// GR 1: Prolblem 6          Limit your time to approximately 15 minutes
//
// Grade: _____ / 21 
//
public class Prob6 {

  static int SIZE = 3;

  public static void main(String[] args) {
    for (int counter = 1; counter <= SIZE; counter++) {
      for (int counter2 = 1; counter2 <= counter; counter2++) {
        printCharLine('?', counter2);
      }
    }
  }

  //-------------------------------------------------------------------
  public static void printCharLine(char c, int n) {
    for (int j = 0; j < n; j++) {
      System.out.print(c);
    }
    System.out.println();
  }
}
