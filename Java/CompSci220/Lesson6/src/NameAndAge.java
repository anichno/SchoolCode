
/**
 * <put a program description here>
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Jan 18, 2012 at 9:34:04 AM
 */
public class NameAndAge {

  private String name = null;
  private int age = 0;
  
  public void setName(String newName) {
    name = newName;
  }
  
  public void setAge(int newAge) {
    age = newAge;
  }
  
  public String getName() {
    return name;
  }
  
  public int getAge() {
    return age;
  }
  
  public String toString() {
    return "Name: " + name + " age: " + age;
  }
}
