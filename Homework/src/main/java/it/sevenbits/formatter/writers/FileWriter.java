package it.sevenbits.formatter.writers;

import it.sevenbits.formatter.exceptions.FileWriterException;

import java.io.*;

public class FileWriter implements IWriter, Closeable, AutoCloseable {
    private File file;
    private Writer writer;

    public FileWriter(final String path) throws IOException {
        try {
            file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            writer = new BufferedWriter(new java.io.FileWriter(path));
        } catch (IOException e) {
            throw new FileWriterException(e);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    public FileWriter(final File file) throws IOException {
        try {
            this.file = file;
            if (!file.exists()) {
                this.file.createNewFile();
            }
            writer = new BufferedWriter(new java.io.FileWriter(this.file));
        } catch (IOException e) {
            throw new FileWriterException(e);
        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }



    @Override
    public void close() throws IOException {
        writer.close();
    }

    @Override
    public void write(final char ch) throws IOException {
        writer.write(ch);
    }
}
