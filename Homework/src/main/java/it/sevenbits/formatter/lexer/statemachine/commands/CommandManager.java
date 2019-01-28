package it.sevenbits.formatter.lexer.statemachine.commands;

import it.sevenbits.formatter.lexer.statemachine.Pair;
import it.sevenbits.formatter.lexer.statemachine.State;

import java.util.HashMap;
import java.util.Map;

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
    private final String BACKSLASH = "BACKSLASH";
    private final String STRING = "STRING";

    public CommandManager(final CommandContext commandContext) {
        this.commandContext = commandContext;
        result = new ResultCommand(commandContext, null);
        ICommand append = new AppendingCommand(commandContext, null);
        ICommand ignore = new IgnoringCommand(commandContext, null);
        ICommand appendAndReturn = new AppendingCommand(commandContext, result);
        ICommand reserveAndReturn = new ReservingCommand(commandContext, result);
        ICommand ignoreAndReturn = new IgnoringCommand(commandContext, result);
        State defaultState = new State("DEFAULT");
        State oneLineCommentCollectionState = new State("ONE_LINE_COMMENT_COLLECTION");
        State manyLinesCommentCollectionState = new State("MANY_LINES_COMMENT_COLLECTION");
        State wordCollectionState = new State("WORD_COLLECTION");
        commandMap = new HashMap<>();
        commandMap.put(new Pair<>(defaultState, NEW_LINE), ignore);
        commandMap.put(new Pair<>(defaultState, SPACE), ignore);
        commandMap.put(new Pair<>(defaultState, CURLY_BRACKET_OPEN), appendAndReturn); //here was append
        commandMap.put(new Pair<>(defaultState, CURLY_BRACKET_CLOSED), appendAndReturn); //TODO add command for {}();
        commandMap.put(new Pair<>(defaultState, ROUND_BRACKET_OPEN), appendAndReturn); //TODO append does not suit
        commandMap.put(new Pair<>(defaultState, ROUND_BRACKET_CLOSED), appendAndReturn); // here was append
        commandMap.put(new Pair<>(defaultState, SEMICOLON), appendAndReturn); // here was append
        commandMap.put(new Pair<>(defaultState, ID), append); //here was append // no
        commandMap.put(new Pair<>(defaultState, SLASH), append); //here was append // no
        commandMap.put(new Pair<>(defaultState, ASTERISK), appendAndReturn); // here was append // no
        commandMap.put(new Pair<>(defaultState, BACKSLASH), appendAndReturn); // here was append // no
        commandMap.put(new Pair<>(defaultState, STRING), append);

        commandMap.put(new Pair<>(wordCollectionState, "STRING"), append);
        commandMap.put(new Pair<>(wordCollectionState, "NEW_LINE"), result);
        commandMap.put(new Pair<>(wordCollectionState, "SPACE"), result);
        commandMap.put(new Pair<>(wordCollectionState, "OPENING_BRACKET"), result);
        commandMap.put(new Pair<>(wordCollectionState, "CLOSING_BRACKET"), result);
        commandMap.put(new Pair<>(wordCollectionState, "OPENING_PARENTHESIS"), result);
        commandMap.put(new Pair<>(wordCollectionState, "CLOSING_PARENTHESIS"), result);
        commandMap.put(new Pair<>(wordCollectionState, "SEMICOLON"), result);
        commandMap.put(new Pair<>(wordCollectionState, "ONE_LINE_COMMENT"), result);
        commandMap.put(new Pair<>(wordCollectionState, "MANY_LINES_COMMENT_OPENING"), result);

        commandMap.put(new Pair<>(oneLineCommentCollectionState, "STRING"), append);
        commandMap.put(new Pair<>(oneLineCommentCollectionState, "SPACE"), append);
        commandMap.put(new Pair<>(oneLineCommentCollectionState, "OPENING_BRACKET"), append);
        commandMap.put(new Pair<>(oneLineCommentCollectionState, "CLOSING_BRACKET"), append);
        commandMap.put(new Pair<>(oneLineCommentCollectionState, "OPENING_PARENTHESIS"), append);
        commandMap.put(new Pair<>(oneLineCommentCollectionState, "CLOSING_PARENTHESIS"), append);
        commandMap.put(new Pair<>(oneLineCommentCollectionState, "SEMICOLON"), append);
        commandMap.put(new Pair<>(oneLineCommentCollectionState, "NEW_LINE"), result);

        commandMap.put(new Pair<>(manyLinesCommentCollectionState, "STRING"), append);
        commandMap.put(new Pair<>(manyLinesCommentCollectionState, "NEW_LINE"), append);
        commandMap.put(new Pair<>(manyLinesCommentCollectionState, "SPACE"), append);
        commandMap.put(new Pair<>(manyLinesCommentCollectionState, "OPENING_BRACKET"), append);
        commandMap.put(new Pair<>(manyLinesCommentCollectionState, "CLOSING_BRACKET"), append);
        commandMap.put(new Pair<>(manyLinesCommentCollectionState, "OPENING_PARENTHESIS"), append);
        commandMap.put(new Pair<>(manyLinesCommentCollectionState, "CLOSING_PARENTHESIS"), append);
        commandMap.put(new Pair<>(manyLinesCommentCollectionState, "SEMICOLON"), append);
        commandMap.put(new Pair<>(manyLinesCommentCollectionState, "MANY_LINES_COMMENT_CLOSING"), result);
    }

    public ICommand getCommand(final State state, final String type) {
        return commandMap.getOrDefault(new Pair<>(state, type), result);
    }
}
