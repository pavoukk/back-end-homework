package it.sevenbits.formatter.io.writers;

import it.sevenbits.formatter.io.writers.exceptions.WriterException;
import org.junit.Test;

import static org.junit.Assert.*;

public class FileWriterTest {
    @Test
    public void fileWriterTest() {
        try(FileWriter writer = new FileWriter("../big")) {
            writer.write('p');
            writer.write('e');
            writer.write('r');
            writer.write('m');
            writer.write('a');
            writer.write('c');
            writer.write('h');
        } catch (WriterException e) {
            e.printStackTrace();
        }
    }
}