
/**
 * Given 3 integers, count the number that are odd. 
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Sep 11, 2011 at 4:10:37 PM
 */
public class CountOdds {

  public static void main(String[] args) {
    // test cases
    System.out.println("Number odds of 1 2 3 = " + countNumberOdds(1, 2, 3));
    System.out.println("Number odds of 1 1 1 = " + countNumberOdds(1, 1, 1));
    System.out.println("Number odds of 2 2 2 = " + countNumberOdds(2, 2, 2));
    System.out.println("Number odds of 1 2 1 = " + countNumberOdds(1, 2, 1));
    System.out.println("Number odds of 2 1 2 = " + countNumberOdds(2, 1, 2));
    System.out.println("Number odds of 3 2 1 = " + countNumberOdds(3, 2, 1));
  }

  //-------------------------------------------------------------------
  // Preconditions:
  // Postconditions:
  public static int countNumberOdds(int n1, int n2, int n3) {
    int count = 0;

    if (n1 % 2 != 0) {
      count++;
    }
    if (n2 % 2 != 0) {
      count++;
    }
    if (n3 % 2 != 0) {
      count++;
    }

    return count;
  }
}
