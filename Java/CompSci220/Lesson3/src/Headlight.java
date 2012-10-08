
/**
 * <put a program description here>
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Jan 9, 2012 at 9:20:37 AM
 */
public class HeadLight implements Switchable {
  private int lightVal = 0;

  @Override
  public void switchOn() {
    lightVal = 2;
  }
  
  public void switchLow() {
    lightVal = 1;
  }

  @Override
  public void switchOff() {
    lightVal = 0;
  }

  @Override
  public void toggle() {
    lightVal++;
    if (lightVal > 2) {
      lightVal = 0;
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
