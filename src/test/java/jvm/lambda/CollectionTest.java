package jvm.lambda;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;
import static java.util.Arrays.*;

/**
 * Created by yubo on 1/8/16.
 */
public class CollectionTest {

    @Test
    public void testOrdering() {
        List<Integer> numbers = asList(1, 2, 3, 4);
        List<Integer> sameOrder = asList(1, 2, 3, 4);

        assertEquals(sameOrder, numbers);
    }
}
