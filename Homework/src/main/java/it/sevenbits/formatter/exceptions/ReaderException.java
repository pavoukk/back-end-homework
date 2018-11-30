package it.sevenbits.formatter.exceptions;

import java.io.IOException;

/**
 * FileReaderException is an exception class.
 * It's thrown when something goes wrong in FileReader's object
 */
public class FileReaderException extends IOException {
    /**
     *
     * @param message says what actually gone wrong
     */
    public FileReaderException(final String message) {
        super(message);
    }

    /**
     *
     * @param message says what actually gone wrong
     * @param cause input cause
     */
    public FileReaderException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     *
     * @param cause input cause
     */
    public FileReaderException(final Throwable cause) {
        super(cause);
    }
}
