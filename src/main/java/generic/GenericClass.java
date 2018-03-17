package generic;

import java.util.ArrayList;

public class GenericClass {

    /**
     * Generic class pair, which has two type parameter. It will be instantiated by replacing its type parameter with type argument.
     * The type parameter is unbound.
     * <p>
     * Any type argument can be any concrete reference type.
     *
     * @param <X> type parameter for first element.
     * @param <Y> type parameter for second element.
     */
    private static class Pair<X, Y> {
        private X first;
        private Y second;

        public Pair(X a1, Y a2) {
            this.first = a1;
            this.second = a2;
        }

        public X getFirst() {
            return first;
        }

        public Y getSecond() {
            return second;
        }

        public void setFirst(X a1) {
            first = a1;
        }

        public void setSecond(Y a2) {
            second = a2;
        }
    }

    private static void printPair(Pair<?, ?> pair) {
        System.out.println("{" + pair.getFirst() + "," + pair.getSecond() + "}");
    }

    private static void checkRuntimeType() {
        System.out.printf("runtime type of ArrayList<String>: %s%n", new ArrayList<String>().getClass());
        System.out.printf("runtime type of ArrayList<Long>: %s%n", new ArrayList<Long>().getClass());
    }

    /**
     *
     */
    private static void arrayIsCovariance() {
        /**
         * Array of parameterized component type is illegal.
         *
         * Pair<Integer, Integer>[] intPairs = new Pair<Integer, Integer>[10];
         */
    }

    public static void main(String[] args) {
        Pair<?, ?> pair = new Pair<>("test", 1);
        printPair(pair);

        checkRuntimeType();
    }
}
