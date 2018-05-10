package modernjavarecipe.ch09;

import org.junit.Test;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

public class Recipe96_1 {

    @Test
    public void testCompose() throws ExecutionException, InterruptedException {
        int x = 2;
        int y = 3;

        final CompletableFuture<Integer> completableFuture =
                CompletableFuture.supplyAsync(() -> x)
                        .thenCompose(n -> CompletableFuture.supplyAsync(() -> n + y));

        assertThat(completableFuture.get(), is(equalTo(5)));
    }

    @Test
    public void testCombine() throws ExecutionException, InterruptedException {
        int x = 2;
        int y = 3;

        final CompletableFuture<Integer> completableFuture =
                CompletableFuture.supplyAsync(() -> x)
                        .thenCombine(CompletableFuture.supplyAsync(() -> y), (n1, n2) -> n1 + n2);

        assertThat(completableFuture.get(), is(equalTo(5)));
    }

    @Test
    public void handleWithException() throws ExecutionException, InterruptedException {
        String num = "a";

        CompletableFuture<Integer> completableFuture = getIntegerCompletableFuture(num);
        assertThat(completableFuture.get(), is(0));
    }

    private static CompletableFuture<Integer> getIntegerCompletableFuture(String num) {
        return CompletableFuture.supplyAsync(() -> Integer.parseInt(num))
                .handle((val, exc) -> Objects.nonNull(val) ? val : 0);
    }
}
