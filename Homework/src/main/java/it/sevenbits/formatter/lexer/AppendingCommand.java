package it.sevenbits.formatter.lexer;

public class AppendingCommand implements ICommand {
    private CommandContext commandContext;

    public AppendingCommand(final CommandContext commandContext) {
        this.commandContext = commandContext;
    }

    @Override
    public void execute() {
        commandContext.getLexemeBuffer().append(commandContext.getCurrentSymbol().getCharacter());
    }
}
