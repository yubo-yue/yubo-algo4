package experiments;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by yubo on 12/26/15.
 */
public class StringTest {
    @Test
    public void testStringFormat() {
        String r = String.format("%s-%s-%s-d", "1001", "1002", "PHX1", 10);
        System.out.println(r);
        assertEquals("1001-1002-PHX1-d", r);
    }
}
