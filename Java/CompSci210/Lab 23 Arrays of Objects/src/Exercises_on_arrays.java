
/**
 * Exercises on arrays for lesson 23
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Oct 6, 2011 at 2:22:21 PM
 */
import java.util.Scanner;
import java.io.*;
import java.util.Arrays;

public class Exercises_on_arrays {
  
  public static void main(String[] args) {
    
    try {
      Scanner getData = new Scanner(new File("Exercise13_data.txt"));
      Scanner testData = new Scanner(new File("exercise14_test.txt"));
      while (getData.hasNextLine()) {
        int[] data = createArrayFromString(getData.nextLine());
        int[] test = createArrayFromString(testData.nextLine());
        System.out.println(Arrays.toString(data));
        System.out.println(longestSortedSequence(data));
        System.out.println(Arrays.toString(data) + " " + Arrays.toString(test));
        System.out.println(contains(data, test));
        System.out.println(Arrays.toString(append(data, test)));
      }
    } catch (IOException error) {
      System.out.printf("Error reading data file: error was %s\n", error);
    }
    
  }

  //-------------------------------------------------------------------
  /**
   * Given a string with a series of integer values, comma separated,
   * create an array of integers that contains the data.
   * 
   * @param data A string that contains a comma separated list of integers.
   * @return An array of integers.
   */
  public static int[] createArrayFromString(String data) {

    // Read the values out of the string and count how many there are.
    Scanner countValues = new Scanner(data);
    countValues.useDelimiter(",");
    int count = 0;
    while (countValues.hasNext()) {
      countValues.next();
      count++;
    }

    // Create an array of the appropriate size.
    int[] array = new int[count];

    // Replace all the commas with white space so we can read the integers
    data = data.replaceAll(",", " ");

    // Read the data out of the string and into the array.
    Scanner getValues = new Scanner(data);
    int index = 0;
    while (getValues.hasNextInt()) {
      array[index++] = getValues.nextInt();
    }
    
    return array;
  }
  
  public static int longestSortedSequence(int[] inputAry) {
    int length[] = new int[inputAry.length];
    int maxLength = 0;
    for (int counter = 0; counter < inputAry.length; counter++) {
      for (int counter2 = counter; counter2 < inputAry.length - 1; counter2++) {
        if (inputAry[counter2] < inputAry[counter2 + 1]) {
          length[counter]++;
        } else {
          break;
        }
      }
    }
    for (int counter = 0; counter < length.length; counter++) {
      if (length[counter] > maxLength) {
        maxLength = length[counter];
      }
    }
    return maxLength + 1;
  }
  
  public static boolean contains(int[] a1, int[] a2) {
    String stra1 = Arrays.toString(a1);
    String stra2 = Arrays.toString(a2);
    String regexMatch = String.format(".*%s.*", stra2.subSequence(1, stra2.length() - 1));
    
    if (stra1.matches(regexMatch)) {
      return true;
    }
    return false;
  }
  
  public static int[] append(int[] a1, int[] a2) {
    int[] appended = new int[a1.length + a2.length];
    
    System.arraycopy(a1, 0, appended, 0, a1.length);
    System.arraycopy(a2, 0, appended, a1.length, a2.length);
    return appended;
  }
}
