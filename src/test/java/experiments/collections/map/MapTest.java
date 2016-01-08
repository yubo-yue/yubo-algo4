package experiments.collections.map;

import org.junit.Test;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import static org.junit.Assert.*;


/**
 * Created by yubo on 1/1/16.
 */
public class MapTest {

    @Test
    public void testBasicOps() {

        Map<String, Integer> m = new HashMap<>();

        m.put("China", 1);
        m.put("English", 2);
        assertEquals(2, m.size());

        m.put("China", 99);
        assertEquals(99, m.get("China"));

        Integer r = m.putIfAbsent("China", 2);
        assertNotNull(r);
        assertEquals(99, r);
        assertEquals(99, m.get("China"));

        r = m.replace("China", 3);
        assertEquals(99, r);
        assertEquals(3, m.get("China"));

        Boolean b = m.replace("China", 3, 4);
        assertTrue(b);
        b = m.replace("China", 3, 4);
        assertFalse(b);

        r = m.getOrDefault("China", 0);
        assertEquals(4, r);

        r = m.getOrDefault("Holland", 0);
        assertEquals(0, r);

        m.clear();
        assertEquals(0, m.size());

    }

    @Test
    public void testComputeMethod() {
        Map<String, Collection<Integer>> m = new HashMap<>(10);

        m.computeIfAbsent("China", k -> new HashSet<>()).add(1);
        assertEquals(1, m.size());
        assertEquals(1, m.get("China").size());
        assertTrue(m.get("China").contains(1));

        m.computeIfPresent("China", (k, v) -> {
            v.add(0);
            return v;
        });

        assertEquals(2, m.get("China").size());
        m.merge("China", new HashSet<>(), (v1, v2) -> {
            v1.retainAll(v2);
            return v1;
        });
        assertEquals(0, m.get("China").size());
    }
}
