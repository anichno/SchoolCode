
/**
 * Given 3 integers, find the median value.
 * Uses an insertion sort algorithm to order the values
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Sep 11, 2011 at 3:36:33 PM
 */
public class Median {

  public static void main(String[] args) {
    // Test cases
    System.out.println("Median of 1 2 3 is " + medianOf3(1, 2, 3));
    System.out.println("Median of 3 1 2 is " + medianOf3(3, 1, 2));
    System.out.println("Median of 2 1 3 is " + medianOf3(2, 1, 3));

    System.out.println("Median of 2 2 2 is " + medianOf3(2, 2, 2));
    System.out.println("Median of 2 2 3 is " + medianOf3(2, 2, 3));
    System.out.println("Median of 3 1 1 is " + medianOf3(3, 1, 1));

    System.out.println("Median of 3 2 1 is " + medianOf3(3, 2, 1));
    System.out.println("Median of 3 5 1 is " + medianOf3(3, 5, 1));
  }

  //-----------------------------------------------------------------
  // Preconditions:
  // Postconditions:
  public static int medianOf3(int n1, int n2, int n3) {
    int[] numSet = new int[]{n1, n2, n3};
    numSet = sort(numSet);
    return numSet[1];
  }

  public static int[] sort(int[] numSet) {
    for (int counter = 1; counter < numSet.length; counter++) {
      int temp = numSet[counter];
      int counter2 = counter - 1;
      for (; counter2 >= 0 && numSet[counter2] > temp; counter2--) {
        numSet[counter2 + 1] = numSet[counter2];
      }
      numSet[counter2 + 1] = temp;
    }
    return numSet;
  }
}
