import appl.Calculator;
import org.junit.Assert;
import org.junit.Test;

public class test {
    @Test
    public void test() {
        Calculator c1 = new Calculator();
        double tmp = c1.getResult();
        c1.Add(2);
        Assert.assertTrue(c1.getResult() == tmp + 2);
    }
}
