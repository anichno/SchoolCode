
/**
 * Takes lengths of the sides of a triangle and outputs the respective angles
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Aug 29, 2011 at 11:11:11 AM
 */
import java.util.Scanner;

public class Project4 {

  public static void main(String[] args) {
    Scanner user = new Scanner(System.in);
    System.out.print("Please enter side A: ");
    double lengthA = user.nextDouble();
    System.out.print("Please enter side B: ");
    double lengthB = user.nextDouble();
    System.out.print("Please enter side C: ");
    double lengthC = user.nextDouble();

    double angleA = Math.acos(((lengthB) * (lengthB) + (lengthC) * (lengthC)
                               - (lengthA) * (lengthA)) / (2 * lengthC * lengthB));
    double angleB = Math.acos(((lengthA) * (lengthA) + (lengthC) * (lengthC)
                               - (lengthB) * (lengthB)) / (2 * lengthA * lengthC));
    double angleC = Math.acos(((lengthA) * (lengthA) + (lengthB) * (lengthB)
                               - (lengthC) * (lengthC)) / (2 * lengthA * lengthB));

    System.out.println("Angle A is: " + Math.toDegrees(angleA));
    System.out.println("Angle B is: " + Math.toDegrees(angleB));
    System.out.println("Angle C is: " + Math.toDegrees(angleC));
  }
}
