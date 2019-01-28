package it.sevenbits.formatter.formatters.statemachine.commands;

import it.sevenbits.formatter.io.writers.exceptions.WriterException;

/**
 * WriteIndentCommand writes an indent to Formatter's Writer
 */
public class WriteIndentCommand implements ICommand {
    private CommandContext commandContext;
    private ICommand next;
    private final int INDENT = 4;

    /**
     * A constructor with one parameter
     *
     * @param commandContext is a command context
     */
    public WriteIndentCommand(final CommandContext commandContext) {
        this.commandContext = commandContext;
    }

    /**
     * A constructor with two parameters
     *
     * @param commandContext is a commandContext
     * @param next           is the next command
     */
    public WriteIndentCommand(final CommandContext commandContext, final ICommand next) {
        this(commandContext);
        this.next = next;
    }

    @Override
    public void execute() throws WriterException {
        commandContext.getWriter().write('\n');
        for (int i = 0; i < commandContext.getStepsCount(); i++) {
            for (int j = 0; j < INDENT; j++) {
                commandContext.getWriter().write(' ');
            }
        }
        next();
    }

    private void next() throws WriterException {
        if (next != null) {
            next.execute();
        }
    }
}
