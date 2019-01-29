package it.sevenbits.formatter.lexer.exceptions;

/**
 * LexerException is an exception class.
 * It's thrown when something goes wrong in Formatter's object
 */
public class LexerException extends Exception {

    /**
     * @param message says what actually gone wrong
     */
    public LexerException(final String message) {
        super(message);
    }

    /**
     * @param message says what actually gone wrong
     * @param cause   input cause
     */
    public LexerException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause input cause
     */
    public LexerException(final Throwable cause) {
        super(cause);
    }
}
