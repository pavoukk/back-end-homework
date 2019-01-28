package it.sevenbits.formatter.formatters.statemachine.commands;

import it.sevenbits.formatter.io.writers.exceptions.WriterException;

/**
 * WriteSpaceCommand writes the space character to Formatter's Writer
 */
public class WriteSpaceCommand implements ICommand {
    private CommandContext commandContext;
    private ICommand next;

    /**
     * A constructor with one parameter
     *
     * @param commandContext is a command context
     */
    public WriteSpaceCommand(final CommandContext commandContext) {
        this.commandContext = commandContext;
    }

    /**
     * A constructor with two parameters
     *
     * @param commandContext is a commandContext
     * @param next           is the next command
     */
    public WriteSpaceCommand(final CommandContext commandContext, final ICommand next) {
        this(commandContext);
        this.next = next;
    }

    @Override
    public void execute() throws WriterException {
        commandContext.getWriter().write(' ');

        next();
    }

    private void next() throws WriterException {
        if (next != null) {
            next.execute();
        }
    }
}
