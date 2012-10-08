/**
 * A comparison of classes that use different variable scope.
 * 
 * @author Wayne.Brown
 * 
 * @version 1.0 - Aug 15, 2011 at 1:22:47 PM
 */
//======================================================================
public class Scope_version1 {
  
  public static void main(String[] args) {
    
    for (int j = 0; j < 5; j++) {
      System.out.println("j = " + j);
    }

    for (int j = 10; j <= 20; j++) {
      System.out.println("j = " + j);
    }
  }
}

//======================================================================
class Scope_version2 {

    public static void main(String[] args) {
    
    int j;

    for (j = 0; j < 5; j++) {
      System.out.println("j = " + j);
    }

    for (j = 10; j <= 20; j++) {
      System.out.println("j = " + j);
    }
  }
}

//======================================================================
class Scope_version3 {
  
  public static int j;

  public static void main(String[] args) { 

    for (j = 0; j < 5; j++) {
      System.out.println("j = " + j);
    }

    for (j = 10; j <= 20; j++) {
      System.out.println("j = " + j);
    }
  }
  
  public static void anotherMethod() {
    for (j=10; j<20; j++) {
      System.out.println("j = " + j);
    }
  }
}

//======================================================================
// Potential GR questions:

// 1. Which version of the code is better and why?

//Number 1, variables are declared in the scope they will be used


// 2. What "good programming property" does version 1 of the code have, 
//    as compared to versions 2 and 3?

//It uses scope to keep the variable close to where it needs to be used