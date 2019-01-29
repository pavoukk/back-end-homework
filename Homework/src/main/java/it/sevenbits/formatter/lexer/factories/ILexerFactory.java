package it.sevenbits.formatter.lexer.factories;

import it.sevenbits.formatter.lexer.ILexer;
import it.sevenbits.formatter.io.readers.IReader;

/**
 * ILexerFactory is an interface that describes StateMachineLexer creating.
 */
public interface ILexerFactory {
    /**
     * @param reader input data for StateMachineLexer
     * @return ILexer's object
     */
    ILexer createLexer(IReader reader);
}
