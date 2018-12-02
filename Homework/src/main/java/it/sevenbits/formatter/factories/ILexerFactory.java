package it.sevenbits.formatter.factories;

import it.sevenbits.formatter.lexer.ILexer;
import it.sevenbits.formatter.readers.IReader;

/**
 * ILexerFactory is an interface that describes Lexer creating.
 */
public interface ILexerFactory {
    /**
     *
     * @param reader input data for Lexer
     * @return ILexer's object
     */
    ILexer createLexer(IReader reader);
}
