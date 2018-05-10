package modernjavarecipe.ch09;

import org.junit.Test;

import java.util.concurrent.ForkJoinPool;
import java.util.stream.LongStream;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class Recipe93 {

    @Test
    public void commonPoolSize() {
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", "20");
        long total = LongStream.rangeClosed(1, 1_000_000).parallel().sum();

        assertThat(ForkJoinPool.commonPool().getPoolSize(), is(20));
        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism", Integer.toString(Runtime.getRuntime().availableProcessors()));

    }
}
