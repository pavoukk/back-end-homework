package it.sevenbits.formatter.lexer.factories;

import it.sevenbits.formatter.lexer.ILexer;
import it.sevenbits.formatter.lexer.StateMachineLexer;
import it.sevenbits.formatter.io.readers.IReader;

/**
 * StateMachineLexerFactory is an ILexerFactory's implementation.
 */
public class StateMachineLexerFactory implements ILexerFactory {
    @Override
    public ILexer createLexer(final IReader reader) {
        return new StateMachineLexer(reader);
    }
}
