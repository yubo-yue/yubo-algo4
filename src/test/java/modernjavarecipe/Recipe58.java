package modernjavarecipe;

import yubo.algo.common.StdOut;

import java.util.function.Function;

public class Recipe58 {
    public static void main(String[] args) {
        Function<Integer, Integer> add2 = t -> t + 2;
        Function<Integer, Integer> mult3 = t -> t * 3;

        Function<Integer, Integer> mult3add2 = add2.compose(mult3);
        Function<Integer, Integer> add2mult3 = add2.andThen(mult3);

        StdOut.printf("%d * 3 + 2 = %d%n", 3, mult3add2.apply(3));
        StdOut.printf("(%d + 2) * 3 = %d%n", 3, add2mult3.apply(3));
    }
}
