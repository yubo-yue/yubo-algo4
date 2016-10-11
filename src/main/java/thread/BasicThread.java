package thread;

public class BasicThread {
    public static void main(String[] args) {
        final Runnable r = () -> {
            String threadName = Thread.currentThread().getName();
            System.out.println("Hello " + threadName);
        };

        r.run();

        Thread thread = new Thread(r);
        thread.start();

        System.out.println("Done!");
    }
}
