package it.sevenbits.formatter.lexer.statemachine.commands;

import it.sevenbits.formatter.lexer.Symbol;
import it.sevenbits.formatter.lexer.statemachine.commands.CommandContext;
import it.sevenbits.formatter.lexer.statemachine.commands.ICommand;

import java.util.HashSet;
import java.util.Set;

public class IgnoringCommand implements ICommand {
    private final CommandContext commandContext;
    private Set<String> ignoredTypes;

    public IgnoringCommand(final CommandContext commandContext) {
        this.commandContext = commandContext;
        ignoredTypes = new HashSet<>();
        ignoredTypes.add("NEW_LINE");
        ignoredTypes.add("SPACE");
    }

    @Override
    public void execute() {
        StringBuilder sb = commandContext.getLexemeBuffer();
        String symbolType = commandContext.getCurrentSymbol().getType();
        char symbolCharacter = commandContext.getCurrentSymbol().getCharacter();
        if (!ignoredTypes.contains(symbolType) && sb.length() == 0) {
            sb.append(symbolType);
            commandContext.setPoison(true);
        } else if (!ignoredTypes.contains(symbolType) && sb.length() > 0) {
            commandContext.setIgnoredSymbol(new Symbol(symbolCharacter, symbolType));
            commandContext.setPoison(true);
        } // else log
    }
}
