
/**
 * <put a program description here>
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Aug 19, 2011 at 10:53:25 AM
 */
public class Exercise11 {

  public static void main(String[] args) {
    
    int max_num = 5;
    int reps = 10;
    for (int a = 0; a < 6; a++) {
      for (int b = 0; b < reps; b++) {
        for (int c = 0; c < max_num-1; c++) {
          System.out.print(" ");
        }
        System.out.print("|");
      }
      System.out.println();
      
      for (int b = 0; b < reps; b++) {
        for (int c = 0; c < max_num; c++) {
          System.out.print(c);
        }
      }
      System.out.println();
    }
  }
}
