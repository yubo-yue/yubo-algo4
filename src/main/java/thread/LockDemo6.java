package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * Optimistic Lock
 */
public class LockDemo6 {
    public static void main(String[] args) {
        final ExecutorService executorService = Executors.newFixedThreadPool(2);
        final StampedLock lock = new StampedLock();

        executorService.submit(() -> {
            long stamp = lock.tryOptimisticRead();
            try {
                System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
                TimeUnit.SECONDS.sleep(1);
                System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
                TimeUnit.SECONDS.sleep(2);
                System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock(stamp);
            }
        });

        executorService.submit(() -> {
            long stamp = lock.writeLock();
            try {
                System.out.println("Write Lock acquired");
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock(stamp);
                System.out.println("Write Done");
            }
        });

        executorService.shutdown();
    }
}
