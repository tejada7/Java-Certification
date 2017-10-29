package b;

/**
 * Created by Favio on 24/8/2017.
 */
import a.AccessTest;
public class AccessTester extends AccessTest {
    public int a = 2;
    public static void main(String[] args) throws Exception {
        AccessTest ref = new AccessTest();
        System.out.println(ref.member);
    }

    public AccessTester() {
    }

    public void method1() throws Exception {
        System.out.println("From AccessTester");
    }
}
