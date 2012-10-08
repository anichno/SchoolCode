/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author C14Anthony.Canino
 */
public class LampTest {
  
  public LampTest() {
  }

  @BeforeClass
  public static void setUpClass() throws Exception {
  }

  @AfterClass
  public static void tearDownClass() throws Exception {
  }
  
  @Before
  public void setUp() {
  }
  
  @After
  public void tearDown() {
  }

  /**
   * Test of switchOn method, of class Lamp.
   */
  @Test
  public void testSwitchOn() {
    System.out.println("switchOn");
    Lamp instance = new Lamp();
    instance.switchOn();
  }

  /**
   * Test of switchOff method, of class Lamp.
   */
  @Test
  public void testSwitchOff() {
    System.out.println("switchOff");
    Lamp instance = new Lamp();
    instance.switchOff();
  }

  /**
   * Test of toggle method, of class Lamp.
   */
  @Test
  public void testToggle() {
    System.out.println("toggle");
    Lamp instance = new Lamp();
    instance.toggle();
  }

  /**
   * Test of isOn method, of class Lamp.
   */
  @Test
  public void testIsOn() {
    System.out.println("isOn");
    Lamp instance = new Lamp();
    boolean expResult = false;
    boolean result = instance.isOn();
    assertEquals(expResult, result);
  }
}
