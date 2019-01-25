package it.sevenbits.formatter.lexer;

public class ResultCommand implements ICommand {
    private CommandContext commandContext;

    public ResultCommand(CommandContext commandContext) {
        this.commandContext = commandContext;
    }

    @Override
    public void execute() {
        Symbol cur = commandContext.getCurrentSymbol();
        if (cur.getType().equals("MANY_LINES_COMMENT_CLOSING")) {
            commandContext.getLexemeBuffer().append(cur.getCharacter());
        }
    }
}
