package it.sevenbits.formatter;

import it.sevenbits.formatter.formatters.exceptions.FormatterException;
import it.sevenbits.formatter.io.readers.exceptions.ReaderException;
import it.sevenbits.formatter.io.writers.exceptions.WriterException;
import it.sevenbits.formatter.lexer.StateMachineLexer;
import it.sevenbits.formatter.lexer.factories.ILexerFactory;
import it.sevenbits.formatter.lexer.factories.StateMachineLexerFactory;
import it.sevenbits.formatter.formatters.FileFormatter;
import it.sevenbits.formatter.io.readers.FileReader;
import it.sevenbits.formatter.io.writers.FileWriter;

/**
 * Main class of the project
 */
public final class Main {
    private Main() {
    }

    /**
     * Main method that runs the project
     * @param args are arguments
     */
    public static void main(final String[] args) {
        try (FileReader reader = new FileReader(args[0]); FileWriter writer = new FileWriter(args[1])) {
            StateMachineLexer lexer = new StateMachineLexer(reader);
            ILexerFactory factory = new StateMachineLexerFactory();
            FileFormatter formatter = new FileFormatter(factory);
            formatter.format(reader, writer);
        } catch (ReaderException | WriterException | FormatterException e) {
            e.printStackTrace();
        }
    }
}
