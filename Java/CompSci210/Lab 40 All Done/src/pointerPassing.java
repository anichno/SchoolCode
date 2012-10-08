
/**
 * Demonstrates how an array is a pointer which can be passed to a method
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Dec 9, 2011 at 11:02:09 AM
 */
import java.util.Arrays;

public class pointerPassing {

  public static void main(String[] args) {
    int[] daArray = new int[10];
    for (int i = 0; i < daArray.length; i++) {
      daArray[i] = (int) (Math.random() * 10) + 1;
    }
    System.out.println(Arrays.toString(daArray));

    changeIt(daArray);

    System.out.println(Arrays.toString(daArray));
  }

  public static void changeIt(int[] array) {
    for (int i = 0; i < array.length; i++) {
      array[i] = (int) (Math.random() * 10) + 1;
    }
  }
}
