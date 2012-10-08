
import java.util.Arrays;


/**
 * <put a program description here>
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Oct 13, 2011 at 9:57:18 AM
 */
public class TowersOfHanoi {

  public static void main(String[] args) {
    final int SIZE = 30;
    int[] src = new int[SIZE];
    int[] dst = new int[SIZE];
    int[] tmp = new int[SIZE];
    for (int a = 1; a <= SIZE; a++) {
      src[a-1] = a;
    }
    System.out.println("Source tower");
    System.out.println(Arrays.toString(src));
    System.out.println("Destination Tower");
    System.out.println(Arrays.toString(dst));
    System.out.println("Temp Tower");
    System.out.println(Arrays.toString(tmp));
    System.out.println("\r\nDestination Tower Result\r\n" + Arrays.toString(hanoi(SIZE, src, dst, tmp)));
  }
  
  public static int[] hanoi(int n, int[] src, int[] dst, int[] tmp) {
    if (n > 0) {
      hanoi(n-1, src, tmp, dst);
      dst[n-1] = src[n-1];
      hanoi(n-1, tmp, dst, src);
    }
    return dst;
  }
}
