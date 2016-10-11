package thread;

import java.util.concurrent.TimeUnit;

public class BasicThread2 {
    public static void main(String[] args) {
        final Runnable r = () -> {
            String name = Thread.currentThread().getName();
            System.out.println("Foo " + name);

            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                //ignored
            }

            System.out.println("Bar " + name);
        };

        Thread thread = new Thread(r);
        thread.start();
    }
}
