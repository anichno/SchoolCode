
/**
 * Fun with arrays!!!
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Oct 6, 2011 at 9:50:07 AM
 */
import java.util.Arrays;
import java.util.Scanner;

public class Lab22 {

  public static void main(String[] args) {
    // Exercise 6
    System.out.println("Exercise 6");
    int[] test1 = new int[]{1, -2, 4, -4, 9, -6, 16, -8, 25, -10};
    int[] test2 = null;
    int[] test3 = new int[]{1};
    System.out.println(stdev(test1));
    System.out.println(stdev(test2));
    System.out.println(stdev(test3));

    //  Exercise 7
    System.out.println("\nExercise 7");
    int[] test4 = new int[]{74, 85, 102, 99, 101, 56, 84};
    System.out.println(kthLargest(2, test4));

    //  Exercise 8
    System.out.println("\nExercise 8");
    int[] test5 = new int[]{5, 2, 4, 17, 55, 4, 3, 26, 18, 2, 17};
    System.out.println(median(test5));

    //  Exercise 12
    System.out.println("\nExercise 12");
    int[] test6 = new int[]{200, 300, 250, 1, 950, 40};
    System.out.println(priceIsRight(test6, 280));
    
    //  Project 1
    System.out.println("\nProject 1");
    int size1 = (int) (Math.random()*100)+1;
    int size2 = (int) (Math.random()*100)+1;
    proj1(3,3);
    System.out.println();
    proj1(2,2);
    System.out.println();
    proj1(size1,size2);
  }

  /**
   * Takes an array and returns the standard deviation
   * @param inputAry
   * @return stdev
   */
  public static double stdev(int[] inputAry) {
    double summation = 0;
    double average = 0;

    if (inputAry == null || inputAry.length == 1) {
      return 0;
    }

    for (int counter = 0; counter < inputAry.length; counter++) {
      average += inputAry[counter];
      average = average / 2;
    }

    for (int counter = 0; counter < inputAry.length; counter++) {
      summation += Math.pow((inputAry[counter] - average), 2) / (inputAry.length - 1);
    }
    return Math.sqrt(summation);
  }

  /**
   * Finds the kthLargest in a set of numbers
   * @param k
   * @param inputAry
   * @return kthLargest
   */
  public static int kthLargest(int k, int[] inputAry) {
    Arrays.sort(inputAry);
    return inputAry[inputAry.length - 1 - k];
  }

  /**
   * Finds the median of a set of numbers
   * @param inputAry
   * @return median
   */
  public static int median(int[] inputAry) {
    Arrays.sort(inputAry);
    return inputAry[inputAry.length / 2];
  }

  /**
   * Finds the closest bid in a set of numbers to the correct value
   * @param bids
   * @param corPrice
   * @return closest bid
   */
  public static int priceIsRight(int[] bids, int corPrice) {
    Arrays.sort(bids);
    for (int counter = bids.length - 1; counter >= 0; counter--) {
      if (bids[counter] <= corPrice) {
        return bids[counter];
      }
    }
    return -1;
  }
  
  
  public static void proj1(int size1, int size2) {
    String input1 = "";
    String input2 = "";
    String sum = "";
    int temp = 0;
    int nextAdd = 0;
    
    
    //  Create random inputs
    for (int counter = 0; counter < size1; counter++) {
      input1 += (int) (Math.random()*10);
    }
    for (int counter = 0; counter < size2; counter++) {
      input2 += (int) (Math.random()*10);
    }
    
    System.out.printf("%8s%s\n\r", "Input 1: ", input1);
    System.out.printf("%8s%s\n\r", "Input 2: ", input2);
    input1 = new StringBuffer(input1).reverse().toString();
    input2 = new StringBuffer(input2).reverse().toString();
    
    //  Add for the length of the smaller input
    for (int counter = 0; counter < Math.min(size1, size2); counter++) {
      temp = (int) (input1.charAt(counter) - '0') + (int) (input2.charAt(counter) - '0') + nextAdd;
      if (temp > 9) {
        temp -= 10;
        sum += (char) (temp + '0');
        nextAdd = 1;
        if (counter == Math.max(size1, size2)-1)
          sum += nextAdd;
      }
      else {
        sum += (char) (temp + '0');
        nextAdd=0;
      }
    }
    
    //  Add the leftover from the longer input
    for (int counter = Math.min(size1, size2); counter < (Math.max(size1, size2)-Math.min(size1, size2))+ Math.min(size1, size2); counter++) {
      if (Math.max(size1, size2)== size1) {
        temp = (int) (input1.charAt(counter) - '0')+nextAdd;
        nextAdd = 0;
        sum += temp;
      }
      else {
        temp = (int) (input2.charAt(counter) - '0')+nextAdd;
        nextAdd = 0;
        sum += temp;
      }
    }
    
    //  Prep and show output
    String output = new StringBuffer(sum).reverse().toString();
    System.out.printf("Answer:%2s%s\n\r", " ",output);
  }
}