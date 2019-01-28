package it.sevenbits.formatter.formatters;

import it.sevenbits.formatter.io.readers.IReader;
import it.sevenbits.formatter.io.writers.IWriter;

import java.io.IOException;

/**
 * IFormatter is an interface that is used to format reader's info and write it to writer
 */
public interface IFormatter {

    /**
     * @param reader is input info
     * @param writer is output info
     * @throws IOException if something goes wrong
     */
    void format(IReader reader, IWriter writer) throws IOException;
}
