
/**
 * <put a program description here>
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Jan 1, 2012 at 10:03:06 PM
 */
public class VariableCreation {
  static int d;
  static int e;
  static int f;

  public static void main(String[] args) {
    final int NUM_RUNS = 100000000;
    long startTime = 0;
    long endTime = 0;
    
    startTime = System.currentTimeMillis();
    for (int i = 0; i < NUM_RUNS; i++) {
      doSomething1();
    }
    endTime = System.currentTimeMillis();
    System.out.println(endTime-startTime);
    
    startTime = System.currentTimeMillis();
    for (int i = 0; i < NUM_RUNS; i++) {
      doSomething2();
    }
    endTime = System.currentTimeMillis();
    System.out.println(endTime-startTime);
    
    VariableObject vo = new VariableObject();
    
    startTime = System.currentTimeMillis();
    for (int i = 0; i < NUM_RUNS; i++) {
      vo.doSomething1();
    }
    endTime = System.currentTimeMillis();
    System.out.println(endTime-startTime);
    
    startTime = System.currentTimeMillis();
    for (int i = 0; i < NUM_RUNS; i++) {
      vo.doSomething2();
    }
    endTime = System.currentTimeMillis();
    System.out.println(endTime-startTime);
  }
  
  public static void doSomething1() {
    int a = 1;
    int b = 2;
    int c = 3;
    
    a = 5;
    b = 10;
    c = 15;
  }
  
  public static void doSomething2() {
    d = 1;
    e = 2;
    f = 3;
    
    d = 5;
    e = 10;
    f = 15;
  }
}
