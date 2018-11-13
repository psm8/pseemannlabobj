import org.junit.*;
import org.junit.rules.ExpectedException;

import java.io.File;

public class MammothTest{
    File file = null;
    @BeforeClass
    public void setup() {
        file = new File("data/file");
    }
    @AfterClass
    public void deleteFile() {
        file.delete();
    }

    @Test(expected=InadequateFoodException.class)
    public void throwsInadequateFoodException() throws InadequateFoodException{
        Mammoth mammoth = new Mammoth();
        mammoth.eat(new Meat());
    }

    @Test
    public void throwsInadequateFoodException2() {
        try {
            Mammoth mammoth = new Mammoth();
            mammoth.eat(new Meat());
            Assert.fail("Expected InadequateFoodException to be thrown");
        } catch( InadequateFoodException e ) {}
    }

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void throwsInadequateFoodException3() throws InadequateFoodException{
        thrown.expect(InadequateFoodException.class);
        thrown.expectMessage("I don't like meat");
        Mammoth mammoth = new Mammoth();
        mammoth.eat(new Meat());
    }
}