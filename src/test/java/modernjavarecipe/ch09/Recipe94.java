package modernjavarecipe.ch09;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Recipe94 {

    public static void main(String[] args) {
        final ExecutorService executorService = Executors.newCachedThreadPool();

        final Future<String> future = executorService.submit(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            return "Hello World";
        });

        System.out.println("Processing...");

        future.cancel(true);

        getIfNotCancelled(future);

    }

    public static void getIfNotCancelled(final Future<String> future) {
        try {
            if (!future.isCancelled()) {
                System.out.println(future.get());
            } else {
                System.out.println("Cancelled");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
