package it.sevenbits.formatter;

import java.io.*;

public class FileReader implements IReader, AutoCloseable {
    private Reader reader;
    private File file;
    private int count;
    public FileReader(final String path) throws IOException {
        this.file = new File(path);
        count = 0;
        if (!file.exists()) {
            file.createNewFile();
        }
        reader = new BufferedReader(new java.io.FileReader(path));
    }

    public FileReader(final File file) throws IOException {
        this.file = file;
        count = 0;
        if(!file.exists()) {
            this.file.createNewFile();
        }
        reader = new BufferedReader(new java.io.FileReader(file));
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
    public void close() throws Exception {
        reader.close();
    }
}
