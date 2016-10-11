package io;

import java.nio.file.Path;
import java.nio.file.Paths;

public class FileTestDemo {
    public static void main(String[] args) {
        Path p1 = Paths.get("/home/joe/foo");
        System.out.format("%s%n", p1.resolve("bar"));

        Path p2 = Paths.get("joe");
        Path p3 = Paths.get("sally");

        Path p2_to_p3 = p2.relativize(p3);
        Path p3_to_p2 = p3.relativize(p2);

        System.out.format("%s%n", p2_to_p3);
        System.out.format("%s%n", p3_to_p2);
    }
}
