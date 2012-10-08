
/**
 * Identifies triangles
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Sep 9, 2011 at 10:53:32 AM
 */
public class Exercise9 {

  public static void main(String[] args) {
    printTriangleType(5, 7, 7);
    printTriangleType(6, 6, 6);
    printTriangleType(5, 7, 8);
    printTriangleType(2, 18, 2);
  }

  public static void printTriangleType(int a, int b, int c) throws IllegalArgumentException {
    int[] userNums = new int[]{a, b, c};
    int[] whichMax = new int[]{0, 0, 0};

    int max = Math.max(a, b);
    max = Math.max(max, c);
    //int min1 = 0,min2 = 0;
    int small = 0;
    for (int counter = 0; counter < 3; counter++) {
      if (userNums[counter] == max) {
        whichMax[counter] = 1;
        break;
      }
    }
    for (int counter = 0; counter < 3; counter++) {
      if (whichMax[counter] != 1) {
        small += userNums[counter];
      }
    }
    if (small < max) {
      throw new IllegalArgumentException("Invalid Triangle");
    }


    if (a == b && a == c && b == c) {
      System.out.println("equilateral");
      return;
    }
    if (a == b || a == c || b == c) {
      System.out.println("isosceles");
      return;
    }
    if (a != b && a != c && b != c) {
      System.out.println("scalene");
      return;
    }
  }
}
