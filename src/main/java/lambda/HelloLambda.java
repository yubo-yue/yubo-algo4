package lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by yubo on 12/12/15.
 */
public class HelloLambda {
    public static void main(String[] args) {
        List<Integer> a = Arrays.asList(7, 2, 3, 6, 5, 4, 9, 0, 5);

        a.forEach(x -> {
            int y = x / 2;
            System.out.println(y);
        });

        /**
         * Lambda express is kinda function, not object.
         * It can be assigend to functional interface which has only one abstract method defined.
         * When assigned, and conversion from lambda express to functional interface occured.
         */
        Comparator<Integer> comp = (first, second) ->
                Integer.compare(first, second);

        Integer[] ax = a.toArray(new Integer[0]);
        Arrays.sort(ax, comp);

        Arrays.asList(ax).forEach(System.out::println);
    }
}
