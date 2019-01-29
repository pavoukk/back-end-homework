package it.sevenbits.formatter.lexer;

import it.sevenbits.formatter.io.readers.IReader;
import it.sevenbits.formatter.io.readers.StringReader;
import it.sevenbits.formatter.io.readers.exceptions.ReaderException;
import it.sevenbits.formatter.lexer.exceptions.LexerException;
import it.sevenbits.formatter.lexer.token.IToken;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class LexerTest {
    @Test
    public void nonEmptyBufferTest() throws LexerException {
        IReader reader = new StringReader("africa;");
        ILexer lexer = new Lexer(reader);

        IToken token1 = lexer.readToken();
        assertEquals("string", token1.getName());
        assertEquals("africa", token1.getLexeme());

        IToken token2 = lexer.readToken();
        assertEquals("semicolon", token2.getName());
        assertEquals(";", token2.getLexeme());
    }

    @Test(expected = LexerException.class)
    public void readTokenExceptionTest() throws ReaderException, LexerException {
        IReader readerMocked = mock(IReader.class);
        when(readerMocked.hasNext()).thenThrow(ReaderException.class);
        Lexer lexer = new Lexer(readerMocked);
        lexer.readToken();
    }

    @Test(expected = LexerException.class)
    public void hasMoreTokensExceptionTest() throws ReaderException, LexerException {
        IReader readerMocked = mock(IReader.class);
        when(readerMocked.hasNext()).thenThrow(ReaderException.class);
        Lexer lexer = new Lexer(readerMocked);
        lexer.hasMoreTokens();
    }

}