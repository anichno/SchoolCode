
/**
 * Final Exam: Problem 9    Limit your time to approximately 23 minutes
 *
 * Grade: _____ / 25
 */
public class Prob09 {

  public static void main(String[] args) {
    sumRecip(0, 1, 7);
    System.out.println("-----");
    sumRecip(0, 1, 10);
  }

  public static void sumRecip(double cur, int start, int end) {
    if (start <= end) {
      cur += (float) 1 / start;
      System.out.printf("sum for n = %2d is %.5f\r\n", start, cur);
      sumRecip(cur, start += 1, end);
    }
  }
}
