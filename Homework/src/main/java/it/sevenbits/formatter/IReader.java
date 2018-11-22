package it.sevenbits.formatter;

import java.io.IOException;

/**
 * IReader is an interface that describes stream reading symbol by symbol.
 * It has two methods.
 */
public interface IReader {
    /**
     * hasNext is a method that returns true if the stream has next element otherwise false.
     * @return boolean
     */
    boolean hasNext() throws IOException;
    /**
     * read is a method that reads one symbol from the stream.
     * @return char
     * @throws IOException
     * An input/output exception.
     */
    char read() throws IOException;

}
