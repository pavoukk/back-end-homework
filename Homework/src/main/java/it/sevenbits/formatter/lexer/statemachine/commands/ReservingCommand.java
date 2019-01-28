package it.sevenbits.formatter.lexer.statemachine.commands;

/**
 * ReservingCommand reserves current character to recognize it in the next iteration
 */
public class ReservingCommand implements ICommand {
    private CommandContext commandContext;
    private ICommand next;

    /**
     * A constructor with one parameter
     *
     * @param commandContext is a command context
     */
    public ReservingCommand(final CommandContext commandContext) {
        this.commandContext = commandContext;
    }

    /**
     * A constructor with two parameters
     *
     * @param commandContext is a command context
     * @param next           is the next command to execute
     */
    public ReservingCommand(final CommandContext commandContext, final ICommand next) {
        this.commandContext = commandContext;
        this.next = next;
    }

    @Override
    public void execute() {
        commandContext.setReservedSymbol(commandContext.getCurrentSymbol());

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
