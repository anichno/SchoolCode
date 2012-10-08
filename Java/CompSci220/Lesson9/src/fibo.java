
/**
 * <put a program description here>
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Jan 30, 2012 at 9:03:36 AM
 */
public class fibo {

  public static void main(String[] args) {
    printFibo(10);
  }
  
  public static int printFibo(int finalNum) {
    int b = 0;
    if (finalNum > 1) {
      int a = printFibo(finalNum-1);
      b = a+finalNum;
      System.out.print(b + " ");
    }
    return b;
  }
}
