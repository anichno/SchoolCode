
/**
 * Prints the Roman numeral equivalents from 1 to 2014
 * Uses an iterative approach with parallel arrays
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Sep 7, 2011 at 10:43:04 AM
 */
public class Project1 {

  public static void main(String[] args) {
    for (int counter = 1; counter <= 2014; counter++) {
      System.out.print(counter + ": ");
      romanNumerals(counter);
      System.out.println();
    }
  }

  public static void romanNumerals(int num) {
    int leftover = num;
    String[] romanChars = new String[]{"M", "CM", "CCM", "DCC", "DC", "D", "CD", "C", "L",
                                       "LX", "X", "IX", "IIX", "VII", "VI", "V", "IV", "I"};
    int[] romanNums = new int[]{1000, 900, 800, 700, 600, 500, 400,
                                100, 50, 40, 10, 9, 8, 7, 6, 5, 4, 1};
    String nextRoman = "";
    for (int counter = 0; leftover > 0;) {
      if (leftover / romanNums[counter] >= 1) {
        nextRoman = nextRoman + romanChars[counter];
        leftover -= romanNums[counter];
        counter = 0;
      } else {
        counter++;
      }
    }
    System.out.print(nextRoman);
  }
}
