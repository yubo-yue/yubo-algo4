package antlr.parser;

public class ListLexer extends Lexer {

    public static final int NAME = 2;
    public static final int COMMA = 3;
    public static final int LBRACK = 4;
    public static final int RBRACK = 5;

    public static String[] TOKEN_NAMES =
            {"n/a", "<EOF>", "NAME", "COMMA", "LBRACK", "RBRACK"};

    public ListLexer(final String input) {
        super(input);
    }


    @Override
    public Token nextToken() {
        while (c != EOF) {
            switch (c) {
                case ' ':
                case '\t':
                case '\n':
                case '\r':
                    WS();
                    continue;
                case ',':
                    consume();
                    return new Token(COMMA, ",");
                case '(':
                    consume();
                    return new Token(LBRACK, "(");
                case ')':
                    consume();
                    return new Token(RBRACK, ")");
                default:
                    if (isLetter()) return NAME();
                    throw new Error("Invalid characters: " + c);
            }
        }
        return new Token(EOF_TYPE, "<EOF>");
    }

    private Token NAME() {
        StringBuilder builder = new StringBuilder();

        do {
            builder.append(c);
            consume();
        } while (isLetter());
        return new Token(NAME, builder.toString());
    }

    private void WS() {
        while (c == ' ' || c == '\t' || c == '\n' || c == '\r') consume();
    }

    private boolean isLetter() {
        return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z';
    }


    @Override
    public String getTokenName(int tokenType) {
        return TOKEN_NAMES[tokenType];
    }
}
