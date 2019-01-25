package it.sevenbits.formatter.lexer;

public class CommandContext {
    private Symbol currentSymbol;
    private StringBuilder lexemeBuffer;
    private boolean poison;
    private Symbol ignoredSymbol;

    public CommandContext() {

    }
    public CommandContext(final Symbol currentSymbol, final StringBuilder lexemeBuffer) {
        this.currentSymbol = currentSymbol;
        this.lexemeBuffer = lexemeBuffer;
    }

    public Symbol getCurrentSymbol() {
        return currentSymbol;
    }

    public void setCurrentSymbol(Symbol currentSymbol) {
        this.currentSymbol = currentSymbol;
    }

    public StringBuilder getLexemeBuffer() {
        return lexemeBuffer;
    }

    public void setLexemeBuffer(StringBuilder lexemeBuffer) {
        this.lexemeBuffer = lexemeBuffer;
    }

    public boolean isPoison() {
        return poison;
    }

    public void setPoison(boolean poison) {
        this.poison = poison;
    }

    public Symbol getIgnoredSymbol() {
        return ignoredSymbol;
    }

    public void setIgnoredSymbol(Symbol ignoredSymbol) {
        this.ignoredSymbol = ignoredSymbol;
    }
}
