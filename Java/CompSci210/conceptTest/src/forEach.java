
/**
 * for each loop slow
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Nov 6, 2011 at 10:59:09 AM
 */
public class forEach {

  public static void main(String[] args) {
    int[] intArry = new int[10000000];
    int len = intArry.length;
    long startTime = 0;
    long endTime = 0;
    
    startTime = System.currentTimeMillis();
    for (int i = 0; i < intArry.length; i++) {
      intArry[i] = i;
    }
    endTime = System.currentTimeMillis();
    System.out.println(endTime - startTime);
    
    startTime = System.currentTimeMillis();
    for (int i = 0; i < len; i++) {
      intArry[i] = i;
    }
    endTime = System.currentTimeMillis();
    System.out.println(endTime - startTime);
    
    startTime = System.currentTimeMillis();
    for (int i : intArry) {
      intArry[i] = i;
    }
    endTime = System.currentTimeMillis();
    System.out.println(endTime - startTime);
  }
}
