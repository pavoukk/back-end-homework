package it.sevenbits.formatter.lexer.statemachine.commands;

/**
 * AppendingCommand appends the current character to lexeme buffer
 */
public class AppendingCommand implements ICommand {
    private CommandContext commandContext;
    private ICommand next;

    /**
     * A constructor with one parameter
     *
     * @param commandContext is a command context
     */
    public AppendingCommand(final CommandContext commandContext) {
        this.commandContext = commandContext;
    }

    /**
     * A constructor with two parameters
     *
     * @param commandContext is a command context
     * @param next           is the next command to execute
     */
    public AppendingCommand(final CommandContext commandContext, final ICommand next) {
        this.commandContext = commandContext;
        this.next = next;
    }

    @Override
    public void execute() {
        commandContext.getLexemeBuffer().append(commandContext.getCurrentSymbol());
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
