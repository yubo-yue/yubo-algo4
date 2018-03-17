package antlr.parser;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class Token {
    private int type;
    private String text;

    public Token(int type, final String text) {
        this.type = type;
        this.text = text;
    }
}
