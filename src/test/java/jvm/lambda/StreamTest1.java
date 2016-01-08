package jvm.lambda;

import lambda.Track;
import org.junit.Test;

import static org.junit.Assert.*;


import java.util.*;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by yubo on 12/17/15.
 */
public class StreamTest1 {

    public static void info(Logger logger, Supplier<String> message) {
        if (logger.isLoggable(Level.INFO)) {
            logger.info(message.get());
        }
    }

    @Test
    public void testFlatmap() {
        List<Integer> together = Stream.of(
                Arrays.asList(1, 2), Arrays.asList(3, 4)
        ).flatMap(
                numbers -> numbers.stream()
        ).collect(
                Collectors.toList()
        );
        assertEquals(Arrays.asList(1, 2, 3, 4), together);
    }

    @Test
    public void testMin() {
        List<Track> tracks = Arrays.asList(
                new Track("Bakai", 524),
                new Track("Vialet for your furs", 378),
                new Track("Time Wars", 451)
        );

        Track shortestTrack = tracks.stream().min(Comparator.comparing(track -> track.getLength())).get();
        assertEquals(tracks.get(1), shortestTrack);
    }

    @Test
    public void testOrderElements() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        List<Integer> sameOrder = numbers.stream().sorted().collect(Collectors.toList());
        assertEquals(numbers, sameOrder);
    }

    @Test
    public void testEncounterOrder() {
        Set<Integer> numbers = new HashSet<>(Arrays.asList(4, 3, 2, 1));
        List<Integer> sameOrder = numbers.stream().collect(Collectors.toList());

        assertEquals(Arrays.asList(1, 2, 3, 4), sameOrder);

    }

    @Test
    public void testPreseverEncounterOrder() {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4);
        List<Integer> stillOrdered = numbers.stream().map(x -> x + 1).collect(Collectors.toList());
        assertEquals(Arrays.asList(2, 3, 4, 5), stillOrdered);

        Set<Integer> unordered = new HashSet<>(numbers);
        List<Integer> stillUnordered = unordered.stream().map(x -> x + 1).collect(Collectors.toList());
    }
}
