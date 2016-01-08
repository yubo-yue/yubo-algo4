package jvm.lambda;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.*;

/**
 * Created by yubo on 12/16/15.
 */

public class StreamTest {

    @Test
    public void testStream() {
        IntStream stream = IntStream.of(1, 1, 2, 3);
        assertEquals("Sum is not equal to 14", 14, stream.map(v -> v * 2).sum());

        int[] values = {1, 2, 3, 4, 5};
        IntStream stream1 = Arrays.stream(values, 1, 3);
        assertEquals("Sum is not equal to 5", 5, stream1.sum());

    }

    @Test
    public void testStreamRange() {
        IntStream zeroToNinetyNine = IntStream.range(0, 100);
        assertEquals(100L, zeroToNinetyNine.count());

        IntStream zeroToHundred = IntStream.rangeClosed(0, 100);
        assertEquals(101L, zeroToHundred.count());
    }

    @Test
    public void testCurrentJar() throws IOException {
        final File f = new File(StreamTest.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        String parentDir = f.getParent();
        System.out.println(parentDir);
        System.out.println(f.getCanonicalPath());
        assertNotNull(parentDir);
    }

    @Test
    public void testMapToInt() throws IOException {
        Stream<String> lines = Files.lines(Paths.get("pom.xml"));
        IntStream lengths = lines.filter(l -> l.trim().length() > 0).mapToInt(l -> l.length());
        assertTrue(lengths.count() > 0);
    }


}
