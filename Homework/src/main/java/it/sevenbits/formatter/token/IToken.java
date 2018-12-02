package it.sevenbits.formatter.token;

/**
 *IToken is an interface that is used to store some lexeme and it's name
 */
public interface IToken {

    /**
     *
     * @return the lexeme's name
     */
    String getName();

    /**
     *
     * @return the lexeme
     */
    String getLexeme();
}
