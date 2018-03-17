package antlr.parser.listeners;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class TestPropertyFile {

    private static class PropertyFileLoader extends PropertyFileBaseListener {
        Map<String, String> props = new HashMap<>();

        @Override
        public void exitProp(PropertyFileParser.PropContext ctx) {
            final String id = ctx.ID().getText();
            final String value = ctx.STRING().getText();
            props.put(id, value);
        }
    }

    public static void main(String[] args) {
        try (final InputStream in = TestPropertyFile.class.getResourceAsStream("/t.properties")) {
            final CharStream charStream = CharStreams.fromStream(in);

            final PropertyFileLexer lexer = new PropertyFileLexer(charStream);
            final CommonTokenStream tokenStream = new CommonTokenStream(lexer);
            final PropertyFileParser parser = new PropertyFileParser(tokenStream);

            final ParseTree parseTree = parser.file();

            final ParseTreeWalker walker = new ParseTreeWalker();
            final PropertyFileLoader loader = new PropertyFileLoader();

            walker.walk(loader, parseTree);
            System.out.println(loader.props);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
