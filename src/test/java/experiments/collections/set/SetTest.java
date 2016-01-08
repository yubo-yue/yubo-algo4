package experiments.collections.set;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Set in JDK has 3 implementations
 * 1. HashSet - no guarantee to sort.
 * 2. TreeSet - sorted set. (red-black tree based implementations)
 * 3. LinkedHashSet - keep the insertion sort
 * <p>
 * Created by yubo on 12/27/15.
 */
public class SetTest {

    @Test
    public void testLinkedSet() {
        String[] input = {"a", "b", "c", "b", "a", "e", "x", "q", "s"};
        String[] expectedOrderedOutput = {"a", "b", "c", "e", "q", "s", "x"};
        // expected set result is a, b, c, e, q, s, x
        Set<String> distinctWords = Arrays.asList(input).stream().collect(Collectors.toSet());
        assertEquals(7, distinctWords.size());
        System.out.println(distinctWords);
        Set<String> distinctWordsTreeSet = Arrays.asList(input).stream().collect(Collectors.toCollection(TreeSet::new));
        assertEquals(7, distinctWordsTreeSet.size());
        assertEquals(expectedOrderedOutput, distinctWordsTreeSet.toArray(new String[0]));
        System.out.println(distinctWordsTreeSet);
    }

    /**
     * Test basic operations of set data structure.
     * add, remove, retain, size, etc...
     */
    @Test
    public void testBasicOps() {
        Set<Integer> si = new HashSet<>();
        assertTrue(si.add(1));
        assertTrue(si.add(2));
        assertTrue(si.add(3));
        assertFalse(si.add(1));
        assertEquals(3, si.size());

        Integer[] actual = si.toArray(new Integer[0]);
        Integer[] expected = {1, 2, 3};
        assertEquals(expected, actual);

        assertTrue(si.contains(1));
        assertFalse(si.contains(4));

        assertTrue(si.retainAll(Arrays.asList(1, 2)));
        assertEquals(2, si.size());
        assertFalse(si.retainAll(Arrays.asList(1, 2)));
        assertEquals(2, si.size());

        assertTrue(si.remove(1));
        assertEquals(1, si.size());
        assertFalse(si.remove(4));
        assertEquals(1, si.size());

    }

    @Test
    public void testNavigableSet() {
        NavigableSet<Integer> si = new TreeSet<>();
        assertTrue(si.addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7)));
        assertEquals(7, si.size());

        assertNull(si.ceiling(8));
        assertEquals(4, si.ceiling(4));

        assertNull(si.floor(0));
        assertEquals(4, si.floor(4));

        Iterator<Integer> ri = si.descendingIterator();
        Integer previous = Integer.MAX_VALUE;
        while (ri.hasNext()) {
            Integer next = ri.next();
            assertTrue(previous > next);
            previous = next;
        }

        SortedSet<Integer> headSet = si.headSet(4);
        assertEquals(3, headSet.size());

        SortedSet<Integer> headSet1 = si.headSet(4, true);
        assertEquals(4, headSet1.size());

        assertNull(si.higher(7));
        assertEquals(5, si.higher(4));

        assertEquals(7, si.pollLast());
        assertEquals(1, si.pollFirst());

        assertEquals(5, si.size());
    }

}
