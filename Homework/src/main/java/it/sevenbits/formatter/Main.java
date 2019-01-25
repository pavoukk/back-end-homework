package it.sevenbits.formatter;

import it.sevenbits.formatter.exceptions.ReaderException;
import it.sevenbits.formatter.exceptions.WriterException;
import it.sevenbits.formatter.factories.ILexerFactory;
import it.sevenbits.formatter.factories.LexerFactory;
import it.sevenbits.formatter.formatters.FileFormatter;
import it.sevenbits.formatter.lexer.Lexer;
import it.sevenbits.formatter.readers.FileReader;
import it.sevenbits.formatter.writers.FileWriter;

public final class Main {
    private Main(){
    }

    public static void main(final String[] args) {
            try (FileReader reader = new FileReader(args[0]); FileWriter writer = new FileWriter(args[1])) {
                Lexer lexer = new Lexer(reader);
                ILexerFactory factory = new LexerFactory();
                FileFormatter formatter = new FileFormatter(factory);
                formatter.format(reader, writer);
            } catch (ReaderException | WriterException e) {
                e.printStackTrace();
            }
    }
}
