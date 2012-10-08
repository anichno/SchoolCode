
/**
 * Final Exam: Problem 10     Limit your time to approximately 23 minutes
 *
 * Grade: _____ / 25
 */
public class Prob10 {

  public static final int START = 100;
  public static final int END = 500;

  public static void main(String[] args) {
    print7Factors(START, END);
  }

  public static void print7Factors(int start, int end) {
    for (int i = start; i <= end; i++) {
      if (has7Factors(i)) {
        System.out.println(i + " has 7 factors");
      }
    }
  }

  public static boolean has7Factors(int testNum) {
    int numFactors = 0;
    for (int factor = 2; factor < testNum; factor++) {
      if (testNum % factor == 0) {
        numFactors++;
      }
    }
    if (numFactors == 7) {
      return true;
    } else {
      return false;
    }
  }
}
