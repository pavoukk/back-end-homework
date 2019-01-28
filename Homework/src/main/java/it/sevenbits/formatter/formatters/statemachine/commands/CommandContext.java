package it.sevenbits.formatter.formatters.statemachine.commands;

import it.sevenbits.formatter.io.writers.IWriter;
import it.sevenbits.formatter.lexer.token.IToken;

public class CommandContext {
    private IWriter writer;
    private IToken token;
    private int stepsCount;

    public CommandContext(final IWriter writer) {
        this.writer = writer;
    }

    public IWriter getWriter() {
        return writer;
    }

    public void setWriter(final IWriter writer) {
        this.writer = writer;
    }

    public IToken getToken() {
        return token;
    }

    public void setToken(final IToken token) {
        this.token = token;
    }

    public int getStepsCount() {
        return stepsCount;
    }

    public void setStepsCount(final int stepsCount) {
        this.stepsCount = stepsCount;
    }
}
