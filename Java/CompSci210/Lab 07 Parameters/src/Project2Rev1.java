
/**
 * calculates interest and puts it in a fancy table
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Aug 23, 2011 at 10:32:10 AM
 */
public class Project2Rev1 {

  public static void main(String[] args) {
    System.out.println("Year\t\tCurrent Balance\t\tInterest\t\tNew Deposit\t\tNew Balance");
    calcmoney(25, .065, 100, 1000);
  }

  public static void calcmoney(int years, double interest, int deposit, double curmoney) {
    for (int a = 0; a < years; a++) {
      System.out.print((a + 1) + "\t\t" + (int) curmoney + "\t\t\t" + (int) (curmoney * .065));
      curmoney = curmoney * .065 + curmoney + deposit;
      System.out.println("\t\t\t" + (int) (curmoney * .065 + 100) + "\t\t\t" + (int) curmoney);
    }
  }
}
