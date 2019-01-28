package it.sevenbits.formatter.io.formatters;

import it.sevenbits.formatter.IFormatter;
import it.sevenbits.formatter.io.exceptions.ReaderException;
import it.sevenbits.formatter.io.exceptions.WriterException;
import it.sevenbits.formatter.token.IToken;
import it.sevenbits.formatter.factories.ILexerFactory;
import it.sevenbits.formatter.lexer.ILexer;
import it.sevenbits.formatter.IReader;
import it.sevenbits.formatter.IWriter;

import java.util.ArrayList;
import java.util.List;

/**
 * FileFormatter is an IFormatter's implementation. It works with file IO.
 */
public class FileFormatter implements IFormatter {
    private final int spaceAmount = 4;
    private final char space = ' ';
    private final char newLine = '\n';
    private final String openingBracketName = "opening bracket";
    private final String closingBracketName = "closing bracket";
    private final String semicolonName = "semicolon";

    private ILexerFactory factory;
    private List<String> noSpace;

    /**
     * The constructor takes ILexerFactory object.
     * @param factory is used to create Lexer object
     */
    public FileFormatter(final ILexerFactory factory) {
        this.factory = factory;
        noSpace = new ArrayList<>();
        noSpace.add("opening parenthesis");
        noSpace.add("closing parenthesis");
        noSpace.add("opening bracket");
        noSpace.add("closing bracket");
        noSpace.add("semicolon");
    }

    /**
     * The Method formats reader's info and writes it to writer
     * @param reader is input info
     * @param writer is output info
     * @throws ReaderException is thrown if something goes wrong
     * @throws WriterException is thrown if something goes wrong
     */
    public void format(final IReader reader, final IWriter writer) throws ReaderException, WriterException {
        ILexer lexer = factory.createLexer(reader);
        IToken token;
        IToken bufToken;
        int countClBr = 0;
        int countOpBr = 0;
        boolean write;
        boolean count = true;
        token = lexer.readToken();
        bufToken = token;
        while (reader.hasNext()) {
            write = false;
            if (token.getName().equals(openingBracketName)) {
                countOpBr++;
                write = true;
                writer.write(space);
            }
            if (token.getName().equals(semicolonName)) {
                write = true;
            }
            if (token.getName().equals(closingBracketName)) {
                if (count) {
                    countClBr++;
                }
                count = true;
                write = true;
            }
            char[] arr = token.getLexeme().toCharArray();
            for (char ch : arr) {
                writer.write(ch);
            }
            if (reader.hasNext()) {
                bufToken = token;
                token = lexer.readToken();
            }
            if (token.getName().equals(closingBracketName)) {
                count = false;
                countClBr++;
            }
            if (write) {
                writer.write(newLine);
                for (int j = 0; j < countOpBr - countClBr; j++) {
                    for (int i = 0; i < spaceAmount; i++) {
                        writer.write(space);
                    }
                }
            } else {
                if (!(noSpace.contains(token.getName()) || noSpace.contains(bufToken.getName()))) {
                    writer.write(space);
                }
            }

            if (!reader.hasNext()) {
                arr = token.getLexeme().toCharArray();
                for (char ch : arr) {
                    writer.write(ch);
                }
            }
        }
    }
}