package it.sevenbits.formatter.lexer.statemachine.commands;

public class CommandContext {
    private StringBuilder lexemeBuffer;
    private char currentSymbol;
    private String lexemeType;
    private boolean poison;
    private char reservedSymbol;

    public CommandContext() {

    }
    public CommandContext(final StringBuilder lexemeBuffer) {
        this.lexemeBuffer = lexemeBuffer;
    }

    public StringBuilder getLexemeBuffer() {
        return lexemeBuffer;
    }

    public void setLexemeBuffer(final StringBuilder lexemeBuffer) {
        this.lexemeBuffer = lexemeBuffer;
    }

    public char getCurrentSymbol() {
        return currentSymbol;
    }

    public void setCurrentSymbol(final char currentSymbol) {
        this.currentSymbol = currentSymbol;
    }

    public String getLexemeType() {
        return lexemeType;
    }

    public void setLexemeType(final String lexemeType) {
        this.lexemeType = lexemeType;
    }

    public boolean isPoison() {
        return poison;
    }

    public void setPoison(final boolean poison) {
        this.poison = poison;
    }

    public char getReservedSymbol() {
        return reservedSymbol;
    }

    public void setReservedSymbol(final char reservedSymbol) {
        this.reservedSymbol = reservedSymbol;
    }
}
