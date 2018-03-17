package antlr.parser;

public class Test {
    public static void main(String[] args) {
        Lexer l = new ListLexer(args[0]);
        //
        Token t = l.nextToken();
        while (t.getType() != Lexer.EOF_TYPE) {
            System.out.println(t);
            t = l.nextToken();
        }

        System.out.println(t);
    }
}
