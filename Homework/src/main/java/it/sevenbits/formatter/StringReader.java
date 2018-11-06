package it.sevenbits.formatter;

import java.util.Objects;

/**
 * StringReader is a IReader implementation
 */
public class StringReader implements IReader {
    private String string;
    private int current;

    /**
     * Constructor
     * @param string incoming string
     */
    public StringReader(final String string) {
        current = -1;
        this.string = string;
    }

    /**
     * The implemented method.
     * @return boolean
     */
    public boolean hasNext() {
        return current + 1 < string.length();
    }

    /**
     * The implemented method.
     * @return char
     */
    public char read() {
        current++;
        return string.charAt(current);
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringReader that = (StringReader) o;
        return current == that.current &&
                Objects.equals(string, that.string);
    }

    @Override
    public int hashCode() {
        return Objects.hash(string, current);
    }

    @Override
    public String toString() {
        return "StringReader{" +
                "string='" + string + '\'' +
                ", current=" + current +
                '}';
    }
}
