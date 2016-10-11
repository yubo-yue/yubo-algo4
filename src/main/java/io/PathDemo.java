package io;

import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathDemo {
    public static void main(String[] args) {
        Path p1 = Paths.get("/tmp/foo");
        //Path p2 = Paths.get(args[0]);
        Path p3 = Paths.get(URI.create("file:///Users/yubo/test.txt"));
        Path p4 = FileSystems.getDefault().getPath("/tmp/bar");
        Path p5 = Paths.get(System.getProperty("user.home"), "logs", "foo.log");

        System.out.format("toString: %s%n", p3.toString());
        System.out.format("getFileName: %s%n", p3.getFileName());
        System.out.format("getName(0): %s%n", p3.getName(0));
        System.out.format("getNameCount: %d%n", p3.getNameCount());
        System.out.format("subpath(0, 2): %s%n", p3.subpath(0, 2));
        System.out.format("getParent: %s%n", p3.getParent());
        System.out.format("getRoot: %s%n", p3.getRoot());

        Path relativePath = Paths.get("sally/bar");

        System.out.println("");

        for (Path p : relativePath) {
            System.out.println(p);
        }
    }
}
