package io;

import java.io.Console;

public class Password {
    public static void main(String[] args) {
        Console c = System.console();

        if (null == c) {
            System.err.println("No console.");
            System.exit(1);
        }

        String login = c.readLine("Enter your login:");
        char[] oldPassword = c.readPassword("Enter your old password:");

    }
}
