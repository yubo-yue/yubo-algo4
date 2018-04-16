package modernjavarecipe.ch06;

import lombok.NonNull;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparingInt;
import static java.util.Comparator.naturalOrder;

public class SortingExample {

    public static List<String> lengthSortUsingSorted(@NonNull final List<String> strings) {
        return strings.stream()
                .sorted((s1, s2) -> s1.length() - s2.length())
                .collect(Collectors.toList());
    }

    public static List<String> lengthSortUsingComparator(@NonNull final List<String> strings) {
        return strings.stream()
                .sorted(comparingInt(String::length))
                .collect(Collectors.toList());
    }

    public static List<String> lengthSortThenAlphaSort(@NonNull final List<String> strings) {
        return strings.stream()
                .sorted(comparingInt(String::length).thenComparing(naturalOrder()))
                .collect(Collectors.toList());
    }
}
