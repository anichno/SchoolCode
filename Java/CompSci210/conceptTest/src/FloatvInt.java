
/**
 * float way slow, try to do math with int.
 * long and double same, but slower than int
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Nov 6, 2011 at 12:42:37 PM
 */
public class FloatvInt {

  public static void main(String[] args) {
    long startTime = 0;
    long endTime = 0;
    float num1 = 0;
    int num2 = 0;
    double num3 = 0;
    long num4 = 0;
    
    startTime = System.currentTimeMillis();
    for(int i = 0; i < 10000000; i++) {
      num1 += 0.2f*2;
    }
    endTime = System.currentTimeMillis();
    System.out.println("float time:\t" + (endTime - startTime) + "\tresult = " + num1);
    
    startTime = System.currentTimeMillis();
    for(int i = 0; i < 10000000; i++) {
      num2 += 2*2;
    }
    num2 /= 10;
    endTime = System.currentTimeMillis();
    System.out.println("int time:\t" + (endTime - startTime) + "\tresult = " + num2);
    
    startTime = System.currentTimeMillis();
    for(int i = 0; i < 10000000; i++) {
      num3 += 0.2*2;
    }
    endTime = System.currentTimeMillis();
    System.out.println("double time:\t" + (endTime - startTime) + "\tresult = " + num3);
    
    startTime = System.currentTimeMillis();
    for(int i = 0; i < 10000000; i++) {
      num4 += 2*2;
    }
    num4 /= 10;
    endTime = System.currentTimeMillis();
    System.out.println("long time:\t" + (endTime - startTime) + "\tresult = " + num4);
  }
}
