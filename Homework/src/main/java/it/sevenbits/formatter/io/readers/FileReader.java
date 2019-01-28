package it.sevenbits.formatter.io.readers;

import it.sevenbits.formatter.io.readers.exceptions.ReaderException;

import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.io.File;
import java.io.Closeable;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * FileReader reads info from a file
 */
public class FileReader implements IReader, Closeable, AutoCloseable {
    private Reader reader;
    private File file;

    /**
     * A constructor with one parameter
     *
     * @param path is a file path or a file name
     * @throws ReaderException is thrown if something goes wrong
     */
    public FileReader(final String path) throws ReaderException {
        this(new File(path));
    }

    /**
     * A constructor with one parameter
     *
     * @param file is a file
     * @throws ReaderException is thrown if sonething goes wrong
     */
    public FileReader(final File file) throws ReaderException {
        try {
            this.file = file;
            if (!file.exists()) {
                this.file.createNewFile();
            }
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new ReaderException(e);
        }
    }

    @Override
    public boolean hasNext() throws ReaderException {
        try {
            return reader.ready();
        } catch (IOException e) {
            throw new ReaderException(e);
        }
    }

    @Override
    public char read() throws ReaderException {
        try {
            return (char) reader.read();
        } catch (IOException e) {
            throw new ReaderException(e);
        }
    }

    @Override
    public void close() throws ReaderException {
        try {
            reader.close();
        } catch (IOException e) {
            throw new ReaderException(e);
        }
    }
}
