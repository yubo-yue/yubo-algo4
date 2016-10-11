package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class Semaphore1 {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        final Semaphore semaphore = new Semaphore(5);
        Runnable longRunningTask = () -> {
            boolean permit = false;
            try {
                permit = semaphore.tryAcquire();
                if (permit) {
                    System.out.println("Semaphore acquired");
                    TimeUnit.SECONDS.sleep(5);
                } else {
                    System.out.println("Could not acquire semaphore");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                if (permit) {
                    semaphore.release();
                }
            }
        };
        IntStream.range(0, 10).forEach(i -> executorService.submit(longRunningTask));

        executorService.shutdown();
    }
}
