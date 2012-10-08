
/**
 * <put a program description here>
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Jan 9, 2012 at 9:27:10 AM
 */
public class Client {

  public static void main(String[] args) {
    Lamp testLamp = new Lamp();
    HeadLight testHeadLight = new HeadLight();
    DimmerLight testDimmerLight = new DimmerLight();
    
    System.out.println("testLamp: " + testLamp.isOn());
    testLamp.switchOn();
    System.out.println("testLamp: " + testLamp.isOn());
    testLamp.toggle();
    System.out.println("testLamp: " + testLamp.isOn());
    
    System.out.println("testHeadLight: " + testHeadLight.isOn());
    testHeadLight.switchLow();
    System.out.println("testHeadLight: " + testHeadLight.isOn());
    
    System.out.println("testDimmerLight: " + testDimmerLight.isOn());
    testDimmerLight.dimmer(0.5f);
    System.out.println("testDimmerLight: " + testHeadLight.isOn());
  }
}
