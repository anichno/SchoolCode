/**
 * Solve a series of problems using arrays
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Sep 29, 2011 at 5:15:01 PM
 */
public class Array_exercises {

  public static void main(String[] args) {

    int[] alpha;

    alpha = createRandomArray(15, 1, 5);
    printArray("alpha", alpha);
    
    for (int test = 1; test <= 5; test++) {
      System.out.printf("%d was found at location %d\n", test,
                        lastIndexOf(alpha, test));
    }
    
    //  Excercise 2
    int[] excer2 = new int[] {36,12,25,19,46,31,22};
    System.out.println(range(excer2));
    
    //  Excercise 3
    int[] excer3 = new int[] {14,1,22,17,36,7,-43,5};
    System.out.println(countInRange(excer3,4,17));

    //  Excercise 4
    double[] list1 = new double[] {16.1,12.3,22.2,14.4};
    double[] list2 = new double[] {1.5,4.3,7.0,19.5,25.1,46.2};
    System.out.println(isSorted(list1));
    System.out.println(isSorted(list2));
    
    //  Excercise 5
    int[] excer5 = createRandomArray(10000, 1, 1000);
    int[] modeAry = mode(excer5);
    printArray("Dataset", excer5);
    System.out.println("The mode is " + modeAry[0] + " with " + modeAry[1] + " occurences");
  }

  /**
   * Creates an integer array of the specified size and fills it with 
   * random values in the range [minValue, maxValue] inclusive.
   * 
   * @param size The desired size of the integer array
   * @param minValue The minimum value of any element in the array.
   * @param maxValue The maximum value of any element in the array.
   * @return The array that was created.
   */
  public static int[] createRandomArray(int size, int minValue, int maxValue) {
    int[] array = new int[size];

    for (int j = 0; j < size; j++) {
      array[j] = minValue + (int) (Math.random() * (maxValue - minValue + 1));
    }

    return array;
  }

  /**
   * Prints all the elements of an integer array on a single line. The
   * array is labeled using the specified name. A line above the array
   * elements is printed to show the index/location of each element.
   * 
   * @param name A string to display that labels the array.
   * @param a The integer array to be printed.
   */
  public static void printArray(String name, int[] a) {

    if (a.length > 0) {
      // Print the indexes so that the position of an element is easy determined.
      System.out.printf("%" + name.length() + "s   ", " ");
      for (int j = 0; j < a.length; j++) {
        System.out.printf("%6d", j);
      }
      System.out.println();

      // Print the element values
      System.out.printf("%s : ", name);
      System.out.printf("  %4d", a[0]);
      for (int j = 1; j < a.length; j++) {
        System.out.printf(", %4d", a[j]);
      }
    }
    System.out.println();
  }
  
  public static int lastIndexOf(int[] array, int test) {
    for(int counter = array.length-1; counter >= 0; counter--) {
      if (array[counter]==test)
        return counter;
    }
    return -1;
  }
  
  public static int range(int[] array) {
    int max = array[0];
    int min = array[0];
    
    for(int counter = 0; counter < array.length; counter++) {
      if (array[counter] > max)
        max = array[counter];
      if (array[counter] < min)
        min = array[counter];
    }
    return max-min+1;
  }
  
  public static int countInRange(int[] array, int min, int max) {
    int totInRange = 0;
    
    for (int counter = 0; counter < array.length; counter++) {
      if (array[counter] >= min && array[counter] <= max)
        totInRange++;
    }
    return totInRange;
  }
  
  /**
   * Takes an array and determines whether or not it is sorted
   * 
   * @param array
   * @return boolean
   */
  public static boolean isSorted(double[] array) {
    for (int counter = 1; counter < array.length; counter++) {
      if (array[counter-1] > array[counter])
        return false;
    }
    return true;
  }
  
  /**
   * Takes an array and returns the most frequently occuring number
   * along with its number of occurences
   * @param array
   * @return int[] {mode, occurences}
   */
  public static int[] mode(int[] array) {
    int[] sortedArray = sort(array);
    int[] dupeRemoved = removeDupes(sortedArray);
    int[] countOccurence = new int[dupeRemoved.length];
    for (int counter = 0; counter < sortedArray.length; counter++) {
      for (int counter2 = 0; counter2 < dupeRemoved.length; counter2++) {
        if (sortedArray[counter] == dupeRemoved[counter2])
          countOccurence[counter2]++;
      }
    }
    int max = 0;
    int modeIndex = 0;
    for (int counter = 0; counter < countOccurence.length; counter++) {
      if (max < countOccurence[counter]) {
        max = countOccurence[counter];
        modeIndex = counter;
      }
    }
    int[] returnMode = new int[] { dupeRemoved[modeIndex], max};
    return returnMode;
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
  
  public static int[] removeDupes(int[] numSet) {
    int[] temp = new int[numSet.length];
    int tempCount = 0;
    
    temp[0] = numSet[0];
    tempCount++;
    for (int counter = 0; counter < numSet.length; counter++) {
      if (counter-1 >=0 && numSet[counter] != numSet[counter-1]) {
        temp[tempCount] = numSet[counter];
        tempCount++;
      }
    }
    int[] dupeRemoved = new int[tempCount];
    System.arraycopy(temp, 0, dupeRemoved, 0, tempCount);
    return dupeRemoved;
  }

}
