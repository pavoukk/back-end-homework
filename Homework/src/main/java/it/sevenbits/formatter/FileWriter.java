package it.sevenbits.formatter;

import java.io.*;

public class FileWriter implements IWriter, Closeable {
    private File file;
    private Writer writer;
    public FileWriter(String path) throws IOException {
        file = new File(path);
        if(!file.exists()) {
            file.createNewFile();
        }
        writer = new BufferedWriter(new java.io.FileWriter(path));
    }
    public FileWriter(File file) throws IOException {
        this.file = file;
        if(!file.exists()) {
            this.file.createNewFile();
        }
        writer = new BufferedWriter(new java.io.FileWriter(this.file));
    }
    @Override
    public void write(char ch) throws IOException {
        writer.write(ch);
    }

    @Override
    public void close() throws IOException {
        writer.close();
    }
}
