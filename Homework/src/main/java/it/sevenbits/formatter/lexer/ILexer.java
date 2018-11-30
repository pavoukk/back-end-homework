package it.sevenbits.formatter.lexer;

import it.sevenbits.formatter.IToken;

import java.io.IOException;

public interface ILexer {
    IToken readToken() throws IOException;
}
