package yubo.algo.common;

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Locale;

public final class StdOut {
    private static final String CHARSET_NAME = StandardCharsets.UTF_8.name();
    private static final Locale LOCALE = Locale.US;

    private static PrintWriter out;

    static {
        try {
            out = new PrintWriter(new OutputStreamWriter(System.out, CHARSET_NAME), true);
        } catch (UnsupportedEncodingException e) {
            System.err.println(e);
        }
    }

    private StdOut() {

    }

    public static void close() {
        out.close();
    }

    public static void println() {
        out.println();
    }

    public static void println(Object o) {
        out.println(o);
    }

    public static void println(char c) {
        out.println(c);
    }

    public static void println(double x) {
        out.println(x);
    }

    public static void println(float x) {
        out.println(x);
    }

    public static void println(int x) {
        out.println(x);
    }

    public static void println(long x) {
        out.println(x);
    }

    public static void println(short x) {
        out.println(x);
    }

    public static void println(byte x) {
        out.println(x);
    }

    public static void print() {
        out.flush();
    }

    public static void print(Object x) {
        out.print(x);
        out.flush();
    }

    public static void print(boolean x) {
        out.print(x);
        out.flush();
    }

    public static void print(char x) {
        out.print(x);
        out.flush();
    }

    public static void print(double x) {
        out.print(x);
        out.flush();
    }

    public static void print(float x) {
        out.print(x);
        out.flush();
    }

    public static void print(int x) {
        out.print(x);
        out.flush();
    }

    public static void print(int[] arr) {
        Arrays.stream(arr).forEach(out::println);
    }

    public static void print(long x) {
        out.print(x);
        out.flush();
    }

    public static void print(short x) {
        out.print(x);
        out.flush();
    }

    public static void print(byte x) {
        out.print(x);
        out.flush();
    }

    public static void printf(String format, Object... args) {
        out.printf(LOCALE, format, args);
        out.flush();
    }

    public static void printf(Locale locale, String format, Object... args) {
        out.printf(locale, format, args);
        out.flush();
    }

    public static void main(String[] args) {
        StdOut.println("Hello World");
        StdOut.println(17);
        StdOut.println(true);
        StdOut.printf(Locale.FRANCE, "%.2f\n", 1.0/7.0);
    }
}

