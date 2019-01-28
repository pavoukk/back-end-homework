package it.sevenbits.formatter.lexer.statemachine.commands;

import it.sevenbits.formatter.lexer.statemachine.Pair;
import it.sevenbits.formatter.lexer.statemachine.State;

import java.util.HashMap;
import java.util.Map;

/**
 * CommandManager with commands for state machine transitions
 */
public class CommandManager {
    private Map<Pair<State, String>, ICommand> commandMap;
    private CommandContext commandContext;
    private ICommand result;

    private final String CURLY_BRACKET_OPEN = "CURLY_BRACKET_OPEN";
    private final String CURLY_BRACKET_CLOSED = "CURLY_BRACKET_CLOSED";
    private final String ROUND_BRACKET_OPEN = "ROUND_BRACKET_OPEN";
    private final String ROUND_BRACKET_CLOSED = "ROUND_BRACKET_CLOSED";
    private final String SEMICOLON = "SEMICOLON";
    private final String SPACE = "SPACE";
    private final String NEW_LINE = "NEW_LINE";
    private final String ID = "ID";
    private final String SLASH = "SLASH";
    private final String ASTERISK = "ASTERISK";
    private final String STRING_LITERAL = "STRING_LITERAL";

    /**
     * A constructor with one parameter
     *
     * @param commandContext context
     */
    public CommandManager(final CommandContext commandContext) {
        this.commandContext = commandContext;

        result = new ResultCommand(commandContext);
        ICommand append = new AppendingCommand(commandContext);
        ICommand ignore = new IgnoringCommand(commandContext);

        ICommand appendAndReturn = new AppendingCommand(commandContext, result);
        ICommand reserveAndReturn = new ReservingCommand(commandContext, result);
        ICommand ignoreAndReturn = new IgnoringCommand(commandContext, result);

        ICommand appendAndRecognizeAndReturn = new AppendingCommand(commandContext,
                new TypeRecognitionCommand(commandContext, result));
        ICommand appendAndRecognize = new AppendingCommand(commandContext, new TypeRecognitionCommand(commandContext));

        State defaultState = new State("DEFAULT");
        State idCollectionState = new State("ID_COLLECTION");
        State stringLiteralCollectionState = new State("STRING_LITERAL_COLLECTION_STATE");
        State oneLineCommentCollectionState = new State("ONE_LINE_COMMENT_COLLECTION");
        State commentSuspicionState = new State("ONE_LINE_COMMENT_SUSPICION");
        State manyLinesCommentCollectionState = new State("MANY_LINES_COMMENT_COLLECTION");
        State manyLinesCommentsEndSuspicionState = new State("MANY_LINES_COMMENT_SUSPICION");

        commandMap = new HashMap<>();
        commandMap.put(new Pair<>(defaultState, CURLY_BRACKET_OPEN), appendAndRecognizeAndReturn);
        commandMap.put(new Pair<>(defaultState, CURLY_BRACKET_CLOSED), appendAndRecognizeAndReturn);
        commandMap.put(new Pair<>(defaultState, ROUND_BRACKET_OPEN), appendAndRecognizeAndReturn);
        commandMap.put(new Pair<>(defaultState, ROUND_BRACKET_CLOSED), appendAndRecognizeAndReturn);
        commandMap.put(new Pair<>(defaultState, SEMICOLON), appendAndRecognizeAndReturn);
        commandMap.put(new Pair<>(defaultState, SPACE), ignore);
        commandMap.put(new Pair<>(defaultState, NEW_LINE), ignore);
        commandMap.put(new Pair<>(defaultState, SLASH), appendAndRecognize);
        commandMap.put(new Pair<>(defaultState, ASTERISK), appendAndRecognizeAndReturn);
        commandMap.put(new Pair<>(defaultState, STRING_LITERAL), appendAndRecognize);
        commandMap.put(new Pair<>(defaultState, ID), appendAndRecognize);

        commandMap.put(new Pair<>(idCollectionState, CURLY_BRACKET_OPEN), reserveAndReturn);
        commandMap.put(new Pair<>(idCollectionState, CURLY_BRACKET_CLOSED), reserveAndReturn);
        commandMap.put(new Pair<>(idCollectionState, ROUND_BRACKET_OPEN), reserveAndReturn);
        commandMap.put(new Pair<>(idCollectionState, ROUND_BRACKET_CLOSED), reserveAndReturn);
        commandMap.put(new Pair<>(idCollectionState, SEMICOLON), reserveAndReturn);
        commandMap.put(new Pair<>(idCollectionState, SPACE), result);
        commandMap.put(new Pair<>(idCollectionState, NEW_LINE), result);
        commandMap.put(new Pair<>(idCollectionState, SLASH), reserveAndReturn);
        commandMap.put(new Pair<>(idCollectionState, ASTERISK), reserveAndReturn);
        commandMap.put(new Pair<>(idCollectionState, STRING_LITERAL), reserveAndReturn);
        commandMap.put(new Pair<>(idCollectionState, ID), append);

        commandMap.put(new Pair<>(stringLiteralCollectionState, STRING_LITERAL), appendAndReturn);
        commandMap.put(new Pair<>(stringLiteralCollectionState, CURLY_BRACKET_OPEN), append);
        commandMap.put(new Pair<>(stringLiteralCollectionState, CURLY_BRACKET_CLOSED), append);
        commandMap.put(new Pair<>(stringLiteralCollectionState, ROUND_BRACKET_OPEN), append);
        commandMap.put(new Pair<>(stringLiteralCollectionState, ROUND_BRACKET_CLOSED), append);
        commandMap.put(new Pair<>(stringLiteralCollectionState, ID), append);
        commandMap.put(new Pair<>(stringLiteralCollectionState, SEMICOLON), append);
        commandMap.put(new Pair<>(stringLiteralCollectionState, SPACE), append);
        commandMap.put(new Pair<>(stringLiteralCollectionState, NEW_LINE), append);
        commandMap.put(new Pair<>(stringLiteralCollectionState, SLASH), append);
        commandMap.put(new Pair<>(stringLiteralCollectionState, ASTERISK), append);

        commandMap.put(new Pair<>(commentSuspicionState, CURLY_BRACKET_OPEN), reserveAndReturn);
        commandMap.put(new Pair<>(commentSuspicionState, CURLY_BRACKET_CLOSED), reserveAndReturn);
        commandMap.put(new Pair<>(commentSuspicionState, ROUND_BRACKET_OPEN), reserveAndReturn);
        commandMap.put(new Pair<>(commentSuspicionState, ROUND_BRACKET_CLOSED), reserveAndReturn);
        commandMap.put(new Pair<>(commentSuspicionState, SEMICOLON), reserveAndReturn);
        commandMap.put(new Pair<>(commentSuspicionState, ID), reserveAndReturn);
        commandMap.put(new Pair<>(commentSuspicionState, SPACE), reserveAndReturn);
        commandMap.put(new Pair<>(commentSuspicionState, NEW_LINE), reserveAndReturn);
        commandMap.put(new Pair<>(commentSuspicionState, STRING_LITERAL), reserveAndReturn);
        commandMap.put(new Pair<>(commentSuspicionState, SLASH), appendAndRecognize);
        commandMap.put(new Pair<>(commentSuspicionState, ASTERISK), appendAndRecognize);

        commandMap.put(new Pair<>(oneLineCommentCollectionState, NEW_LINE), result);
        commandMap.put(new Pair<>(oneLineCommentCollectionState, CURLY_BRACKET_OPEN), append);
        commandMap.put(new Pair<>(oneLineCommentCollectionState, CURLY_BRACKET_CLOSED), append);
        commandMap.put(new Pair<>(oneLineCommentCollectionState, ROUND_BRACKET_OPEN), append);
        commandMap.put(new Pair<>(oneLineCommentCollectionState, ROUND_BRACKET_CLOSED), append);
        commandMap.put(new Pair<>(oneLineCommentCollectionState, SEMICOLON), append);
        commandMap.put(new Pair<>(oneLineCommentCollectionState, ID), append);
        commandMap.put(new Pair<>(oneLineCommentCollectionState, SPACE), append);
        commandMap.put(new Pair<>(oneLineCommentCollectionState, SLASH), append);
        commandMap.put(new Pair<>(oneLineCommentCollectionState, ASTERISK), append);
        commandMap.put(new Pair<>(oneLineCommentCollectionState, STRING_LITERAL), append);

        commandMap.put(new Pair<>(manyLinesCommentCollectionState, ASTERISK), append); // maybe end of the comment
        commandMap.put(new Pair<>(manyLinesCommentCollectionState, CURLY_BRACKET_OPEN), append);
        commandMap.put(new Pair<>(manyLinesCommentCollectionState, CURLY_BRACKET_CLOSED), append);
        commandMap.put(new Pair<>(manyLinesCommentCollectionState, ROUND_BRACKET_OPEN), append);
        commandMap.put(new Pair<>(manyLinesCommentCollectionState, ROUND_BRACKET_CLOSED), append);
        commandMap.put(new Pair<>(manyLinesCommentCollectionState, SEMICOLON), append);
        commandMap.put(new Pair<>(manyLinesCommentCollectionState, ID), append);
        commandMap.put(new Pair<>(manyLinesCommentCollectionState, SPACE), append);
        commandMap.put(new Pair<>(manyLinesCommentCollectionState, NEW_LINE), append);
        commandMap.put(new Pair<>(manyLinesCommentCollectionState, SLASH), append);
        commandMap.put(new Pair<>(manyLinesCommentCollectionState, STRING_LITERAL), append);

        commandMap.put(new Pair<>(manyLinesCommentsEndSuspicionState, SLASH), appendAndReturn); // end of the comment
        commandMap.put(new Pair<>(manyLinesCommentsEndSuspicionState, CURLY_BRACKET_OPEN), append);
        commandMap.put(new Pair<>(manyLinesCommentsEndSuspicionState, CURLY_BRACKET_CLOSED), append);
        commandMap.put(new Pair<>(manyLinesCommentsEndSuspicionState, ROUND_BRACKET_OPEN), append);
        commandMap.put(new Pair<>(manyLinesCommentsEndSuspicionState, ROUND_BRACKET_CLOSED), append);
        commandMap.put(new Pair<>(manyLinesCommentsEndSuspicionState, SEMICOLON), append);
        commandMap.put(new Pair<>(manyLinesCommentsEndSuspicionState, ID), append);
        commandMap.put(new Pair<>(manyLinesCommentsEndSuspicionState, SPACE), append);
        commandMap.put(new Pair<>(manyLinesCommentsEndSuspicionState, NEW_LINE), append);
        commandMap.put(new Pair<>(manyLinesCommentsEndSuspicionState, ASTERISK), append);
        commandMap.put(new Pair<>(manyLinesCommentsEndSuspicionState, STRING_LITERAL), append);
    }

    /**
     * Gets the next command for transition
     *
     * @param state current state
     * @param type  type of current character
     * @return next command
     */
    public ICommand getCommand(final State state, final String type) {
        return commandMap.getOrDefault(new Pair<>(state, type), result);
    }
}
