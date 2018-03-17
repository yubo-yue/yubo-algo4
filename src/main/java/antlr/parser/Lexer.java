package antlr.parser;

public abstract class Lexer {
    public static final char EOF = (char) -1;
    public static final int EOF_TYPE = -1;

    protected String input;
    /**
     * current position to input. take input as sequence of chars.
     */
    protected int pos;
    /**
     * char under current position.
     */
    protected char c;

    public Lexer(final String input) {
        this.input = input;
        c = input.charAt(pos);
    }

    /**
     * skip char under {@literal pos} and move pos to next chars.
     */
    public void consume() {
        pos++;
        if (pos >= input.length()) c = EOF;
        else c = input.charAt(pos);
    }

    public void match(char x) {
        if (c == x) consume();
        else throw new Error("Expecting " + x + ", found " + c);
    }

    public abstract Token nextToken();

    public abstract String getTokenName(int tokenType);

}
