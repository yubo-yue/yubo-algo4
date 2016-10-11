package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class LockDemo2 {
    private static int count = 0;
    private static ReentrantLock lock = new ReentrantLock();

    private static void increment() {
        lock.lock();
        try {
            count ++;
        } finally {
            lock.unlock();
        }

    }
    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, 10000)
                .forEach(i -> executor.submit(LockDemo2::increment));

        executor.shutdown();
        System.out.println(count);
    }
}
