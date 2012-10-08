
/**
 * <put a program description here>
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Jan 9, 2012 at 9:14:20 AM
 */
public class Lamp implements Switchable {
  private boolean lightOn;

  @Override
  public void switchOn() {
    lightOn = true;
  }

  @Override
  public void switchOff() {
    lightOn = false;
  }

  @Override
  public void toggle() {
    lightOn = !lightOn;
  }

  @Override
  public boolean isOn() {
    return lightOn;
  }
}
