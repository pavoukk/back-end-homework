package it.sevenbits.formatter;

import java.io.IOException;

public class FileReaderException extends IOException {
    public FileReaderException(String message) {
        super(message);
    }
    public FileReaderException(String message, Throwable cause) {
        super(message, cause);
    }
    public FileReaderException(Throwable cause) {
        super(cause);
    }
}
