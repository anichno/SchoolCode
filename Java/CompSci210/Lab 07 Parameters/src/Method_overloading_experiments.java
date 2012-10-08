
/**
 * Experiments with method overloading
 * 
 * @author Wayne.Brown
 * 
 * @version 1.0 - Aug 19, 2011 at 11:29:49 AM
 */
public class Method_overloading_experiments {

  //---------------------------------------------------------------    
  public static void main(String[] args) {
    doSomething((byte) 3);
    doSomething(3.4);
    doSomething(37);
    doSomething(50L);
    doSomething((short) 3);
  }
  
  //---------------------------------------------------------------    
  public static void doSomething(byte alpha) {
    System.out.println("the byte version was called");
  }

  //---------------------------------------------------------------    
  public static void doSomething(short alpha) {
    System.out.println("the short version was called");
  }

  //---------------------------------------------------------------    
  public static void doSomething(int alpha) {
    System.out.println("the int version was called");
  }

  //---------------------------------------------------------------    
  public static void doSomething(long alpha) {
    System.out.println("the long version was called");
  }

  //---------------------------------------------------------------    
  public static void doSomething(double alpha) {
    System.out.println("the double version was called");
  }
}
