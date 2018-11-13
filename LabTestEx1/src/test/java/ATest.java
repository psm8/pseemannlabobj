
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;




public class ATest {
    A a1 = null;

    @Before
    public void setup() {
        a1 = new A();
    }
    @Test
    public void firstTest() {
        Assert.assertTrue(a1.met(1)=="pierwszy");
    }
    @Test
    public void secondTest() {
        Assert.assertTrue(a1.met(2)=="drugi");
    }
    @Test
    public void thirdTest() {
        Assert.assertTrue(a1.met(6)=="inny");
    }
}

