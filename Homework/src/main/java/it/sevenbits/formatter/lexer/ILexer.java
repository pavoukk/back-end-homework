package it.sevenbits.formatter.lexer;

import it.sevenbits.formatter.lexer.exceptions.LexerException;
import it.sevenbits.formatter.lexer.token.IToken;

/**
 * ILexer is an interface that is used to read some source's info and translate it into tokens.
 */
public interface ILexer {
    /**
     *
     * @return a single token
     * @throws LexerException if something goes wrong
     */
    IToken readToken() throws LexerException;

    boolean hasMoreTokens() throws LexerException;
}
