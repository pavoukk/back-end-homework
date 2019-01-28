package it.sevenbits.formatter.io.readers;

import it.sevenbits.formatter.io.readers.exceptions.ReaderException;
import org.junit.Test;

import static org.junit.Assert.*;

public class FileReaderTest {
    @Test
    public void testHasNext() throws ReaderException {
        try (FileReader fileReader = new FileReader("./src/test/resources/small")) {
            assertEquals('b', fileReader.read());
            assertEquals('a', fileReader.read());
            assertEquals('n', fileReader.read());
            assertFalse(fileReader.hasNext());
        }
    }

}