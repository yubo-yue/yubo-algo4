package jvm.lambda;

import org.junit.Test;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.*;
import static java.util.Arrays.*;
import static java.util.stream.Collectors.*;

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

    @Test
    public void testCollect() {
        List<Integer> numbers = asList(1, 2, 3, 4);
        Set<Integer> iSet = numbers.stream().map(i -> i * 2).collect(toSet());
        assertEquals(4, iSet.size());
        TreeSet<Integer> itSet = numbers.stream().map(i -> i * 3).collect(toCollection(TreeSet::new));
        assertEquals(4, itSet.size());
    }
}
