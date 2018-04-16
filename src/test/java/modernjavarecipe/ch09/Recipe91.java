package modernjavarecipe.ch09;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class Recipe91 {

    @Test
    public void sequentialStreamOf() {
        assertThat(Stream.of(1, 2, 3).isParallel(), is(false));
    }

    @Test
    public void sequentialIterateStream() {
        assertThat(Stream.iterate(1, n -> n + 1).isParallel(), is(false));
    }

    @Test
    public void sequentialGenerateStream() {
        assertThat(Stream.generate(Math::random).isParallel(), is(false));
    }

    @Test
    public void sequentialCollectionStream() {
        assertThat(Arrays.asList(3, 1, 4).stream().isParallel(), is(false));
    }

    @Test
    public void parallelStreamMethodOnCollection() {
        assertThat(Arrays.asList(3, 1, 4).parallelStream().isParallel(), is(true));
    }

    @Test
    public void parallelMethodOnStream() {
        assertThat(Stream.of(3, 1, 4).parallel().isParallel(), is(true));
    }

    @Test
    public void parallelStreamThenSequential() {
        assertThat(Arrays.asList(3, 1, 4).parallelStream().sequential().isParallel(), is(false));
    }

    @Test
    public void testOnly() {
        List<Integer> nums = Arrays.asList(3, 1, 4, 5)
                .parallelStream()
                .map(n -> n * 2)
                .peek(n -> System.out.printf("%s processing %d%n", Thread.currentThread().getName(), n))
                .sequential()
                .sorted()
                .collect(Collectors.toList());

    }
}
