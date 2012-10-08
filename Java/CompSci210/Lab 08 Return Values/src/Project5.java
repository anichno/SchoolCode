
/**
 * Calculates the distance between Nashville and Los Angeles
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Aug 25, 2011 at 11:03:57 AM
 */
public class Project5 {

  public static void main(String[] args) {
    double[] Nashville = new double[]{36.12, -86.67};
    double[] LosAngeles = new double[]{33.94, -118.40};
    double distance = 0;

    Nashville[0] = Math.toRadians(Nashville[0]);
    Nashville[1] = Math.toRadians(Nashville[1]);


    LosAngeles[0] = Math.toRadians(LosAngeles[0]);
    LosAngeles[1] = Math.toRadians(LosAngeles[1]);

    distance = Math.acos(Math.sin(Nashville[0]) * Math.sin(LosAngeles[0])
                         + Math.cos(Nashville[0]) * Math.cos(LosAngeles[0]) * Math.cos(Nashville[1] - LosAngeles[1]));
    System.out.println(distance * 6372.795);
  }
}
