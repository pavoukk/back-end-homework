package it.sevenbits.formatter.formatters;

import it.sevenbits.formatter.formatters.exceptions.FormatterException;
import it.sevenbits.formatter.io.readers.exceptions.ReaderException;
import it.sevenbits.formatter.io.writers.exceptions.WriterException;
import it.sevenbits.formatter.lexer.factories.LexerFactory;
import it.sevenbits.formatter.io.readers.FileReader;
import it.sevenbits.formatter.io.writers.FileWriter;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class FileFormatterTest {
    private File fileToRead;
    private File fileToWrite;
    private LexerFactory lexerFactory;
    private IFormatter fileFormatter;

    @Before
    public void initialize() {
        fileToRead = new File("../toread.txt");
        fileToWrite = new File("../towrite.txt");
        lexerFactory = new LexerFactory();
        fileFormatter = new FileFormatter(lexerFactory);
    }

    @Test
    public void formatSimpleStringTest() throws FormatterException {
        String testString = "aaa { bbbb; ccc;}";
        String testStringResult =
                "aaa {\n" +
                        "    bbbb;\n" +
                        "    ccc;\n" +
                        "}";
        try (BufferedWriter bw = new BufferedWriter(new java.io.FileWriter(fileToRead))) {
            bw.write(testString);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileReader fileReader = new FileReader(fileToRead);
             FileWriter fileWriter = new FileWriter(fileToWrite)) {

            fileFormatter.format(fileReader, fileWriter);
        } catch (ReaderException | WriterException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(fileToWrite))) {
            StringBuilder formattedFileContent = new StringBuilder();
            while (reader.ready()) {
                formattedFileContent.append((char) reader.read());
            }

            assertEquals(testStringResult, formattedFileContent.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void formatBracketsOddAmountTest() throws FormatterException {
        String testString = "{{{{{}}}}}";
        String testStringResult =
                " {\n" +
                        "     {\n" +
                        "         {\n" +
                        "             {\n" +
                        "                 {\n" +
                        "                }\n" +
                        "            }\n" +
                        "        }\n" +
                        "    }\n" +
                        "}";
        try (BufferedWriter bw = new BufferedWriter(new java.io.FileWriter(fileToRead))) {
            bw.write(testString);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileReader fileReader = new FileReader(fileToRead);
             FileWriter fileWriter = new FileWriter(fileToWrite)) {

            fileFormatter.format(fileReader, fileWriter);
        } catch (ReaderException | WriterException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(fileToWrite))) {
            StringBuilder formattedFileContent = new StringBuilder();
            while (reader.ready()) {
                formattedFileContent.append((char) reader.read());
            }

            assertEquals(testStringResult, formattedFileContent.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void formatBracketsEvenAmountTest() throws FormatterException {
        String testString = "{{{{}}}}";
        String testStringResult =
                " {\n" +
                        "     {\n" +
                        "         {\n" +
                        "             {\n" +
                        "            }\n" +
                        "        }\n" +
                        "    }\n" +
                        "}";

        try (BufferedWriter bw = new BufferedWriter(new java.io.FileWriter(fileToRead))) {
            bw.write(testString);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileReader fileReader = new FileReader(fileToRead);
             FileWriter fileWriter = new FileWriter(fileToWrite)) {

            fileFormatter.format(fileReader, fileWriter);
        } catch (ReaderException | WriterException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(fileToWrite))) {
            StringBuilder formattedFileContent = new StringBuilder();
            while (reader.ready()) {
                formattedFileContent.append((char) reader.read());
            }

            assertEquals(testStringResult, formattedFileContent.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void formatBracketsEvenAmountWithNewLinesTest() throws FormatterException {
        String testString =
                " {\n" +
                        "     {\n" +
                        "         {\n" +
                        "             {\n" +
                        "            }\n" +
                        "        }\n" +
                        "    }\n" +
                        "}";
        String testStringResult =
                " {\n" +
                        "     {\n" +
                        "         {\n" +
                        "             {\n" +
                        "            }\n" +
                        "        }\n" +
                        "    }\n" +
                        "}";

        try (BufferedWriter bw = new BufferedWriter(new java.io.FileWriter(fileToRead))) {
            bw.write(testString);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileReader fileReader = new FileReader(fileToRead);
             FileWriter fileWriter = new FileWriter(fileToWrite)) {

            fileFormatter.format(fileReader, fileWriter);
        } catch (ReaderException | WriterException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(fileToWrite))) {
            StringBuilder formattedFileContent = new StringBuilder();
            while (reader.ready()) {
                formattedFileContent.append((char) reader.read());
            }

            assertEquals(testStringResult, formattedFileContent.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void formatSimpleFunctionTest() throws FormatterException {
        String testString = "func(){ new int[12]; for(){  if(i % 2 == 0){ arr[i] = 0;}}";
        String testStringResult =
                "func() {\n" +
                        "    new int[12];\n" +
                        "    for() {\n" +
                        "        if(i % 2 == 0) {\n" +
                        "            arr[i] = 0;\n" +
                        "        }\n" +
                        "    }";
        try (BufferedWriter bw = new BufferedWriter(new java.io.FileWriter(fileToRead))) {
            bw.write(testString);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileReader fileReader = new FileReader(fileToRead);
             FileWriter fileWriter = new FileWriter(fileToWrite)) {

            fileFormatter.format(fileReader, fileWriter);
        } catch (ReaderException | WriterException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(fileToWrite))) {
            StringBuilder formattedFileContent = new StringBuilder();
            while (reader.ready()) {
                formattedFileContent.append((char) reader.read());
            }

            assertEquals(formattedFileContent.toString(), testStringResult);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void formatValideCodeTest() {
        String testString = "public void func(){int a = 0;int b = 5;if((a + b)>=4){int a = 4;int b = 6;return a + b;}return 0;}";
        String testStringResult =
                "public void func() {\n" +
                        "    int a = 0;\n" +
                        "    int b = 5;\n" +
                        "    if((a + b)>=4) {\n" +
                        "        int a = 4;\n" +
                        "        int b = 6;\n" +
                        "        return a + b;\n" +
                        "    }\n" +
                        "    return 0;\n" +
                        "}";

        try (BufferedWriter bw = new BufferedWriter(new java.io.FileWriter(fileToRead))) {
            bw.write(testString);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileReader fileReader = new FileReader(fileToRead);
             FileWriter fileWriter = new FileWriter(fileToWrite)) {

            fileFormatter.format(fileReader, fileWriter);
        } catch (ReaderException | WriterException | FormatterException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(new java.io.FileReader(fileToWrite))) {
            StringBuilder formattedFileContent = new StringBuilder();
            while (reader.ready()) {
                formattedFileContent.append((char) reader.read());
            }

            assertEquals(testStringResult, formattedFileContent.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
