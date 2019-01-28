package it.sevenbits.formatter.io.readers;

import it.sevenbits.formatter.io.readers.exceptions.ReaderException;

/**
 * IReader is an interface that describes stream reading symbol by symbol.
 * It has two methods.
 */
public interface IReader {
    /**
     * hasNext is a method that returns true if the stream has next element otherwise false.
     *
     * @return boolean
     * @throws ReaderException if something goes wrong
     */
    boolean hasNext() throws ReaderException;

    /**
     * read is a method that reads one symbol from the stream.
     *
     * @return char
     * @throws ReaderException An input/output exception.
     */
    char read() throws ReaderException;

}
