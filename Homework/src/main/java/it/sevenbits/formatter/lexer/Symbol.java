package it.sevenbits.formatter.lexer;

public class Symbol {
    private final char character;
    private final String type;

    public Symbol(char character, String type) {
        this.character = character;
        this.type = type;
    }

    public char getCharacter() {
        return character;
    }

    public String getType() {
        return type;
    }
}
