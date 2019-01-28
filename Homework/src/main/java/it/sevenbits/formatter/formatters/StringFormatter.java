package it.sevenbits.formatter.formatters;

import it.sevenbits.formatter.io.readers.IReader;
import it.sevenbits.formatter.io.writers.IWriter;

import java.io.IOException;

/**
 * FileFormatter is a class that works with streams
 */
public class StringFormatter implements IFormatter {
    private final int spaceAmount = 4;
    private final char space = ' ';
    private final char newLine = '\n';
    private final char openingBracket = '{';
    private final char closingBracket = '}';
    private final char semicolon = ';';

    /**
     * FileFormatter function format that reads symbols step by step from a stream
     * and writes formatted symbols step by step to other stream.
     *
     * @param reader is a stream from where reads every symbol.
     * @param writer is a stream where symbols is written.
     * @throws IOException that is input and output streams exception.
     */

    public void format(final IReader reader, final IWriter writer) throws IOException {
        int countClBr = 0;
        int countOpBr = 0;
        char buffer = reader.read();
        boolean write;
        boolean spaceState;
        boolean count = true;
        char secondBuffer = buffer;
        while (reader.hasNext()) {
            spaceState = false;
            write = false;
            if (buffer == ' ' || buffer == '\n') {
                while (reader.hasNext() && (buffer == space || buffer == newLine)) {
                    buffer = reader.read();
                }
                if (secondBuffer != openingBracket && secondBuffer != semicolon && secondBuffer != closingBracket &&
                        buffer != openingBracket && buffer != closingBracket && buffer != semicolon) {
                    spaceState = true;
                }
            }
            if (buffer == openingBracket) {
                spaceState = true;
                write = true;
                countOpBr++;
            }
            if (buffer == semicolon) {
                write = true;
            }
            if (buffer == closingBracket) {
                write = true;
                if (count) {
                    countClBr++;
                }
                count = true;
            }
            if (spaceState) {
                writer.write(space);
            }
            writer.write(buffer);
            if (reader.hasNext()) {
                secondBuffer = buffer;
                buffer = reader.read();
            }
            if (write) {
                writer.write(newLine);
                if (buffer == space || buffer == newLine) {
                    while (reader.hasNext() && (buffer == space || buffer == newLine)) {
                        buffer = reader.read();
                    }
                }
                if (buffer == closingBracket) {
                    count = false;
                    countClBr++;
                }

                for (int j = 0; j < countOpBr - countClBr; j++) {
                    for (int i = 0; i < spaceAmount; i++) {
                        writer.write(space);
                    }
                }
                if (!reader.hasNext()) {
                    writer.write(buffer);
                }
            }
        }
    }
}
