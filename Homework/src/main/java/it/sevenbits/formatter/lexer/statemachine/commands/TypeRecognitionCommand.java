package it.sevenbits.formatter.lexer.statemachine.commands;

import java.util.HashMap;
import java.util.Map;

/**
 * TypeRecognitionCommand defines the token's type
 */
public class TypeRecognitionCommand implements ICommand {
    private CommandContext commandContext;
    private ICommand next;
    private Map<String, String> tokenTypesMap;

    /**
     * Constructor with one parameth
     *
     * @param commandContext is a command context
     */
    public TypeRecognitionCommand(final CommandContext commandContext) {
        this.commandContext = commandContext;

        createTokenTypesMap();
    }

    /**
     * Constructor with two parameters
     *
     * @param commandContext is a command context
     * @param next           is the next command to execute
     */
    public TypeRecognitionCommand(final CommandContext commandContext, final ICommand next) {
        this.commandContext = commandContext;
        this.next = next;

        createTokenTypesMap();
    }

    @Override
    public void execute() {
        String inBuffer = commandContext.getLexemeBuffer().toString();
        String type = tokenTypesMap.getOrDefault(inBuffer, "ID");
        commandContext.setLexemeType(type);

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

    /**
     * Creates token types map
     */
    private void createTokenTypesMap() {
        tokenTypesMap = new HashMap<>();
        tokenTypesMap.put("{", "CURLY_BRACKET_OPEN");
        tokenTypesMap.put("}", "CURLY_BRACKET_CLOSED");
        tokenTypesMap.put(";", "SEMICOLON");
        tokenTypesMap.put("(", "ROUND_BRACKET_OPEN");
        tokenTypesMap.put(")", "ROUND_BRACKET_CLOSED");
        tokenTypesMap.put("\n", "NEW_LINE");
        tokenTypesMap.put(" ", "SPACE");
        tokenTypesMap.put("//", "ONE_LINE_COMMENT");
        tokenTypesMap.put("/*", "MANY_LINES_COMMENT");
        tokenTypesMap.put("\"", "STRING");
    }
}
