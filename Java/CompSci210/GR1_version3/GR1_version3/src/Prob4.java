//
// GR 1: Prolblem 4          Limit your time to approximately 12 minutes
//
// Grade: _____ / 16 
//
import java.util.Scanner;

public class Prob4 {

  public static void main(String[] args) {
    Scanner user = new Scanner(System.in);
    int propSize = 0;
    System.out.print("Enter the minimum propeller size: ");
    propSize = user.nextInt();

    if (propSize >= 30) {
      System.out.println("Helicopter");
    } else if (propSize >= 8) {
      System.out.println("Propeller airplane");
    } else if (propSize >= 0) {
      System.out.println("Jet plane");
    } else if (propSize < 0) {
      System.out.println("Invalid Size");
    }
  }
}
