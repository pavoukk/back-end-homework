package it.sevenbits.formatter.formatters.statemachine.commands;

import it.sevenbits.formatter.io.writers.IWriter;
import it.sevenbits.formatter.lexer.token.IToken;

/**
 * CommandContext stores constantly changing info that is needed for token building
 */
public class CommandContext {
    private IWriter writer;
    private IToken token;
    private int stepsCount;

    /**
     * A constructor with one parameter
     *
     * @param writer is a writer
     */
    public CommandContext(final IWriter writer) {
        this.writer = writer;
    }

    /**
     * @return writer
     */
    public IWriter getWriter() {
        return writer;
    }

    /**
     * @param writer is a writer
     */
    public void setWriter(final IWriter writer) {
        this.writer = writer;
    }

    /**
     * @return token
     */
    public IToken getToken() {
        return token;
    }

    /**
     * @param token is a new token
     */
    public void setToken(final IToken token) {
        this.token = token;
    }

    /**
     * @return amount of steps
     */
    public int getStepsCount() {
        return stepsCount;
    }

    /**
     * @param stepsCount an amount of steps
     */
    public void setStepsCount(final int stepsCount) {
        this.stepsCount = stepsCount;
    }
}
