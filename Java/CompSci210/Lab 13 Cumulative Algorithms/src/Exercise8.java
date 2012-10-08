
/**
 * Calculates grade average
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Sep 9, 2011 at 10:08:38 AM
 */
import java.util.Scanner;

public class Exercise8 {

  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    printGPA(input);
  }

  public static void printGPA(Scanner input) {
    int totGrades = 0;
    String name = "";
    double average = 0;
    System.out.printf("Enter a student record: ");
    name = input.next();
    totGrades = input.nextInt();
    for (int counter = 0; counter < totGrades; counter++) {
      average += input.nextInt();
    }
    average = average / totGrades;
    System.out.printf("%1$s's grade is ", name);
    System.out.printf("%.2f\n", average);
  }
}
