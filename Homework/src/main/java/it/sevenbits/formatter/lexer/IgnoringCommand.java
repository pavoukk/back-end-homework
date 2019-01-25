package it.sevenbits.formatter.lexer;

public class IgnoringCommand implements ICommand {
    private final CommandContext commandContext;

    public IgnoringCommand(final CommandContext commandContext) {
        this.commandContext = commandContext;
    }

    @Override
    public void execute() {
        StringBuilder sb = commandContext.getLexemeBuffer();
        if(sb.length() == 0) {
            String symbolType = commandContext.getCurrentSymbol().getType();
            if(symbolType.equals("NEW_LINE"))
            //log do nothing
            return; // remove later maybe??
        }
        commandContext.setPoison(true);
    }
}
