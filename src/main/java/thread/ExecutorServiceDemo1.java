package thread;

import java.util.concurrent.*;

public class ExecutorServiceDemo1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Integer> task = () -> {
            TimeUnit.SECONDS.sleep(10);
            return 123;
        };

        ExecutorService executor = Executors.newFixedThreadPool(1);
        Future<Integer> future = executor.submit(task);

        System.out.println("future done? " + future.isDone());
        System.out.println("future cancelled? " + future.isCancelled());

        try {
            Integer result = future.get();
            System.out.println("future done? " + future.isDone());
            System.out.println("result? " + result);
        } catch (InterruptedException e) {
            System.err.println("interrupted");
        } catch (ExecutionException e) {
            //ignore
        }

        try {
            executor.shutdown();
            executor.awaitTermination(10, TimeUnit.MICROSECONDS);
        } catch (InterruptedException e) {

        }
        future.get();
    }
}
