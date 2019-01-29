package it.sevenbits.formatter.formatters;

import it.sevenbits.formatter.formatters.exceptions.FormatterException;
import it.sevenbits.formatter.io.readers.IReader;
import it.sevenbits.formatter.io.readers.exceptions.ReaderException;
import it.sevenbits.formatter.io.writers.exceptions.WriterException;
import it.sevenbits.formatter.lexer.ILexer;
import it.sevenbits.formatter.lexer.StateMachineLexer;
import it.sevenbits.formatter.lexer.exceptions.LexerException;
import it.sevenbits.formatter.lexer.factories.StateMachineLexerFactory;

import it.sevenbits.formatter.io.readers.FileReader;
import it.sevenbits.formatter.io.writers.FileWriter;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class StateMachineFormatterTest {
    private StateMachineLexerFactory lexerFactory;
    private IFormatter stateMachineFormatter;
    private File fileToRead;
    private File fileToWrite;

    @Before
    public void initialize() {
        fileToRead = new File("../toread.txt");
        fileToWrite = new File("../towrite.txt");
        lexerFactory = new StateMachineLexerFactory();
        stateMachineFormatter = new StateMachineFormatter(lexerFactory);
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

            stateMachineFormatter.format(fileReader, fileWriter);
        } catch (ReaderException | WriterException e) {
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

    @Test
    public void formatBracketsOddAmountTest() throws FormatterException {
        String testString = "{{{{{}}}}}";
        String testStringResult =
                "{\n" +
                        "    {\n" +
                        "        {\n" +
                        "            {\n" +
                        "                {\n" +
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

            stateMachineFormatter.format(fileReader, fileWriter);
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
                "{\n" +
                        "    {\n" +
                        "        {\n" +
                        "            {\n" +
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

            stateMachineFormatter.format(fileReader, fileWriter);
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
                "{\n" +
                        "    {\n" +
                        "        {\n" +
                        "            {\n" +
                        "            }\n" +
                        "        }\n" +
                        "    }\n" +
                        "}";
        String testStringResult =
                "{\n" +
                        "    {\n" +
                        "        {\n" +
                        "            {\n" +
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

            stateMachineFormatter.format(fileReader, fileWriter);
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

            stateMachineFormatter.format(fileReader, fileWriter);
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
    public void formatValidCodeTest() {
        String testString = "public void func(){int a = 0;int b = 5;if((a + b)>=4){int a = 4;int b = 6;return a + b;}return 0;}";
        String testStringResult =
                "public void func() {\n" +
                        "    int a = 0;\n" +
                        "    int b = 5;\n" +
                        "    if((a + b) >=4) {\n" +
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

            stateMachineFormatter.format(fileReader, fileWriter);
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

    @Test
    public void bigCodePartTest() {
        String testString = "/**\n" +
                "     * The Method formats reader's info and writes it to writer\n" +
                "     *\n" +
                "     * @param reader is input info\n" +
                "     * @param writer is output info\n" +
                "     * @throws FormatterException is thrown if something goes wrong\n" +
                "     */\n" +
                "    public void format(final IReader reader, final IWriter writer) throws FormatterException {\n" +
                "        ILexer lexer = factory.createLexer(reader);\n" +
                "        IToken token;\n" +
                "        IToken bufToken;\n" +
                "        int countClBr = 0;\n" +
                "        int countOpBr = 0;\n" +
                "        boolean write;\n" +
                "        boolean count = true;\n" +
                "\n" +
                "        try {\n" +
                "            token = lexer.readToken();\n" +
                "            bufToken = token;\n" +
                "            while (reader.hasNext()) {\n" +
                "                write = false;\n" +
                "                if (token.getName().equals(openingBracketName)) {\n" +
                "                    countOpBr++;\n" +
                "                    write = true;\n" +
                "                    writer.write(space);\n" +
                "                }\n" +
                "                if (token.getName().equals(semicolonName)) {\n" +
                "                    write = true;\n" +
                "                }\n" +
                "                if (token.getName().equals(closingBracketName)) {\n" +
                "                    if (count) {\n" +
                "                        countClBr++;\n" +
                "                    }\n" +
                "                    count = true;\n" +
                "                    write = true;\n" +
                "                }\n" +
                "                char[] arr = token.getLexeme().toCharArray();\n" +
                "                for (char ch : arr) {\n" +
                "                    writer.write(ch);\n" +
                "                }\n" +
                "                if (reader.hasNext()) {\n" +
                "                    bufToken = token;\n" +
                "                    token = lexer.readToken();\n" +
                "                }\n" +
                "                if (token.getName().equals(closingBracketName)) {\n" +
                "                    count = false;\n" +
                "                    countClBr++;\n" +
                "                }\n" +
                "                if (write) {\n" +
                "                    writer.write(newLine);\n" +
                "                    for (int j = 0; j < countOpBr - countClBr; j++) {\n" +
                "                        for (int i = 0; i < spaceAmount; i++) {\n" +
                "                            writer.write(space);\n" +
                "                        }\n" +
                "                    }\n" +
                "                } else {\n" +
                "                    if (!(noSpace.contains(token.getName()) || noSpace.contains(bufToken.getName()))) {\n" +
                "                        writer.write(space);\n" +
                "                    }\n" +
                "                }\n" +
                "\n" +
                "                if (!reader.hasNext()) {\n" +
                "                    arr = token.getLexeme().toCharArray();\n" +
                "                    for (char ch : arr) {\n" +
                "                        writer.write(ch);\n" +
                "                    }\n" +
                "                }\n" +
                "            }\n" +
                "        } catch (WriterException e) {\n" +
                "            throw new FormatterException(\"Cannot write any more characters\", e);\n" +
                "        } catch (ReaderException e) {\n" +
                "            throw new FormatterException(\"Cannot read any more characters\", e);\n" +
                "        } catch (LexerException e) {\n" +
                "            throw new FormatterException(\"Cannot read any more tokens\", e);\n" +
                "        }\n" +
                "    }";
        String testStringResult = "/**\n" +
                "     * The Method formats reader's info and writes it to writer\n" +
                "     *\n" +
                "     * @param reader is input info\n" +
                "     * @param writer is output info\n" +
                "     * @throws FormatterException is thrown if something goes wrong\n" +
                "     */\n" +
                "public void format(final IReader reader, final IWriter writer) throws FormatterException {\n" +
                "    ILexer lexer = factory.createLexer(reader);\n" +
                "    IToken token;\n" +
                "    IToken bufToken;\n" +
                "    int countClBr = 0;\n" +
                "    int countOpBr = 0;\n" +
                "    boolean write;\n" +
                "    boolean count = true;\n" +
                "    try {\n" +
                "        token = lexer.readToken();\n" +
                "        bufToken = token;\n" +
                "        while(reader.hasNext()) {\n" +
                "            write = false;\n" +
                "            if(token.getName() .equals(openingBracketName)) {\n" +
                "                countOpBr++;\n" +
                "                write = true;\n" +
                "                writer.write(space);\n" +
                "            }\n" +
                "            if(token.getName() .equals(semicolonName)) {\n" +
                "                write = true;\n" +
                "            }\n" +
                "            if(token.getName() .equals(closingBracketName)) {\n" +
                "                if(count) {\n" +
                "                    countClBr++;\n" +
                "                }\n" +
                "                count = true;\n" +
                "                write = true;\n" +
                "            }\n" +
                "            char[] arr = token.getLexeme() .toCharArray();\n" +
                "            for(char ch : arr) {\n" +
                "                writer.write(ch);\n" +
                "            }\n" +
                "            if(reader.hasNext()) {\n" +
                "                bufToken = token;\n" +
                "                token = lexer.readToken();\n" +
                "            }\n" +
                "            if(token.getName() .equals(closingBracketName)) {\n" +
                "                count = false;\n" +
                "                countClBr++;\n" +
                "            }\n" +
                "            if(write) {\n" +
                "                writer.write(newLine);\n" +
                "                for(int j = 0;\n" +
                "                j < countOpBr - countClBr;\n" +
                "                j++) {\n" +
                "                    for(int i = 0;\n" +
                "                    i < spaceAmount;\n" +
                "                    i++) {\n" +
                "                        writer.write(space);\n" +
                "                    }\n" +
                "                }\n" +
                "            }\n" +
                "            else {\n" +
                "                if(!(noSpace.contains(token.getName()) || noSpace.contains(bufToken.getName()))) {\n" +
                "                    writer.write(space);\n" +
                "                }\n" +
                "            }\n" +
                "            if(!reader.hasNext()) {\n" +
                "                arr = token.getLexeme() .toCharArray();\n" +
                "                for(char ch : arr) {\n" +
                "                    writer.write(ch);\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    }\n" +
                "    catch(WriterException e) {\n" +
                "        throw new FormatterException(\"Cannot write any more characters\" , e);\n" +
                "    }\n" +
                "    catch(ReaderException e) {\n" +
                "        throw new FormatterException(\"Cannot read any more characters\" , e);\n" +
                "    }\n" +
                "    catch(LexerException e) {\n" +
                "        throw new FormatterException(\"Cannot read any more tokens\" , e);\n" +
                "    }\n" +
                "}";

        try (BufferedWriter bw = new BufferedWriter(new java.io.FileWriter(fileToRead))) {
            bw.write(testString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileReader fileReader = new FileReader(fileToRead);
             FileWriter fileWriter = new FileWriter(fileToWrite)) {

            stateMachineFormatter.format(fileReader, fileWriter);
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

    @Test(expected = LexerException.class)
    public void hasMoreTokensTest() throws ReaderException, LexerException {
        IReader readerMocked = mock(IReader.class);
        when(readerMocked.hasNext()).thenThrow(ReaderException.class);
        ILexer smLexer = new StateMachineLexer(readerMocked);
        smLexer.hasMoreTokens();
    }

    @Test(expected = LexerException.class)
    public void readTokenTest() throws ReaderException, LexerException {
        IReader readerMocked = mock(IReader.class);
        when(readerMocked.hasNext()).thenThrow(ReaderException.class);
        when(readerMocked.read()).thenThrow(ReaderException.class);
        ILexer smLexer = new StateMachineLexer(readerMocked);
        smLexer.readToken();
    }
}