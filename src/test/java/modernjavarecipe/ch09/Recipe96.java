package modernjavarecipe.ch09;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Recipe96 {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(4);
        System.out.println(Thread.currentThread().getName());


        CompletableFuture.supplyAsync(Recipe96::sleepThenReturnString, executorService)
                .thenApply(Integer::parseInt)
                .thenApply(n -> {
                    System.out.println(Thread.currentThread().getName());return n * 2;})
                .thenAccept(System.out::println).join();

        System.out.printf("%s, Running ......", Thread.currentThread().getName());
    }

    private static String sleepThenReturnString() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ignored) {
        }
        return "42";
    }


}
