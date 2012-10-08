
/**
 * Returns whether a quantity of coins will contain pennies
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Sep 19, 2011 at 4:24:18 PM
 */
public class Has_pennies {

  public static void main(String[] args) {
    // tests
    for (int money = 1; money < 23; money++) {
      System.out.printf("Does %d cents contain pennies? %b\n", money, hasPennies(money));
    }
  }

  //-------------------------------------------------------------------
  public static boolean hasPennies(int cents) {
    return !(cents % 5 == 0);
  }
}
