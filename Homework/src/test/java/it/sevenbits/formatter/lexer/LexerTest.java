package it.sevenbits.formatter.lexer;

import it.sevenbits.formatter.io.readers.StringReader;
import it.sevenbits.formatter.io.readers.exceptions.ReaderException;
import it.sevenbits.formatter.io.readers.FileReader;
import it.sevenbits.formatter.lexer.token.IToken;
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
            String[] names = {"CURLY_BRACKET_OPEN", "CURLY_BRACKET_OPEN", "CURLY_BRACKET_CLOSED", "CURLY_BRACKET_CLOSED"};

            int i = 0;
            while (lexer.hasMoreTokens()){
                token = lexer.readToken();
                assertEquals(lexemes[i], token.getLexeme());
                assertEquals(names[i], token.getName());
                i++;
            }
        } catch (ReaderException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readTokenSimpleTestMocked() {
        try {
            StringReader reader = new StringReader("    \n  aaa      {  bbbb;  ccc  \n   ;   }  \n  \n  ");
            String[] lexemes = {"aaa", "{", "bbbb", ";", "ccc", ";", "}"};
            String[] names = {"ID", "CURLY_BRACKET_OPEN", "ID", "SEMICOLON", "ID", "SEMICOLON",
                    "CURLY_BRACKET_CLOSED"};
            Lexer lexer = new Lexer(reader);
            IToken token;
            for (int i = 0; i < lexemes.length; i++) {
                token = lexer.readToken();
                assertEquals(lexemes[i], token.getLexeme());
                assertEquals(names[i], token.getName());
            }
        } catch (ReaderException e) {
            e.printStackTrace();
        }
    }
}