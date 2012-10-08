
/**
 * calculates interest and puts it in a fancy table
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Aug 23, 2011 at 10:32:10 AM
 */
public class Project2 {

  public static void main(String[] args) {
    System.out.println("Year\t\tCurrent Balance\t\tInterest\t\tNew Deposit\t\tNew Balance");
    calcmoney();
  }

  public static void calcmoney() {
    int deposit = 100;
    double interest = .065;
    int years = 25;
    double curmoney = 1000;

    for (int a = 0; a < years; a++) {
      System.out.print((a + 1) + "\t\t" + (int) curmoney);
      curmoney = curmoney * .065 + curmoney + deposit;
      System.out.println("\t\t\t" + interest + "\t\t\t" + (int) (curmoney * .065 + 100) + "\t\t\t" + (int) curmoney);
    }
  }
}
