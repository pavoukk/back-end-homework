package it.sevenbits.formatter.lexer;

import it.sevenbits.formatter.io.readers.StringReader;
import it.sevenbits.formatter.io.readers.exceptions.ReaderException;
import it.sevenbits.formatter.io.readers.FileReader;
import it.sevenbits.formatter.lexer.exceptions.LexerException;
import it.sevenbits.formatter.lexer.token.IToken;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;

public class StateMachineLexerTest {

    @Test
    public void readTokenTestBranchesOddAmountMocked() {
        try {
            FileReader reader = Mockito.mock(FileReader.class);
            Mockito.when(reader.read()).thenReturn('{', '{', ' ', '}', '}');
            Mockito.when(reader.hasNext()).thenReturn(true, true, true, true, true, false);

            StateMachineLexer stateMachineLexer = new StateMachineLexer(reader);
            IToken token;
            String[] lexemes = {"{", "{", "}", "}"};
            String[] names = {"CURLY_BRACKET_OPEN", "CURLY_BRACKET_OPEN",
                    "CURLY_BRACKET_CLOSED", "CURLY_BRACKET_CLOSED"};

            int i = 0;
            while (stateMachineLexer.hasMoreTokens()){
                token = stateMachineLexer.readToken();
                assertEquals(lexemes[i], token.getLexeme());
                assertEquals(names[i], token.getName());
                i++;
            }
        } catch (ReaderException | LexerException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readTokenSimpleTestMocked() {
        try {
            StringReader reader = new StringReader("    \n  aaa      {  bbbb;  ccc  \n   ;   }  \n  \n  ");
            String[] lexemes = {"aaa", "{", "bbbb", ";", "ccc", ";", "}"};
            String[] names = {"ID", "CURLY_BRACKET_OPEN", "ID", "SEMICOLON",
                    "ID", "SEMICOLON", "CURLY_BRACKET_CLOSED"};
            StateMachineLexer stateMachineLexer = new StateMachineLexer(reader);
            IToken token;
            for (int i = 0; i < lexemes.length; i++) {
                token = stateMachineLexer.readToken();
                assertEquals(lexemes[i], token.getLexeme());
                assertEquals(names[i], token.getName());
            }
        } catch (LexerException e) {
            e.printStackTrace();
        }
    }
}