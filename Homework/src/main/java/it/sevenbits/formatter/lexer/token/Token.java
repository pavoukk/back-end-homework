package it.sevenbits.formatter.lexer.token;

import java.util.Objects;

/**
 * Token is IToken's implementation
 */
public class Token implements IToken {
    private String name;
    private String lexeme;

    /**
     * @param name   the lexeme's name
     * @param lexeme is a string
     */
    public Token(final String name, final String lexeme) {
        this.name = name;
        this.lexeme = lexeme;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getLexeme() {
        return lexeme;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Token token = (Token) o;
        return Objects.equals(name, token.name) &&
                Objects.equals(lexeme, token.lexeme);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lexeme);
    }
}
