package io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FilesDemo {
    public static void main(String[] args) {
        Charset charset = Charset.forName("US-ASCII");
        String s = "foo";

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get("/tmp/aaa.txt"), charset)) {
            writer.write(s, 0, s.length());
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }

    }
}
