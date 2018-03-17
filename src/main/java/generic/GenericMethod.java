package generic;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Generic method: a method with type parameter.
 */
public class GenericMethod {
    public static <A extends Comparable<A>> A max(Collection<A> xs) {
        Iterator<A> xi = xs.iterator();

        A w = xi.next();
        while (xi.hasNext()) {
            A x = xi.next();
            if (w.compareTo(x) < 0) w = x;
        }

        return w;
    }

    public static void main(String[] args) {
        LinkedList<Long> list = new LinkedList<>();
        list.add(0L);
        list.add(1L);

        Long y = GenericMethod.max(list);
        System.out.println(y);
    }
}
