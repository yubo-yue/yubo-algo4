package io;

import java.io.*;

public class CopyLines {
    public static void main(String[] args) {
        try (BufferedReader in = new BufferedReader(new FileReader("/tmp/xanadu.txt"));
             PrintWriter out = new PrintWriter(new FileWriter("/tmp/characteroutput.txt"))) {
            String line = null;
            while ((line = in.readLine()) != null) {
                out.println(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
