package thread;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemo {

    public static void main(String[] args) {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("Task is running");
            return "Hello World";
        });

        future.thenAccept(info -> System.out.println("Get result: " + info));
    }
}
