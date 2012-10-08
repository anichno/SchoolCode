
import java.util.Arrays;


/**
 * <put a program description here>
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Oct 13, 2011 at 9:23:28 AM
 */
public class Self_check_problems {

  public static void main(String[] args) {
    int[][] data = new int[4][7];
    for (int counter = 0; counter < data[0].length; counter++) {
      data[3][counter] = counter+1;
    }
    System.out.println(Arrays.toString(data[3]));
    
    int[][] table = new int[5][10];
    for (int i = 0; i < table.length; i++) {
      for (int j = 0; j < table[0].length; j++) {
        table[i][j] = i*j;
      }
    }
    System.out.println(Arrays.toString(table[2]));
    
    int[][] matrix = new int[6][8];
    for (int i = 0; i < matrix.length; i++) {
      for (int j = 0; j < matrix[0].length; j++) {
        matrix[i][j] = (int) (Math.random()*10);
      }
    }
    System.out.println(Arrays.toString(matrix[2]));
    System.out.println(Arrays.toString(matrix[5]));
    for (int counter = 0, temp = 0; counter < matrix[5].length; counter++) {
      temp = matrix[5][counter];
      matrix[5][counter] = matrix[2][counter];
      matrix[2][counter] = temp;
    }
    System.out.println("post copy");
    System.out.println(Arrays.toString(matrix[2]));
    System.out.println(Arrays.toString(matrix[5]));
    
    int[][] jagged = new int[5][];
    System.out.println("jagged array");
    for (int counter = 0, curNum = 1; counter < jagged.length; counter++) {
      jagged[counter] = new int[counter+1];
      for (int counter2 = 0; counter2 < jagged[counter].length; counter2++) {
        jagged[counter][counter2] = curNum;
        curNum++;
      }
      System.out.println(Arrays.toString(jagged[counter]));
    }
  }
}

