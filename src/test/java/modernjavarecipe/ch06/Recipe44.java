package modernjavarecipe.ch06;

import yubo.algo.common.StdOut;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Recipe44 {

    public static void main(String[] args) {
        sortByDefaultOrder();

        StdOut.println();

        sortByReverseOrder();
    }

    private static void sortByDefaultOrder() {
        StdOut.printf("Number of words of each length: %n");
        try (Stream<String> lines = Files.lines(Paths.get("/usr/share/dict/words"))) {
            lines.filter(s -> s.length() > 20)
                    .collect(groupingBy(String::length, counting()))
                    .forEach((len, num) -> StdOut.printf("%d: %d%n", len, num));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sortByReverseOrder() {
        StdOut.printf("Number of words of each length: %n");
        try (Stream<String> lines = Files.lines(Paths.get("/usr/share/dict/words"))) {
            Map<Integer, Long> map = lines.filter(s -> s.length() > 20)
                    .collect(groupingBy(String::length, counting()));

            map.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey(Comparator.reverseOrder()))
            .forEach((e) -> StdOut.printf("%d: %d%n", e.getKey(), e.getValue()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
