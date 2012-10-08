
/**
 * Experiments with parameter passing.
 * 
 * @author Wayne.Brown
 * 
 * @version 1.0 - Aug 19, 2011 at 11:12:15 AM
 */
public class Parameter_passing_experiments {

  public static void main(String[] args) {
    repeatOutput(5, '*');
    System.out.println();

    bracketText('&', 3);
    System.out.println();

    bracketText("&&", 1);
    System.out.println();

    repeatOutput(5, '&');
    System.out.println();
  }
  
  //---------------------------------------------------------------    
  public static void repeatOutput(int number, char c) {
    for (int j = 0; j < number; j++) {
      System.out.print(c);
    }
  }

  //---------------------------------------------------------------    
  public static void bracketText(char c, int numberSpaces) {
    System.out.print(c);
    for (int j = 0; j < numberSpaces; j++) {
      System.out.print(' ');
    }
    System.out.print(c);
  }

  //---------------------------------------------------------------    
  public static void bracketText(String str, int numberSpaces) {
    System.out.print(str);
    for (int j = 0; j < numberSpaces; j++) {
      System.out.print(' ');
    }
    System.out.print(str);
  }
}
