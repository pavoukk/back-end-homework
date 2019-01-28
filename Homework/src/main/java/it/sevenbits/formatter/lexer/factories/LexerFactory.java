package it.sevenbits.formatter.factories;

import it.sevenbits.formatter.lexer.ILexer;
import it.sevenbits.formatter.lexer.Lexer;
import it.sevenbits.formatter.io.readers.IReader;

/**
 * LexerFactory is an ILexerFactory's implementation.
 */
public class LexerFactory implements ILexerFactory {
    @Override
    public ILexer createLexer(final IReader reader) {
        return new Lexer(reader);
    }
}
