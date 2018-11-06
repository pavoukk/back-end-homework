package it.sevenbits.formatter;

import org.junit.*;

import java.io.IOException;

import static org.junit.Assert.*;

public class FormatterTest {
    private IReader reader1;
    private IReader reader2;
    private IReader reader3;
    private IReader reader4;
    private IReader reader5;

    @Before
    public void setUp() {
        reader1 = new StringReader("aaa { bbbb; ccc;}");
        reader2 = new StringReader("public void func(){int a = 0;int b = 5;if((a + b)>=4){int a = 4;int b = 6;return a + b;}return 0;}");
        reader3 = new StringReader("a  public  void func (){int a = 0; int b = 3; int c= 4; if(a== b){ b = a; return b;}");
        reader4 = new StringReader("func(){ new int[12]; for(){  if(i % 2 == 0){ arr[i] = 0;}}");
        reader5 = new StringReader("{{{{{}}}}}");
    }

    @Test
    public void formatTestSimple() {
        Formatter formatter = new Formatter();
        IWriter writer = new StringWriter();
        try {
            assertEquals(formatter.format(reader1, writer).toString(), "aaa {\n" +
                    "    bbbb;\n" +
                    "    ccc;\n" +
                    "}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void formatTestNotSimple() {
        Formatter formatter = new Formatter();
        IWriter writer = new StringWriter();
        try {
            assertEquals(formatter.format(reader2, writer).toString(), "public void func() {\n" +
                    "    int a = 0;\n" +
                    "    int b = 5;\n" +
                    "    if((a + b)>=4) {\n" +
                    "        int a = 4;\n" +
                    "        int b = 6;\n" +
                    "        return a + b;\n" +
                    "    }\n" +
                    "    return 0;\n" +
                    "}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void formatTestNotSimpleToo() {
        Formatter formatter = new Formatter();
        IWriter writer = new StringWriter();
        try {
            assertEquals(formatter.format(reader3, writer).toString(), "a public void func () {\n" +
                    "    int a = 0;\n" +
                    "    int b = 3;\n" +
                    "    int c= 4;\n" +
                    "    if(a== b) {\n" +
                    "        b = a;\n" +
                    "        return b;\n" +
                    "    }");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void formatTestOtherTest() {
        Formatter formatter = new Formatter();
        IWriter writer = new StringWriter();
        try {
            assertEquals(formatter.format(reader4, writer).toString(), "func() {\n" +
                    "    new int[12];\n" +
                    "    for() {\n" +
                    "        if(i % 2 == 0) {\n" +
                    "            arr[i] = 0;\n" +
                    "        }\n" +
                    "    }");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Test
    public void formatTestBrackets() {
        Formatter formatter = new Formatter();
        IWriter writer = new StringWriter();
        try {
            assertEquals(formatter.format(reader5, writer).toString(), " {\n" +
                    "     {\n" +
                    "         {\n" +
                    "             {\n" +
                    "                 {\n" +
                    "                }\n" +
                    "            }\n" +
                    "        }\n" +
                    "    }\n" +
                    "}");
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }
}