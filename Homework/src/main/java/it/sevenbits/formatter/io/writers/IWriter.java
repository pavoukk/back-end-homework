package it.sevenbits.formatter;

import it.sevenbits.formatter.io.exceptions.WriterException;

/**
 * IWriter is an interface that describes stream writing symbol by symbol.
 * It has one method.
 */
public interface IWriter {
    /**
     * The method writes a symbol in the stream
     * @param ch is the writing symbol
     * @throws WriterException is an input/output exception.
     */
    void write(char ch) throws WriterException;
}
