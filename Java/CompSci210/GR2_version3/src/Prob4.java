
/**
 * GR 2: Problem 4          Limit your time to approximately 12 minutes
 *
 * Grade:           / 16
 */
import java.util.Arrays;

public class Prob4 {

  public static void main(String[] args) {
    final int SIZE = 9;
    final int MULTIPLE = 4;

    // Declare a variable whose data type is an integer array.
    int[] GRarray = null;

    // For the variable you declared, create an integer array that can
    // hold SIZE elements.
    GRarray = new int[SIZE];

    // Implement a loop that initializes the array to contain the values:
    // MULTIPLE * 0, 
    // MULTIPLE * 1,
    // MULTIPLE * 2,
    // etc. ...
    // up to MULTIPLE * SIZE-1.

    for (int index = 0; index < SIZE; index++) {
      GRarray[index] = index * MULTIPLE;
    }

    // Display the contents of the entire array to a single line in the
    // console window.

    System.out.println(Arrays.toString(GRarray));

    // Implement a loop that, for each consecutive pair of elements in
    // the array, it swaps their contents (i.e., swap 0 with 1, 1 with 2, 
    // 2 with 3, etc.).

    for (int index = 0; index < SIZE - 1; index++) {
      GRarray[index] = GRarray[index + 1];
    }
    GRarray[SIZE - 1] = 0;

    // Display the contents of the entire array to a single line in the
    // console window.
    System.out.println(Arrays.toString(GRarray));
  }
}
