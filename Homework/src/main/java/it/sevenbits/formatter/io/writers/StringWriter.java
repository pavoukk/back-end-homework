package it.sevenbits.formatter.io.writers;

import java.util.Objects;

/**
 * StringWriter is an IWriter implementation.
 */
public class StringWriter implements IWriter {
    private StringBuilder string;

    /**
     * Constructor
     */
    public StringWriter() {
        string = new StringBuilder();
    }

    /**
     * The implemented method.
     * @param ch is the writing symbol
     */
    public void write(final char ch) {
        string.append(ch);
    }

    public String getString() {
        return string.toString();
    }
    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringWriter that = (StringWriter) o;
        return Objects.equals(string, that.string);
    }

    @Override
    public int hashCode() {
        return Objects.hash(string);
    }

    @Override
    public String toString() {
        return string.toString();
    }
}
