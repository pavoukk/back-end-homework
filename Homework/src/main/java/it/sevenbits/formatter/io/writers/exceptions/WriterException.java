package it.sevenbits.formatter.io.writers;

import java.io.IOException;

/**
 * WriterException is an exception class that is thrown if something goes wrong in FileWriter's object.
 */
public class WriterException extends IOException {
    /**
     *
     * @param message says what actually gone wrong
     */
    public WriterException(final String message) {
        super(message);
    }

    /**
     *
     * @param message says whart actually gone wrong
     * @param cause input cause
     */
    public WriterException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     *
     * @param cause input cause
     */
    public WriterException(final Throwable cause) {
        super(cause);
    }
}
