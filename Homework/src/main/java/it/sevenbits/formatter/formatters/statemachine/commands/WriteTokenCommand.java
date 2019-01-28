package it.sevenbits.formatter.formatters.statemachine.commands;

import it.sevenbits.formatter.io.writers.exceptions.WriterException;

/**
 * WriteTokenCommand writes a token to Formatter's Writer
 */
public class WriteTokenCommand implements ICommand {
    private CommandContext commandContext;
    private ICommand next;

    /**
     * A constructor with one parameter
     *
     * @param commandContext is a command context
     */
    public WriteTokenCommand(final CommandContext commandContext) {
        this.commandContext = commandContext;
    }

    /**
     * A constructor with two parameters
     *
     * @param commandContext is a commandContext
     * @param next           is the next command
     */
    public WriteTokenCommand(final CommandContext commandContext, final ICommand next) {
        this(commandContext);
        this.next = next;
    }

    @Override
    public void execute() throws WriterException {
        String lexeme = commandContext.getToken().getLexeme();
        for (char el : lexeme.toCharArray()) {
            commandContext.getWriter().write(el);
            next();
        }
    }

    private void next() throws WriterException {
        if (next != null) {
            next.execute();
        }
    }
}
