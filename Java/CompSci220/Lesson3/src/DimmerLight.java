
/**
 * <put a program description here>
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Jan 9, 2012 at 9:23:31 AM
 */
public class DimmerLight implements Switchable {
  private float lightVal = 0;

  @Override
  public void switchOn() {
    lightVal = 1;
  }

  @Override
  public void switchOff() {
    lightVal = 0;
  }
  
  public void dimmer(float newLightVal) {
    lightVal = newLightVal;
  }

  @Override
  public void toggle() {
    if (lightVal > 0) {
      lightVal = 0;
    }
    else {
      lightVal = 1;
    }
  }

  @Override
  public boolean isOn() {
    if (lightVal > 0) {
      return true;
    }
    else {
      return false;
    }
  }
}
