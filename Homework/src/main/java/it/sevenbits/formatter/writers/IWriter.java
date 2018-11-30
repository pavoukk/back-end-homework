package it.sevenbits.formatter.writers;

import java.io.IOException;
/**
 * IWriter is an interface that describes stream writing symbol by symbol.
 * It has one method.
 */
public interface IWriter {
    /**
     * The method writes a symbol in the stream
     * @param ch is the writing symbol
     * @throws IOException is an input/output exception.
     */
    void write(char ch) throws IOException;
}
