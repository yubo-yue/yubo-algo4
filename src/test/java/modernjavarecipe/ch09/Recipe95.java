package modernjavarecipe.ch09;

import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.Assert.*;

@Slf4j
public class Recipe95 {
    private static final Recipe95 DEMO = new Recipe95();

    private Map<Integer, String> cache = new HashMap<>();


    private String getLocal(int id) {
        return cache.get(id);
    }

    private String getRemote(int id) {
        try {
            Thread.sleep(100);
            if (id == 666) {
                throw new RuntimeException();
            }
        } catch (InterruptedException e) {
        }

        return "name";
    }

    public CompletableFuture<String> getProduct(int id) {
        try {
            final String product = getLocal(id);
            if (Objects.nonNull(product)) {
                return CompletableFuture.completedFuture(product);
            } else {
                CompletableFuture<String> future = new CompletableFuture<>();
                String remoteProduct = getRemote(id);
                cache.put(id, remoteProduct);

                future.complete(remoteProduct);
                return future;
            }
        } catch (Exception e) {
            CompletableFuture<String> future = new CompletableFuture<>();
            future.completeExceptionally(e);

            return future;
        }
    }

    public CompletableFuture<String> getProductAsync(int id) {
        try {
            final String product = getLocal(id);
            if (Objects.nonNull(product)) {
                return CompletableFuture.completedFuture(product);
            } else {
                return CompletableFuture.supplyAsync(() -> {
                    String productRemote = getRemote(id);
                    cache.put(id, productRemote);
                    return productRemote;
                });
            }
        } catch (Exception e) {
            CompletableFuture<String> future = new CompletableFuture<>();
            future.completeExceptionally(e);

            return future;
        }
    }

    @Test(expected = ExecutionException.class)
    public void testException() throws ExecutionException, InterruptedException {
        DEMO.getProduct(666).get();
    }

    @Test
    public void testExceptionWithCause() throws InterruptedException {
        try {
            DEMO.getProduct(666).get();
            fail("Should not get here");
        } catch (ExecutionException e) {
            assertThat(e.getClass(), Matchers.equalTo(ExecutionException.class));
            assertThat(e.getCause(), Matchers.instanceOf(RuntimeException.class));
        }
    }
}
