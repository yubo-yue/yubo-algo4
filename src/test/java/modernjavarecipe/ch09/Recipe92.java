package modernjavarecipe.ch09;

import java.time.Duration;
import java.time.Instant;
import java.util.stream.IntStream;

public class Recipe92 {

    public static void main(String[] args) {
        final Instant before = Instant.now();

        int total = IntStream.of(3, 1, 4, 1).parallel()
                .map(Recipe92::doubleIt)
                .sum();

        Instant after = Instant.now();

        Duration duration = Duration.between(before, after);

        System.out.printf("Total of doubles = %d%n", total);
        System.out.printf("Time = %dms%n", duration.toMillis());
    }

    public static int doubleIt(int n) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return n * 2;
    }
}
