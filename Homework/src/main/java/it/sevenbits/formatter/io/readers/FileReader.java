package it.sevenbits.formatter.io.readers;

import it.sevenbits.formatter.io.readers.exceptions.ReaderException;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * FileReader reads info from a file
 */
public class FileReader implements IReader, Closeable, AutoCloseable {
    private Reader reader;
    private File file;

    /**
     *
     * @param path is a file path or a file name
     * @throws ReaderException is thrown if something goes wrong
     */
    public FileReader(final String path) throws ReaderException {
        try {
            this.file = new File(path);
//            reader = new BufferedReader(new java.io.FileReader(path));
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(path), StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new ReaderException(e);
        }
    }

    /**
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
//            reader = new BufferedReader(new java.io.FileReader(file));
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
