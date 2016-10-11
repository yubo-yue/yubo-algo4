package io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class ScanXan {
    public static void main(String[] args) {
        try (Scanner s = new Scanner(new BufferedReader(new FileReader("/tmp/xanadu.txt")))) {
            s.useDelimiter(",\\s*");
            while (s.hasNext()) {
                System.out.println(s.next());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
