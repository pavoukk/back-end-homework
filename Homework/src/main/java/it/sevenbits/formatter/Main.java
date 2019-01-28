package it.sevenbits.formatter;

import it.sevenbits.formatter.formatters.FormatterException;
import it.sevenbits.formatter.io.readers.exceptions.ReaderException;
import it.sevenbits.formatter.io.writers.exceptions.WriterException;
import it.sevenbits.formatter.lexer.factories.ILexerFactory;
import it.sevenbits.formatter.lexer.factories.LexerFactory;
import it.sevenbits.formatter.formatters.FileFormatter;
import it.sevenbits.formatter.lexer.Lexer;
import it.sevenbits.formatter.io.readers.FileReader;
import it.sevenbits.formatter.io.writers.FileWriter;

public final class Main {
    private Main() {
    }

    public static void main(final String[] args) {
        try (FileReader reader = new FileReader(args[0]); FileWriter writer = new FileWriter(args[1])) {
            Lexer lexer = new Lexer(reader);
            ILexerFactory factory = new LexerFactory();
            FileFormatter formatter = new FileFormatter(factory);
            formatter.format(reader, writer);
        } catch (ReaderException | WriterException | FormatterException e) {
            e.printStackTrace();
        }
    }
}
