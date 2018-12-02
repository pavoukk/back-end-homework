package it.sevenbits.formatter.lexer;

import it.sevenbits.formatter.exceptions.ReaderException;
import it.sevenbits.formatter.readers.FileReader;
import it.sevenbits.formatter.token.IToken;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class LexerTest {

    @Test
    public void readTokenTestBranchesOddAmountMocked() {
        try {
            FileReader reader = Mockito.mock(FileReader.class);
            Mockito.when(reader.read()).thenReturn('{', '{', ' ', '}', '}');
            Mockito.when(reader.hasNext()).thenReturn(true, true, true, true, true, false);
            Lexer lexer = new Lexer(reader);
            IToken token;
            String[] lexemes = {"{", "{", "}", "}"};
            String[] names = {"opening bracket", "opening bracket", "closing bracket", "closing bracket"};
            for (int i = 0; i < names.length; i++) {
                token = lexer.readToken();
                assertEquals(token.getLexeme(), lexemes[i]);
                assertEquals(token.getName(), names[i]);
            }
        } catch (ReaderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readTokenSimpleTestMocked() {
        FileReader reader = Mockito.mock(FileReader.class);
        try {
            Mockito.when(reader.read()).thenReturn('a', 'a', 'a', '{', 'b', 'b', 'b', 'b', ';', 'c', 'c', 'c',
                    ';', '}');
            Mockito.when(reader.hasNext()).thenReturn(true, true, true, true, true, true, true, true, true,
                    true, true, true, true, true, true, true, true, false);
            String[] lexemes = {"aaa", "{", "bbbb", ";", "ccc", ";", "}"};
            String[] names = {"string", "opening bracket", "string", "semicolon", "string", "semicolon",
                    "closing bracket"};
            Lexer lexer = new Lexer(reader);
            IToken token;
            for (int i = 0; i < lexemes.length; i++) {
                token = lexer.readToken();
                assertEquals(token.getLexeme(), lexemes[i]);
                assertEquals(token.getName(), names[i]);
            }
        } catch (ReaderException e) {
            e.printStackTrace();
        }
    }
}