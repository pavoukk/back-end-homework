package it.sevenbits.formatter.formatters.exceptions;

/**
 * FormatterException is an exception class.
 * It's thrown when something goes wrong in Formatter's object
 */
public class FormatterException extends Exception {

    /**
     * @param message says what actually gone wrong
     */
    public FormatterException(final String message) {
        super(message);
    }

    /**
     * @param message says what actually gone wrong
     * @param cause   input cause
     */
    public FormatterException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause input cause
     */
    public FormatterException(final Throwable cause) {
        super(cause);
    }
}
