package it.sevenbits.formatter.exceptions;

import java.io.IOException;

/**
 * ReaderException is an exception class.
 * It's thrown when something goes wrong in FileReader's object
 */
public class ReaderException extends IOException {
    /**
     *
     * @param message says what actually gone wrong
     */
    public ReaderException(final String message) {
        super(message);
    }

    /**
     *
     * @param message says what actually gone wrong
     * @param cause input cause
     */
    public ReaderException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     *
     * @param cause input cause
     */
    public ReaderException(final Throwable cause) {
        super(cause);
    }
}
