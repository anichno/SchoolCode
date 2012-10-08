
/**
 * GR 2: Problem 1          Limit your time to approximately 11 minutes
 *
 * Grade:         / 15
 */
public class Prob1 {

  public static void main(String[] args) {
    int diff = 0;
    int numNew = 0;
    int numOld = 100;
    int totNum = 0;

    do {
      numNew = (int) (Math.random() * 12 + 1);
      diff = Math.abs(numNew - numOld);
      numOld = numNew;
      totNum++;
      if (diff != 4) {
        System.out.print(numNew + "_");
      } else {
        System.out.print(numNew);
      }
    } while (diff != 4);
    System.out.println();
    System.out.println(totNum + " numbers were generated.");
  }
}
