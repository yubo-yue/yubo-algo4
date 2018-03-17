package generic;

import java.util.List;

/**
 * Type parameter is a place holder for a type argument. Each type parameter is replaced by a type argument when an
 * instantiation of the generic type, such as Comparable\<Object\> or Comparable\< ? extends Long \>.
 * <p>
 * Type parameter bound: is used to further describe a type parameter. It restricts the set of types that can be used
 * as a type arguments and give access to the non-static methods that it defines.
 */
public class TypeParameter {
    private static class Hashtable<Key, Data> {
        private static class Entry<Key, Data> {
            private Key key;
            private Data value;
            private int hash;
            private Entry<Key, Data> next;
        }

        private Entry<Key, Data>[] table;

        public Data get(Key key) {
            int hash = key.hashCode();

            for (Entry<Key, Data> e = table[hash & 0x07]; e != null; e = e.next) {
                if ((e.hash == hash) && e.key.equals(key)) {
                    return e.value;
                }
            }

            return null;
        }

    }

    private static class X<T extends List<? extends Number>> {
        public void someMethod(T t) {
//            t.add(new Long(0L));
            Number n = t.remove(0);
        }
    }
}
