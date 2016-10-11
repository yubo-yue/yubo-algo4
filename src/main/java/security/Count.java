package security;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Count {
    public static void countChars(InputStream in) throws IOException {
        int count = 0;

        while (in.read() != -1)
            count++;

        System.out.println("Counted " + count + " chars.");
    }

    public static void main(String[] args) throws Exception {
        if (args.length >= 1)
            countChars(new FileInputStream(args[0]));
        else
            System.err.println("Usage: Count filename");
    }
}
