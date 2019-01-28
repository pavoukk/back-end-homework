package it.sevenbits.formatter.formatters.statemachine.commands;

import it.sevenbits.formatter.formatters.statemachine.Pair;
import it.sevenbits.formatter.formatters.statemachine.State;

import java.util.HashMap;
import java.util.Map;

/**
 * CommandManager with commands for state machine transitions
 */
public class CommandManager {
    private Map<Pair<State, String>, ICommand> commandMap;
    private CommandContext commandContext;

    private final String CURLY_BRACKET_OPEN = "CURLY_BRACKET_OPEN";
    private final String CURLY_BRACKET_CLOSED = "CURLY_BRACKET_CLOSED";
    private final String ROUND_BRACKET_OPEN = "ROUND_BRACKET_OPEN";
    private final String ROUND_BRACKET_CLOSED = "ROUND_BRACKET_CLOSED";
    private final String SEMICOLON = "SEMICOLON";
    private final String ID = "ID";
    private final String ONE_LINE_COMMENT = "ONE_LINE_COMMENT";
    private final String MANY_LINES_COMMENT = "MANY_LINES_COMMENT";
    private final String STRING_LITERAL = "STRING_LITERAL";

    private final ICommand writeToken;

    /**
     * A constructor with one parameter
     *
     * @param commandContext context
     */
    public CommandManager(final CommandContext commandContext) {
        this.commandContext = commandContext;
        State defaultState = new State("DEFAULT");
        State idOrLiteralState = new State("ID_OR_LITERAL");
        State curlyBracketOpenState = new State("CURLY_BRACKET_OPEN");
        State curlyBracketClosedState = new State("CURLY_BRACKET_CLOSED");
        State roundBracketOpenState = new State("ROUND_BRACKET_OPEN");
        State roundBracketClosedState = new State("ROUND_BRACKET_CLOSED");
        State semicolonState = new State("SEMICOLON");
        State oneLineCommentState = new State("ONE_LINE_COMMENT");
        State manyLinesCommentState = new State("MANY_LINES_COMMENT");

        writeToken = new WriteTokenCommand(commandContext);
        ICommand writeIndent = new WriteIndentCommand(commandContext);
        ICommand increaseStepsAmount = new IncreaseStepsAmountCommand(commandContext);
        ICommand decreaseStepsAmount = new DecreaseStepsAmountCommand(commandContext);

        ICommand writeSpaceAndToken = new WriteSpaceCommand(commandContext, writeToken);
        ICommand writeIndentAndToken = new WriteIndentCommand(commandContext, writeToken);
        ICommand writeTokenAndIncreaseStepsAmount = new WriteTokenCommand(commandContext, increaseStepsAmount);
        ICommand writeTokenAndDecreaseStepsAmount = new WriteTokenCommand(commandContext, decreaseStepsAmount);

        ICommand writeSpaceWriteTokenIncreaseStepsAmount = new WriteSpaceCommand(commandContext, writeTokenAndIncreaseStepsAmount);
        ICommand writeTokenDecreaseStepsAmountWriteIndent = new WriteTokenCommand(commandContext,
                new DecreaseStepsAmountCommand(commandContext, writeIndent));

        ICommand writeIndentAndWriteTokenAndIncreaseStepsAmount = new WriteIndentCommand(commandContext, writeTokenAndIncreaseStepsAmount);
        ICommand decreaseStepsAmountAndWriteIndentAndWriteToken = new DecreaseStepsAmountCommand(commandContext, writeIndentAndToken);
        ICommand writeSpaceAndTokenAndIncreaseStepsAmount = new WriteSpaceCommand(commandContext, writeTokenAndIncreaseStepsAmount);

        commandMap = new HashMap<>();
        commandMap.put(new Pair<>(defaultState, ID), writeToken);
        commandMap.put(new Pair<>(defaultState, STRING_LITERAL), writeToken);
        commandMap.put(new Pair<>(defaultState, CURLY_BRACKET_OPEN), writeTokenAndIncreaseStepsAmount);
        commandMap.put(new Pair<>(defaultState, CURLY_BRACKET_CLOSED), writeTokenAndDecreaseStepsAmount); // ?
        commandMap.put(new Pair<>(defaultState, ROUND_BRACKET_OPEN), writeToken);
        commandMap.put(new Pair<>(defaultState, ROUND_BRACKET_CLOSED), writeToken); // ?
        commandMap.put(new Pair<>(defaultState, SEMICOLON), writeToken); // ?
        commandMap.put(new Pair<>(defaultState, ONE_LINE_COMMENT), writeToken);
        commandMap.put(new Pair<>(defaultState, MANY_LINES_COMMENT), writeToken);

        commandMap.put(new Pair<>(idOrLiteralState, ID), writeSpaceAndToken);
        commandMap.put(new Pair<>(idOrLiteralState, STRING_LITERAL), writeSpaceAndToken);
        commandMap.put(new Pair<>(idOrLiteralState, CURLY_BRACKET_OPEN), writeSpaceWriteTokenIncreaseStepsAmount);
        commandMap.put(new Pair<>(idOrLiteralState, CURLY_BRACKET_CLOSED), writeTokenDecreaseStepsAmountWriteIndent); // ?
        commandMap.put(new Pair<>(idOrLiteralState, ROUND_BRACKET_OPEN), writeToken);
        commandMap.put(new Pair<>(idOrLiteralState, ROUND_BRACKET_CLOSED), writeToken);
        commandMap.put(new Pair<>(idOrLiteralState, SEMICOLON), writeToken);
        commandMap.put(new Pair<>(idOrLiteralState, ONE_LINE_COMMENT), writeIndentAndToken); //?
        commandMap.put(new Pair<>(idOrLiteralState, MANY_LINES_COMMENT), writeIndentAndToken); //?

        commandMap.put(new Pair<>(curlyBracketOpenState, ID), writeIndentAndToken);
        commandMap.put(new Pair<>(curlyBracketOpenState, STRING_LITERAL), writeIndentAndToken);
        commandMap.put(new Pair<>(curlyBracketOpenState, CURLY_BRACKET_OPEN), writeIndentAndWriteTokenAndIncreaseStepsAmount);
        commandMap.put(new Pair<>(curlyBracketOpenState, CURLY_BRACKET_CLOSED), decreaseStepsAmountAndWriteIndentAndWriteToken);
        commandMap.put(new Pair<>(curlyBracketOpenState, ROUND_BRACKET_OPEN), writeIndentAndToken);
        commandMap.put(new Pair<>(curlyBracketOpenState, ROUND_BRACKET_CLOSED), writeIndentAndToken);
        commandMap.put(new Pair<>(curlyBracketOpenState, SEMICOLON), writeIndentAndToken); // ?
        commandMap.put(new Pair<>(curlyBracketOpenState, ONE_LINE_COMMENT), writeIndentAndToken);
        commandMap.put(new Pair<>(curlyBracketOpenState, MANY_LINES_COMMENT), writeIndentAndToken);

        commandMap.put(new Pair<>(curlyBracketClosedState, ID), writeIndentAndToken);
        commandMap.put(new Pair<>(curlyBracketClosedState, STRING_LITERAL), writeIndentAndToken);
        commandMap.put(new Pair<>(curlyBracketClosedState, CURLY_BRACKET_OPEN), writeIndentAndWriteTokenAndIncreaseStepsAmount); // ??
        commandMap.put(new Pair<>(curlyBracketClosedState, CURLY_BRACKET_CLOSED), decreaseStepsAmountAndWriteIndentAndWriteToken);
        commandMap.put(new Pair<>(curlyBracketClosedState, ROUND_BRACKET_OPEN), writeIndentAndToken);
        commandMap.put(new Pair<>(curlyBracketClosedState, ROUND_BRACKET_CLOSED), writeIndentAndToken); //?
        commandMap.put(new Pair<>(curlyBracketClosedState, SEMICOLON), writeToken);
        commandMap.put(new Pair<>(curlyBracketClosedState, ONE_LINE_COMMENT), writeIndentAndToken);
        commandMap.put(new Pair<>(curlyBracketClosedState, MANY_LINES_COMMENT), writeIndentAndToken);

        commandMap.put(new Pair<>(roundBracketOpenState, ID), writeToken);
        commandMap.put(new Pair<>(roundBracketOpenState, STRING_LITERAL), writeToken);
        commandMap.put(new Pair<>(roundBracketOpenState, CURLY_BRACKET_OPEN), writeToken); //?
        commandMap.put(new Pair<>(roundBracketOpenState, CURLY_BRACKET_CLOSED), writeToken); //?
        commandMap.put(new Pair<>(roundBracketOpenState, ROUND_BRACKET_OPEN), writeToken);
        commandMap.put(new Pair<>(roundBracketOpenState, ROUND_BRACKET_CLOSED), writeToken);
        commandMap.put(new Pair<>(roundBracketOpenState, SEMICOLON), writeToken);
        commandMap.put(new Pair<>(roundBracketOpenState, ONE_LINE_COMMENT), writeIndentAndToken); //?
        commandMap.put(new Pair<>(roundBracketOpenState, MANY_LINES_COMMENT), writeIndentAndToken); //?

        commandMap.put(new Pair<>(roundBracketClosedState, ID), writeSpaceAndToken);
        commandMap.put(new Pair<>(roundBracketClosedState, STRING_LITERAL), writeSpaceAndToken);
        commandMap.put(new Pair<>(roundBracketClosedState, CURLY_BRACKET_OPEN), writeSpaceAndTokenAndIncreaseStepsAmount);
        commandMap.put(new Pair<>(roundBracketClosedState, CURLY_BRACKET_CLOSED), decreaseStepsAmountAndWriteIndentAndWriteToken); // ?
        commandMap.put(new Pair<>(roundBracketClosedState, ROUND_BRACKET_OPEN), writeSpaceAndToken); // ?
        commandMap.put(new Pair<>(roundBracketClosedState, ROUND_BRACKET_CLOSED), writeToken);
        commandMap.put(new Pair<>(roundBracketClosedState, SEMICOLON), writeToken);
        commandMap.put(new Pair<>(roundBracketClosedState, ONE_LINE_COMMENT), writeIndentAndToken);
        commandMap.put(new Pair<>(roundBracketClosedState, MANY_LINES_COMMENT), writeIndentAndToken);

        commandMap.put(new Pair<>(semicolonState, ID), writeIndentAndToken);
        commandMap.put(new Pair<>(semicolonState, STRING_LITERAL), writeIndentAndToken);
        commandMap.put(new Pair<>(semicolonState, CURLY_BRACKET_OPEN), writeIndentAndWriteTokenAndIncreaseStepsAmount); // ?
        commandMap.put(new Pair<>(semicolonState, CURLY_BRACKET_CLOSED), decreaseStepsAmountAndWriteIndentAndWriteToken);
        commandMap.put(new Pair<>(semicolonState, ROUND_BRACKET_OPEN), writeIndentAndToken);
        commandMap.put(new Pair<>(semicolonState, ROUND_BRACKET_CLOSED), writeToken); // ?
        commandMap.put(new Pair<>(semicolonState, SEMICOLON), writeToken); // ?
        commandMap.put(new Pair<>(semicolonState, ONE_LINE_COMMENT), writeIndentAndToken);
        commandMap.put(new Pair<>(semicolonState, MANY_LINES_COMMENT), writeIndentAndToken);

        commandMap.put(new Pair<>(oneLineCommentState, ID), writeIndentAndToken);
        commandMap.put(new Pair<>(oneLineCommentState, STRING_LITERAL), writeIndentAndToken);
        commandMap.put(new Pair<>(oneLineCommentState, CURLY_BRACKET_OPEN), writeIndentAndWriteTokenAndIncreaseStepsAmount);
        commandMap.put(new Pair<>(oneLineCommentState, CURLY_BRACKET_CLOSED), decreaseStepsAmountAndWriteIndentAndWriteToken);
        commandMap.put(new Pair<>(oneLineCommentState, ROUND_BRACKET_OPEN), writeIndentAndToken);
        commandMap.put(new Pair<>(oneLineCommentState, ROUND_BRACKET_CLOSED), writeIndentAndToken); // ?
        commandMap.put(new Pair<>(oneLineCommentState, SEMICOLON), writeIndentAndToken); // ?
        commandMap.put(new Pair<>(oneLineCommentState, ONE_LINE_COMMENT), writeIndentAndToken);
        commandMap.put(new Pair<>(oneLineCommentState, MANY_LINES_COMMENT), writeIndentAndToken);

        commandMap.put(new Pair<>(manyLinesCommentState, ID), writeIndentAndToken);
        commandMap.put(new Pair<>(manyLinesCommentState, STRING_LITERAL), writeIndentAndToken);
        commandMap.put(new Pair<>(manyLinesCommentState, CURLY_BRACKET_OPEN), writeIndentAndWriteTokenAndIncreaseStepsAmount);
        commandMap.put(new Pair<>(manyLinesCommentState, CURLY_BRACKET_CLOSED), decreaseStepsAmountAndWriteIndentAndWriteToken);
        commandMap.put(new Pair<>(manyLinesCommentState, ROUND_BRACKET_OPEN), writeIndentAndToken);
        commandMap.put(new Pair<>(manyLinesCommentState, ROUND_BRACKET_CLOSED), writeIndentAndToken);
        commandMap.put(new Pair<>(manyLinesCommentState, SEMICOLON), writeIndentAndToken);
        commandMap.put(new Pair<>(manyLinesCommentState, ONE_LINE_COMMENT), writeIndentAndToken);
        commandMap.put(new Pair<>(manyLinesCommentState, MANY_LINES_COMMENT), writeIndentAndToken);
    }

    /**
     * Gets the next command for transition
     *
     * @param state current state
     * @param type  type of current character
     * @return next command
     */
    public ICommand getCommand(final State state, final String type) {
        return commandMap.getOrDefault(new Pair<>(state, type), writeToken);
    }
}
