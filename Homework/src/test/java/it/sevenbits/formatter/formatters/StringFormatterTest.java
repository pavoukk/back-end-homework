package it.sevenbits.formatter.formatters;

import it.sevenbits.formatter.formatters.exceptions.FormatterException;
import it.sevenbits.formatter.io.readers.IReader;
import it.sevenbits.formatter.io.readers.StringReader;
import it.sevenbits.formatter.io.writers.StringWriter;
import it.sevenbits.formatter.lexer.factories.LexerFactory;
import org.junit.*;

import static org.junit.Assert.assertEquals;

public class StringFormatterTest {

    @Test
    public void formatTestSimple() {
        IReader reader1 = new StringReader("aaa { bbbb; ccc;}");
        IFormatter formatter = new StateMachineFormatter(new LexerFactory());
        StringWriter writer = new StringWriter();
        try {
            formatter.format(reader1, writer);
            assertEquals(writer.getString(), "aaa {\n" +
                    "    bbbb;\n" +
                    "    ccc;\n" +
                    "}");
        } catch (FormatterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void formatTestNotSimple() {
        IReader reader2 = new StringReader("public void func(){int a = 0;int b = (5+1);if((a + b)>=4){int a = 4;int b = 6;return a + b;}return 0;}");
        IFormatter formatter = new StateMachineFormatter(new LexerFactory());
        StringWriter writer = new StringWriter();
        try {
            formatter.format(reader2, writer);
            assertEquals(writer.getString(), "public void func() {\n" +
                    "    int a = 0;\n" +
                    "    int b =(5+1);\n" +
                    "    if((a + b) >=4) {\n" +
                    "        int a = 4;\n" +
                    "        int b = 6;\n" +
                    "        return a + b;\n" +
                    "    }\n" +
                    "    return 0;\n" +
                    "}");
        } catch (FormatterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void formatTestNotSimpleToo() {
        IReader reader3 = new StringReader("a  public  void func (){int a = 0; int b = 3; int c= 4; if(a== b){ b = a; return b;}");
        StringFormatter formatter = new StringFormatter();
        StringWriter writer = new StringWriter();
        try {
            formatter.format(reader3, writer);
            assertEquals(writer.getString(), "a public void func () {\n" +
                    "    int a = 0;\n" +
                    "    int b = 3;\n" +
                    "    int c= 4;\n" +
                    "    if(a== b) {\n" +
                    "        b = a;\n" +
                    "        return b;\n" +
                    "    }");
        } catch (FormatterException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void formatTestOtherTest() {
        IReader reader4 = new StringReader("func(){ new int[12]; for(){  if(i % 2 == 0){ arr[i] = 0;}}");
        StringFormatter formatter = new StringFormatter();
        StringWriter writer = new StringWriter();
        try {
            formatter.format(reader4, writer);
            assertEquals(writer.getString(), "func() {\n" +
                    "    new int[12];\n" +
                    "    for() {\n" +
                    "        if(i % 2 == 0) {\n" +
                    "            arr[i] = 0;\n" +
                    "        }\n" +
                    "    }");
        } catch (FormatterException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void formatTestBrackets() {
        IReader reader5 = new StringReader("{{{{{}}}}}");
        StringFormatter formatter = new StringFormatter();
        StringWriter writer = new StringWriter();
        try {
            formatter.format(reader5, writer);
            assertEquals(writer.getString(), " {\n" +
                    "     {\n" +
                    "         {\n" +
                    "             {\n" +
                    "                 {\n" +
                    "                }\n" +
                    "            }\n" +
                    "        }\n" +
                    "    }\n" +
                    "}");
        } catch (FormatterException e) {
            e.printStackTrace();
        }
    }
}