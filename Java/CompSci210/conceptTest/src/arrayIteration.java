
/**
 * Parallel arrays far faster than multidimensional
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Nov 6, 2011 at 12:03:14 PM
 */
public class arrayIteration {

  public static void main(String[] args) {
    int[] D1Arry1 = new int[10000000];
    int[] D1Arry2 = new int[D1Arry1.length];
    int[][] D2Arry = new int[2][D1Arry1.length];
    long startTime = 0;
    long endTime = 0;
    int len = D1Arry1.length;

    startTime = System.currentTimeMillis();
    for (int i = 0; i < len; i++) {
      D1Arry1[i] = i;
      D1Arry2[i] = i;
    }
    endTime = System.currentTimeMillis();
    System.out.println("1D array time:\t" + (endTime - startTime));
    
    startTime = System.currentTimeMillis();
    for (int j = 0; j < len; j++) {
      D2Arry[0][j] = j;
      D2Arry[1][j] = j;
    }
    endTime = System.currentTimeMillis();
    System.out.println("2D array time:\t" + (endTime - startTime));
  }
}
