package jvm;

/**
 * Created by yubo on 12/8/15.
 * Allocate fixed size array, and immediately lets it go out of scope.
 */
public class SimpleAllocator {
    private static int TOTAL_ALLOC = 100000000;
    private static int ALLOC_SIZE = 1024 * 1024 / 2;

    public static void main(String[] args) throws InterruptedException {
        for (int ii = 0; ii < TOTAL_ALLOC / ALLOC_SIZE; ii++) {
            //TimeUnit.SECONDS.sleep(60);
            byte[] data = new byte[ALLOC_SIZE];
        }
    }
}
