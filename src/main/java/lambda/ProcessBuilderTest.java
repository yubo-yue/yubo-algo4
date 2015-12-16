package lambda;

import java.io.IOException;

/**
 * Created by yubo on 12/16/15.
 */
public class ProcessBuilderTest {
    public static void main(String[] args) throws IOException, InterruptedException {
        ProcessBuilder builder = new ProcessBuilder("ls", "-al");
        builder.inheritIO();
        builder.start().waitFor();
    }
}
