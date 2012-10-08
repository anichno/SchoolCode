
/**
 * GR 2: Problem 5          Limit your time to approximately 12 minutes
 *
 * Grade:           / 16
 */
public class Prob5 {

  public static void main(String[] args) {
    // Declare a variable whose data type is a 2D array of doubles.
    double[][] GRarray = null;

    // For the variable you declared, create a rectangular array of
    // doubles with 5 rows and 6 columns.
    GRarray = new double[5][6];

    // Implement a set of loops that initializes every element in the
    // 2D array to consecutive multiples of 1/4, starting at 0. The  
    // element values must increase across each row and continue in the  
    // first column of the next row.
    double curAdd = 0;
    for (int index = 0; index < GRarray.length; index++) {
      for (int index2 = 0; index2 < GRarray[0].length; index2++) {
        GRarray[index][index2] = curAdd;
        curAdd += .25;
      }
    }

    // Finally, display the elements of the 2D array, where each
    // element is displayed with 2 digits after the decimal point.
    // However, if a value has no fractional part, print a series of
    // dashes instead of the number.
    for (int index = 0; index < GRarray.length; index++) {
      for (int index2 = 0; index2 < GRarray[0].length; index2++) {
        if (GRarray[index][index2] == (int) GRarray[index][index2]) {
          System.out.print("-----\t");
        } else {
          System.out.printf("%5.2f\t", GRarray[index][index2]);
        }
      }
      System.out.println();
    }

  }
}
