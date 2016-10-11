package stream;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Demo1 {
    public static void main(String[] args) {
        IntStream.rangeClosed(0, 10).forEach(num -> System.out.print(num));
        System.out.println("");
        IntStream.range(0, 10).forEach(num -> System.out.print(num));

        Stream.of("This", "is", "Java8", "Stream").forEach(s -> System.out.println(s));

        String[] stringArray = {"This", "is", "Java8", "Stream"};
        Arrays.stream(stringArray).forEach(System.out::println);
    }
}
