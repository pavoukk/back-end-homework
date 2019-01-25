package it.sevenbits.formatter.lexer;

import it.sevenbits.formatter.exceptions.ReaderException;
import it.sevenbits.formatter.token.IToken;
import it.sevenbits.formatter.token.Token;
import it.sevenbits.formatter.readers.IReader;

import java.util.HashMap;
import java.util.Map;

/**
 * Lexer is an ILexer's implementation.
 * It gets IReader object while creating and translates object's info into tokens.
 */
public class Lexer implements ILexer {
    private final char SPACE = ' ';
    private final char NEW_LINE = '\n';
//    private final String STRING_NAME = "string";
    private final String CHARACTER = "character";

    private IReader reader;
    private Map<Character, String> names;
    private char markBuf;

    public Lexer(final IReader reader) {
        this.reader = reader;
        names = new HashMap<>();
//        names.put('\n', "new line");
        names.put('{', "opening bracket");
        names.put('}', "closing bracket");
        names.put(';', "semicolon");
        names.put('(', "opening parenthesis");
        names.put(')', "closing parenthesis");
        markBuf = ' ';
    }

    @Override
    public IToken readToken() throws ReaderException {
        StringBuilder lexeme = new StringBuilder();

    }

    /*
    @Override
    public IToken readToken() throws ReaderException {
        char buffer = SPACE;
        StringBuilder lexeme = new StringBuilder();
        boolean append = false;
        if (markBuf != SPACE && markBuf != NEW_LINE) {
            lexeme.append(markBuf);
            String str = names.get(markBuf);
            markBuf = SPACE;
            return new Token(str, lexeme.toString());
        }
        while (reader.hasNext()) {
            buffer = reader.read();
            String name = names.get(buffer);
            if (name != null && lexeme.length() == 0) {
                lexeme.append(buffer);
                return new Token(name, lexeme.toString());
            }
            if ((buffer == SPACE || buffer == NEW_LINE || name != null) && lexeme.length() > 0) {
                markBuf = buffer;
                return new Token(STRING_NAME, lexeme.toString());
            }

            if (!(buffer == SPACE || buffer == NEW_LINE)) {
                lexeme.append(buffer);
            }
        }
        String name = names.get(buffer);
        if (name != null) {
            return new Token(name, lexeme.toString());
        }
        return new Token(STRING_NAME, lexeme.toString());
    }*/



    @Override
    public boolean hasMoreTokens() throws ReaderException {
        return reader.hasNext();
    }
}
