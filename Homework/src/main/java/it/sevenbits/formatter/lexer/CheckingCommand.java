package it.sevenbits.formatter.lexer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckingCommand implements ICommand {
    private CommandContext commandContext;
    private Map<Boolean, List<String>> allowableTypes;

    public CheckingCommand(CommandContext commandContext) {
        this.commandContext = commandContext;
        this.allowableTypes = new HashMap<>();

        List<String> emptyBuffer = new ArrayList<>();
        emptyBuffer.add("OPENING_BRACKET");
        emptyBuffer.add("CLOSING_BRACKET");
        emptyBuffer.add("OPENING_PARENTHESIS");
        emptyBuffer.add("CLOSING_PARENTHESIS");
        emptyBuffer.add("SEMICOLON");
        emptyBuffer.add("ONE_LINE_COMMENT");
        emptyBuffer.add("MANY_LINES_COMMENT_OPENING");
        emptyBuffer.add("STRING");

        List<String> notEmptyBuffer = new ArrayList<>();
        notEmptyBuffer.add("STRING");
        notEmptyBuffer.add("MANY_LINES_COMMENT_CLOSING");

        allowableTypes.put(true, emptyBuffer);
        allowableTypes.put(false, notEmptyBuffer);
    }

    @Override
    public void execute() {
        String type = commandContext.getCurrentSymbol().getType();
        if(allowableTypes.get(commandContext.getLexemeBuffer().length() == 0).contains(type)) {
            //command manager here
        }
    }
}
