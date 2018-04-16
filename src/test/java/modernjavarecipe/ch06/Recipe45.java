package modernjavarecipe.ch06;

import yubo.algo.common.StdOut;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Recipe45 {
    public static void main(String[] args) {
        partitionBy();
    }

    private static void partitionBy() {
        List<String> strings = Arrays.asList("This", "is", "a", "long"
                , "list", "of", "strings", "to"
                , "use", "as", "a", "demo");

        Map<Boolean, List<String>> lengthMap = strings.stream().collect(Collectors.partitioningBy(s -> s.length() % 2 == 0));
        lengthMap.forEach((key, value) -> StdOut.printf("%5s: %s%n", key, value));
    }
}
