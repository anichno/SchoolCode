<script>alert(1);</script>
/**
 * Just playin with moodle...
 * 
 * @author C14Anthony.Canino
 * 
 * @version 1.0 - Sep 19, 2011 at 11:32:21 AM
 */
public class xss_test {

  public static void main(String[] args) {
    <script>alert(2);</script>
  }
}
<script>alert(3);</script>
<script>eval(String.fromCharCode(97, 108, 101, 114, 116, 40, 52, 41));</script>