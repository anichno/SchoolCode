
/**
 * divide and multiply equal in speed
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Nov 6, 2011 at 12:52:43 PM
 */
public class DividevMult {

  public static void main(String[] args) {
    long startTime = 0;
    long endTime = 0;
    int num = 0;
    
    startTime = System.currentTimeMillis();
    for(int i = 0; i < 100000000; i++) {
      num = 10/5;
    }
    endTime = System.currentTimeMillis();
    System.out.println("divide time:\t" + (endTime - startTime));
    
    startTime = System.currentTimeMillis();
    for(int i = 0; i < 100000000; i++) {
      num = (int) (10*0.2);
    }
    endTime = System.currentTimeMillis();
    System.out.println("multiply time:\t" + (endTime - startTime));
    
    System.out.println(0-1);
    System.out.println(1-1);
    System.out.println(2-1);
  }
}
