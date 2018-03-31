package yubo.algo.common;

import org.apache.commons.lang.StringUtils;

import java.io.BufferedInputStream;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.regex.Pattern;

/**
 * Reading data from Std input stream
 */
public final class StdIn {
    private static final String CHARSET_NAME = StandardCharsets.UTF_8.name();
    private static final Locale LOCALE = Locale.US;
    private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\p{javaWhitespace}+"); //Equivalent to Character.isWhitespace
    private static final Pattern EMPTY_PATTERN = Pattern.compile("");
    private static final Pattern EVERYTHING_PATTERN = Pattern.compile("\\A"); //Boundary matcher, the beginning of the input

    private static Scanner scanner;

    private StdIn() {

    }

    public static boolean isEmpty() {
        return !scanner.hasNext();
    }

    public static boolean hasNextLine() {
        return scanner.hasNextLine();
    }

    public static boolean hasNextChar() {
        scanner.useDelimiter(EMPTY_PATTERN);
        boolean result = scanner.hasNext();
        scanner.useDelimiter(WHITESPACE_PATTERN);
        return result;
    }

    public static String readLine() {
        String line;
        try {
            line = scanner.nextLine();
        } catch (Exception ignore) {
            line = null;
        }

        return line;
    }

    public static char readChar() {
        try {
            scanner.useDelimiter(EMPTY_PATTERN);
            String ch = scanner.next();
            scanner.useDelimiter(WHITESPACE_PATTERN);
            return ch.charAt(0);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("attempts to read a 'char' value from standard input, but there are no more tokens available");
        }
    }

    public static String readAll() {
        if (!scanner.hasNextLine()) {
            return StringUtils.EMPTY;
        }

        scanner.useDelimiter(EVERYTHING_PATTERN);
        String result = scanner.next();
        scanner.useDelimiter(WHITESPACE_PATTERN);

        return result;
    }

    public static String readString() {
        try {
            return scanner.next();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("attempts to read a 'String' value from standard input, but there are no more tokens available");
        }
    }

    public static int readInt() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            final String token = scanner.next();
            throw new InputMismatchException("Expect int, but get is " + token);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("attemps to read an 'int' value from standard input, but there are no more tokens available");
        }
    }

    public static double readDouble() {
        try {
            return scanner.nextDouble();
        } catch (InputMismatchException e) {
            final String token = scanner.next();
            throw new InputMismatchException("Expect double, but get is " + token);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("attempts to read a 'double' value from standard input, but there are no more tokens available");
        }
    }

    public static float readFloat() {
        try {
            return scanner.nextFloat();
        } catch (InputMismatchException e) {
            final String token = scanner.next();
            throw new InputMismatchException("Expect float, but get " + token);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("attempts to read a 'float' value from standard input, but there are no more tokens available");
        }
    }

    public static long readLong() {
        try {
            return scanner.nextLong();
        } catch (InputMismatchException e) {
            final String token = scanner.next();
            throw new InputMismatchException("Expect float, but get " + token);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("attempts to read a 'long' value from standard input, but there are no more tokens available");
        }
    }

    public static short readShort() {
        try {
            return scanner.nextShort();
        } catch (InputMismatchException e) {
            final String token = scanner.next();
            throw new InputMismatchException("attempts to read a 'short' value from standard input, but the next token is \"" + token + "\"");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("attempts to read a 'short' value from standard input, but there are no more tokens available");
        }
    }

    public static byte readByte() {
        try {
            return scanner.nextByte();
        } catch (InputMismatchException e) {
            final String token = scanner.next();
            throw new InputMismatchException("Except byte, but get " + token);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("attempts to read a 'byte' value from standard input, but there are no more tokens available");
        }
    }

    public static boolean readBoolean() {
        try {
            final String token = readString();
            if ("true".equalsIgnoreCase(token)) return true;
            if ("false".equalsIgnoreCase(token)) return false;
            if ("1".equals(token)) return true;
            if ("0".equals(token)) return false;
            throw new InputMismatchException("Expect boolean, but get " + token);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException("no more tokens available");
        }
    }

    public static String[] readAllStrings() {
        final String[] tokens = WHITESPACE_PATTERN.split(readAll());
        if (tokens.length == 0 || tokens[0].length() > 0)
            return tokens;

        // don't include first token if it is leading whitespace
        String[] decapitokens = new String[tokens.length - 1];
        for (int i = 0; i < tokens.length - 1; i++)
            decapitokens[i] = tokens[i + 1];
        return decapitokens;
    }

    public static String[] readAllLines() {
        final List<String> lines = new ArrayList<>();
        while (hasNextLine()) {
            lines.add(readLine());
        }

        return lines.toArray(new String[lines.size()]);
    }

    public static int[] readAllInts() {
        final String[] fields = readAllStrings();

        int[] vals = new int[fields.length];

        for (int i = 0; i < fields.length; i++) {
            vals[i] = Integer.parseInt(fields[i]);
        }

        return vals;
    }

    public static long[] readAllLongs() {
        final String[] fields = readAllStrings();
        long[] vals = new long[fields.length];

        for (int i = 0; i < fields.length; i++) {
            vals[i] = Long.parseLong(fields[i]);
        }

        return vals;
    }

    public static double[] readAllDoubles() {
        final String[] fields = readAllStrings();
        double[] vals = new double[fields.length];

        for (int i = 0; i < fields.length; i++) {
            vals[i] = Double.parseDouble(fields[i]);
        }

        return vals;
    }

    static {
        resync();
    }

    private static void resync() {
        setScanner(new Scanner(new BufferedInputStream(System.in), CHARSET_NAME));
    }


    public static void setScanner(final Scanner scanner) {
        StdIn.scanner = scanner;
        StdIn.scanner.useLocale(LOCALE);
    }

    public static void main(String[] args) {
        while (true) {
            char c = StdIn.readChar();
            if (c == 'q') break;
            StdOut.print(c);
        }
    }
}
