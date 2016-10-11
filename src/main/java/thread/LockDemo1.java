package thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class LockDemo1 {
    private static int count = 0;

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        IntStream.range(0, 10000).forEach(i -> executor.submit(LockDemo1::increment));

        executor.shutdown();
        System.out.println("result = " + count);
    }

    public static synchronized void increment() {
        count = count + 1;
    }
}
