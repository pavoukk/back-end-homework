package it.sevenbits.formatter.writers;

import it.sevenbits.formatter.exceptions.WriterException;

import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.io.BufferedWriter;

/**
 * File writer gets file path or a file while creating and then writes input info into it
 */
public class FileWriter implements IWriter, Closeable, AutoCloseable {
    private File file;
    private Writer writer;

    /**
     *
     * @param path is a file path or a file name
     * @throws WriterException is thrown if something goes wrong
     */
    public FileWriter(final String path) throws WriterException {
        try {
            file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            writer = new BufferedWriter(new java.io.FileWriter(path));
        } catch (IOException e) {
            throw new WriterException(e);
        }
    }

    /**
     *
     * @param file is a file
     * @throws WriterException is thrown if something goes wrong
     */
    public FileWriter(final File file) throws WriterException {
        try {
            this.file = file;
            if (!file.exists()) {
                this.file.createNewFile();
            }
            writer = new BufferedWriter(new java.io.FileWriter(this.file));
        } catch (IOException e) {
            throw new WriterException(e);
        }
    }


    @Override
    public void close() throws WriterException {
        try {
            writer.close();
        } catch (IOException e) {
            throw new WriterException(e);
        }
    }

    @Override
    public void write(final char ch) throws WriterException {
        try {
            writer.write(ch);
        } catch (IOException e) {
            throw new WriterException(e);
        }
    }

}
