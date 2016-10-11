package thread;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class ExecutorServiceDemo4 {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newWorkStealingPool();

        List<Callable<String>> tasks = Arrays.asList(
                callable("result1", 10),
                callable("result2", 5),
                callable("result3", 2)
        );

        try {
            String result = executor.invokeAny(tasks);
            System.out.println("result: " + result);
        } catch (Exception e) {
            e.printStackTrace();
        }

        executor.shutdown();
    }

    private static Callable<String> callable(final String result, long sleepSeconds) {
        return () -> {
            TimeUnit.SECONDS.sleep(sleepSeconds);
            return result;
        };
    }
}
