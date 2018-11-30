package it.sevenbits.formatter.lexer;

import it.sevenbits.formatter.IToken;
import it.sevenbits.formatter.Token;
import it.sevenbits.formatter.readers.IReader;
import it.sevenbits.formatter.readers.StringReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lexer implements ILexer {
    private final int spaceAmount = 4;
    private final char space = ' ';
    private final char newLine = '\n';
    private final char openingBracket = '{';
    private final char closingBracket = '}';
    private final char semicolon = ';';
    private final String name = "string";
    private IReader reader;
    private char buffer;
    private List<Character> lexemes;
    private Map<Character, String> names;
    private char buf;

    public Lexer(IReader reader, List<Character> lexemes, Map<Character, String> names) {
        this.reader = reader;
        this.lexemes = lexemes;
        this.names = names;
        buf = ' ';
    }

    @Override
    public IToken readToken() throws IOException {
        StringBuilder lexeme = new StringBuilder();
        boolean append = false;
        if (buf!= ' ') {
            lexeme.append(buf);
            String str = names.get(buf);
            buf = ' ';
            return new Token(str, lexeme.toString());
        }
        while (reader.hasNext()) {
            buffer = reader.read();
            if (lexemes.contains(buffer) && lexeme.length() == 0) {
                lexeme.append(buffer);
                return new Token(names.get(buffer), lexeme.toString());
            }
            if ((buffer == space || lexemes.contains(buffer)) && lexeme.length() > 0) {
                buf = buffer;
                return new Token(name, lexeme.toString());
            }

            if (buffer != space) {
                lexeme.append(buffer);
            }
//            if (lexemes.contains(buffer) && lexeme.length() == 1) {
//                return new Token(name, lexeme.toString());
//            }
        }
        if (lexemes.contains(buffer)) {
            return new Token(names.get(buffer), lexeme.toString());
        }
        return new Token(name, lexeme.toString());
    }

    public static void main(String[] args) throws IOException {
        List<IToken> tokens = new ArrayList<>();
        IReader reader = new StringReader("a  public  void func (){int a = 0; int b = 3; int c= 4; if(a== b){ b = a; return b;}");
        List<Character> lexemes = new ArrayList<>();
//            lexemes.add(' ');
        lexemes.add('\n');
        lexemes.add('{');
        lexemes.add('}');
        lexemes.add(';');
        lexemes.add('(');
        lexemes.add(')');
        Map<Character, String> names = new HashMap<>();
//            names.put(' ', "space");
        names.put('\n', "new line");
        names.put('{', "opening bracket");
        names.put('}', "closing bracket");
        names.put(';', "semicolon");
        names.put('(', "opening parenthesis");
        names.put(')', "closing parenthesis");
        Lexer lexer = new Lexer(reader, lexemes, names);
        while (reader.hasNext()) {
            tokens.add(lexer.readToken());
        }
        System.out.println(tokens.toString());
    }
}
