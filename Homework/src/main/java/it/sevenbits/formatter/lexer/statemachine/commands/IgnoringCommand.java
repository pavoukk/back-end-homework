package it.sevenbits.formatter.lexer.statemachine.commands;

/**
 * IgnoringCommand does nothing it's depressed =)
 */
public class IgnoringCommand implements ICommand {
    private CommandContext commandContext;
    private ICommand next;

    /**
     * A constructor with one parameter
     *
     * @param commandContext is a command context
     */
    public IgnoringCommand(final CommandContext commandContext) {
        this.commandContext = commandContext;
    }

    /**
     * A constructor with two parameters
     *
     * @param commandContext is a command context
     * @param next           is the next command to execute
     */
    public IgnoringCommand(final CommandContext commandContext, final ICommand next) {
        this.commandContext = commandContext;
        this.next = next;
    }

    @Override
    public void execute() {
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
