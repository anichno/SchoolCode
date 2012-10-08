
/**
 * Final Exam: Problem 3     Limit your time to approximately 18 minutes
 *
 * Grade: _____ / 20
 */
import java.util.Arrays;

public class Prob03 {

  public static void main(String[] args) {

    int[] a = {90, 23, 6, 37, 46, 4, 1, 12, 45, 19, 78};

    task1(a);
    task2(a);

    System.out.println(Arrays.toString(a));
  }

  public static void task1(int[] a) {
    int temp = 0;
    int minIndex = 0;

    for (int i = 1; i < a.length; i++) {
      if (a[i] < a[minIndex]) {
        minIndex = i;
      }
    }
    temp = a[0];
    a[0] = a[minIndex];
    a[minIndex] = temp;
  }

  public static void task2(int[] a) {
    int temp = 0;
    int maxIndex = 0;

    for (int i = 1; i < a.length; i++) {
      if (a[i] > a[maxIndex]) {
        maxIndex = i;
      }
    }
    temp = a[a.length - 1];
    a[a.length - 1] = a[maxIndex];
    a[maxIndex] = temp;
  }
}
