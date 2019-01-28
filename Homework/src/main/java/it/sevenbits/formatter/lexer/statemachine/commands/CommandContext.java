package it.sevenbits.formatter.lexer.statemachine.commands;

/**
 * CommandContext stores constantly changing info that is needed for token building
 */
public class CommandContext {
    private StringBuilder lexemeBuffer;
    private char currentSymbol;
    private String lexemeType;
    private boolean poison;
    private char reservedSymbol;

    /**
     * A constructor with no parameters
     */
    public CommandContext() {

    }

    /**
     * A constructor with one parameter
     *
     * @param lexemeBuffer is a lexeme buffer
     */
    public CommandContext(final StringBuilder lexemeBuffer) {
        this.lexemeBuffer = lexemeBuffer;
    }

    /**
     * Lexeme buffer getter
     *
     * @return lexeme buffer
     */
    public StringBuilder getLexemeBuffer() {
        return lexemeBuffer;
    }

    /**
     * Lexeme buffer setter
     *
     * @param lexemeBuffer set lexeme buffer
     */
    public void setLexemeBuffer(final StringBuilder lexemeBuffer) {
        this.lexemeBuffer = lexemeBuffer;
    }

    /**
     * Current symbol getter
     *
     * @return current symbol
     */
    public char getCurrentSymbol() {
        return currentSymbol;
    }

    /**
     * Current symbol setter
     *
     * @param currentSymbol set current symbol
     */
    public void setCurrentSymbol(final char currentSymbol) {
        this.currentSymbol = currentSymbol;
    }

    /**
     * Lexeme type getter
     *
     * @return lexeme type
     */
    public String getLexemeType() {
        return lexemeType;
    }

    /**
     * Lexeme type setter
     *
     * @param lexemeType set lexeme type
     */
    public void setLexemeType(final String lexemeType) {
        this.lexemeType = lexemeType;
    }

    /**
     * Poison's presence checker
     *
     * @return poison's presence status
     */
    public boolean isPoison() {
        return poison;
    }

    /**
     * Poison setter
     *
     * @param poison set poison
     */
    public void setPoison(final boolean poison) {
        this.poison = poison;
    }

    /**
     * Reserved symbol getter
     *
     * @return reserved symbol
     */
    public char getReservedSymbol() {
        return reservedSymbol;
    }

    /**
     * Reserved symbol setter
     *
     * @param reservedSymbol set reserved symbol
     */
    public void setReservedSymbol(final char reservedSymbol) {
        this.reservedSymbol = reservedSymbol;
    }
}
