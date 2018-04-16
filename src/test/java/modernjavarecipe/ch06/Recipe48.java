package modernjavarecipe.ch06;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toSet;

/**
 * Create a immutable collection sample. In Java 9, new factory method added such as List.of(), which greatly simplify such works.
 */
public class Recipe48 {

    public static <T> List<T> createImmutableList(T... ts) {
        return Arrays.stream(ts)
                .collect(collectingAndThen(toList(), Collections::unmodifiableList));
    }

    public static <T> Set<T> createImmutableSet(T... ts) {
        return Arrays.stream(ts)
                .collect(Collectors.collectingAndThen(toSet(), Collections::unmodifiableSet));
    }
}
