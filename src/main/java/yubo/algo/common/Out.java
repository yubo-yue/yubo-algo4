package yubo.algo.common;

import lombok.NonNull;

import java.io.*;
import java.net.Socket;
import java.util.Locale;

public final class Out {

    // force Unicode UTF-8 encoding; otherwise it's system dependent
    private static final String CHARSET_NAME = "UTF-8";

    // assume language = English, country = US for consistency with In
    private static final Locale LOCALE = Locale.US;

    private PrintWriter out;

    /**
     * Initializes an output stream from a {@link OutputStream}.
     *
     * @param os the {@code OutputStream}
     */
    public Out(@NonNull final OutputStream os) {
        try {
            final OutputStreamWriter osw = new OutputStreamWriter(os, CHARSET_NAME);
            out = new PrintWriter(osw, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Out() {
        this(System.out);
    }


    public Out(@NonNull final Socket socket) {
        try {
            final OutputStream os = socket.getOutputStream();
            final OutputStreamWriter osw = new OutputStreamWriter(os, CHARSET_NAME);
            out = new PrintWriter(osw, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Out(@NonNull final String filename) {
        try {
            final OutputStream os = new FileOutputStream(filename);
            final OutputStreamWriter osw = new OutputStreamWriter(os, CHARSET_NAME);
            out = new PrintWriter(osw, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        out.close();
    }

    public void println() {
        out.println();
    }

    public void println(Object x) {
        out.println(x);
    }

    public void println(boolean x) {
        out.println(x);
    }

    public void println(char x) {
        out.println(x);
    }

    public void println(double x) {
        out.println(x);
    }

    public void println(float x) {
        out.println(x);
    }

    /**
     * Prints an integer to this output stream and then terminates the line.
     *
     * @param x the integer to print
     */
    public void println(int x) {
        out.println(x);
    }

    /**
     * Prints a long to this output stream and then terminates the line.
     *
     * @param x the long to print
     */
    public void println(long x) {
        out.println(x);
    }

    /**
     * Prints a byte to this output stream and then terminates the line.
     * <p>
     * To write binary data, see {@link BinaryOut}.
     *
     * @param x the byte to print
     */
    public void println(byte x) {
        out.println(x);
    }


    /**
     * Flushes this output stream.
     */
    public void print() {
        out.flush();
    }

    /**
     * Prints an object to this output stream and flushes this output stream.
     *
     * @param x the object to print
     */
    public void print(Object x) {
        out.print(x);
        out.flush();
    }

    /**
     * Prints a boolean to this output stream and flushes this output stream.
     *
     * @param x the boolean to print
     */
    public void print(boolean x) {
        out.print(x);
        out.flush();
    }

    /**
     * Prints a character to this output stream and flushes this output stream.
     *
     * @param x the character to print
     */
    public void print(char x) {
        out.print(x);
        out.flush();
    }

    /**
     * Prints a double to this output stream and flushes this output stream.
     *
     * @param x the double to print
     */
    public void print(double x) {
        out.print(x);
        out.flush();
    }

    /**
     * Prints a float to this output stream and flushes this output stream.
     *
     * @param x the float to print
     */
    public void print(float x) {
        out.print(x);
        out.flush();
    }

    /**
     * Prints an integer to this output stream and flushes this output stream.
     *
     * @param x the integer to print
     */
    public void print(int x) {
        out.print(x);
        out.flush();
    }

    /**
     * Prints a long integer to this output stream and flushes this output stream.
     *
     * @param x the long integer to print
     */
    public void print(long x) {
        out.print(x);
        out.flush();
    }

    /**
     * Prints a byte to this output stream and flushes this output stream.
     *
     * @param x the byte to print
     */
    public void print(byte x) {
        out.print(x);
        out.flush();
    }

    /**
     * Prints a formatted string to this output stream, using the specified format
     * string and arguments, and then flushes this output stream.
     *
     * @param format the format string
     * @param args   the arguments accompanying the format string
     */
    public void printf(String format, Object... args) {
        out.printf(LOCALE, format, args);
        out.flush();
    }

    /**
     * Prints a formatted string to this output stream, using the specified
     * locale, format string, and arguments, and then flushes this output stream.
     *
     * @param locale the locale
     * @param format the format string
     * @param args   the arguments accompanying the format string
     */
    public void printf(Locale locale, String format, Object... args) {
        out.printf(locale, format, args);
        out.flush();
    }


    public static void main(String[] args) {
        Out out;

        // write to stdout
        out = new Out();
        out.println("Test 1");
        out.close();

        // write to a file
        out = new Out("/tmp/test.txt");
        out.println("Test 2");
        out.close();
    }

}
