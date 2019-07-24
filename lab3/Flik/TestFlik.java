import static org.junit.Assert.*;
import org.junit.Test;
public class TestFlik {

    @Test
    public void testIsSameNumber() {  // should be non-static
        assertTrue(Flik.isSameNumber(200, 200));
        assertFalse(Flik.isSameNumber(2, 3));
        assertTrue(Flik.isSameNumber(0, 0));
    }

}
