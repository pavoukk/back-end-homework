package it.sevenbits.formatter.readers;

import it.sevenbits.formatter.exceptions.FileReaderException;

import java.io.*;

public class FileReader implements IReader, Closeable, AutoCloseable {
    private Reader reader;
    private File file;
    private int count;

    public FileReader(final String path) throws FileReaderException {
        try {
            this.file = new File(path);
            count = 0;
            if (!file.exists()) {
                file.createNewFile();
            }
            reader = new BufferedReader(new java.io.FileReader(path));
        } catch (IOException e) {
            throw new FileReaderException(e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
        }
    }

    public FileReader(final File file) throws IOException {
        try {
            this.file = file;
            count = 0;
            if (!file.exists()) {
                this.file.createNewFile();
            }
            reader = new BufferedReader(new java.io.FileReader(file));
        } catch (IOException e) {
            throw new FileReaderException(e);
        } finally {
            if (reader !=null) {
                try {
                    reader.close();
                } catch (IOException e) {
                }
            }
        }
    }

    @Override
    public boolean hasNext() throws FileReaderException {
        try {
            return reader.ready();
        } catch (IOException e) {
            throw new FileReaderException(e);
        }
    }

    @Override
    public char read() throws IOException {
        return (char) reader.read();
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}
