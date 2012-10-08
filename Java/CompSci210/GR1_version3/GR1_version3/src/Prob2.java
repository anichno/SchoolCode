//
// GR 1: Prolblem 2          Limit your time to approximately 7 minutes
//
// Grade: _____ / 10 
//
public class Prob2 {

  public static void main(String[] args) {
    double radius = 1;
    double height = 2;
    double surfArea = 0;
    double volume = 0;

    surfArea = (2 * Math.PI * radius * height) + (2 * Math.PI * (radius * radius));
    volume = Math.PI * (radius * radius) * height;

    System.out.printf("Surface area: %f rounds to %.0f\n", surfArea, surfArea);
    System.out.printf("Volume%7s %f rounds to %.0f\n", ":", volume, volume);

  }
}
