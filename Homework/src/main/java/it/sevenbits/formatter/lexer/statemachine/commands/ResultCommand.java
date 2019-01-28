package it.sevenbits.formatter.lexer.statemachine.commands;

/**
 * ResultCommand sends a signal saying token's collection completed
 */
public class ResultCommand implements ICommand {
    private final CommandContext commandContext;
    private ICommand next;

    /**
     * A constructor with one parameter
     *
     * @param commandContext is a command context
     */
    public ResultCommand(final CommandContext commandContext) {
        this.commandContext = commandContext;
    }

    /**
     * A constructor with two parameters
     *
     * @param commandContext is a command context
     * @param next           is the next command to execute
     */
    public ResultCommand(final CommandContext commandContext, final ICommand next) {
        this.commandContext = commandContext;

        this.next = next;
    }

    @Override
    public void execute() {
        commandContext.setPoison(true);

        next();
    }

    /**
     * Calls the next command if exists
     */
    private void next() {
        if (next != null) {
            next.execute();
        }
    }
}
