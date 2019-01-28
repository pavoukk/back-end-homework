package it.sevenbits.formatter.io.writers;

import it.sevenbits.formatter.io.writers.exceptions.WriterException;

import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.io.Closeable;
import java.io.IOException;

/**
 * File writer gets file path or a file while creating and then writes input info into it
 */
public class FileWriter implements IWriter, Closeable, AutoCloseable {
    private File file;
    private Writer writer;

    /**
     * A constructor with one parameter
     *
     * @param path is a file path or a file name
     * @throws WriterException is thrown if something goes wrong
     */
    public FileWriter(final String path) throws WriterException {
        this(new File(path));
    }

    /**
     * A constructor with one parameter
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
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
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
