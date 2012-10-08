
/**
 * Final Exam: Problem 5     Limit your time to approximately 23 minutes
 *
 * Grade: _____ / 25
 */
public class Prob05 {

  public static void main(String[] args) {
  }
}

//===================================================================
// Create your Sphere class below
class Sphere {

  private double x = 0;
  private double y = 0;
  private double z = 0;
  private double radius = 0;

  public Sphere(double x, double y, double z, double radius) {
    this.x = x;
    this.y = y;
    this.z = z;
    this.radius = radius;
  }

  public Sphere() {
    this(0, 0, 0, 1);
  }

  public double getvolume() {
    return (4 / 3) * Math.PI * Math.pow(radius, 3);
  }

  public double getX() {
    return x;
  }

  public double getY() {
    return y;
  }

  public double getZ() {
    return z;
  }

  public double getRadius() {
    return radius;
  }

  @Override
  public String toString() {
    return "X: " + x + "\r\nY: " + y + "\r\nZ: " + z + "\r\nRadius: " + radius;
  }

  public boolean equals(Sphere testSphere) {
    if (testSphere.getX() == getX()
        && testSphere.getY() == getY()
        && testSphere.getZ() == getZ()
        && testSphere.getRadius() == getRadius()) {
      return true;
    } else {
      return false;
    }
  }
}