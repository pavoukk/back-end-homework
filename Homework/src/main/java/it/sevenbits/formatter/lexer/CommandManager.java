package it.sevenbits.formatter.lexer;

import java.util.HashMap;
import java.util.Map;

public class CommandManager {
    private Map<Pair<State, String>, ICommand> commandMap;
    private ICommand ignore = new IgnoringCommand();

    public CommandManager() {
        ICommand append = new AppendingCommand();
        State defaultState = new State("DEFAULT");
        State oneLineCommentCollectionState = new State("ONE_LINE_COMMENT_COLLECTION");
        State manyLinesCommentCollectionState = new State("MANY_LINES_COMMENT_COLLECTION");
        State wordCollectionState = new State("WORD_COLLECTION");
        commandMap = new HashMap<>();
        commandMap.put(new Pair<>(defaultState, "NEW_LINE"), ignore);
        commandMap.put(new Pair<>(defaultState, "SPACE"), ignore);
        commandMap.put(new Pair<>(defaultState, "OPENING_BRACKET"), append);
        commandMap.put(new Pair<>(defaultState, "CLOSING_BRACKET"), append); //TODO add command for {}();
        commandMap.put(new Pair<>(defaultState, "OPENING_PARENTHESIS"), append); //TODO append does not suit
        commandMap.put(new Pair<>(defaultState, "CLOSING_PARENTHESIS"), append);
        commandMap.put(new Pair<>(defaultState, "SEMICOLON"), append);
        commandMap.put(new Pair<>(defaultState, "STRING"), append);
        commandMap.put(new Pair<>(defaultState, "ONE_LINE_COMMENT"), append);
        commandMap.put(new Pair<>(defaultState, "MANY_LINES_COMMENT_OPENING"), append);

        commandMap.put(new Pair<>(wordCollectionState, "STRING"), append);
        commandMap.put(new Pair<>(wordCollectionState, "NEW_LINE"), ignore);
        commandMap.put(new Pair<>(wordCollectionState, "SPACE"), ignore);
        commandMap.put(new Pair<>(wordCollectionState, "OPENING_BRACKET"), ignore);
        commandMap.put(new Pair<>(wordCollectionState, "CLOSING_BRACKET"), ignore);
        commandMap.put(new Pair<>(wordCollectionState, "OPENING_PARENTHESIS"), ignore);
        commandMap.put(new Pair<>(wordCollectionState, "CLOSING_PARENTHESIS"), ignore);
        commandMap.put(new Pair<>(wordCollectionState, "SEMICOLON"), ignore);
        commandMap.put(new Pair<>(wordCollectionState, "ONE_LINE_COMMENT"), ignore);
        commandMap.put(new Pair<>(wordCollectionState, "MANY_LINES_COMMENT_OPENING"), ignore);

        commandMap.put(new Pair<>(oneLineCommentCollectionState, "STRING"), append);
        commandMap.put(new Pair<>(oneLineCommentCollectionState, "SPACE"), append);
        commandMap.put(new Pair<>(oneLineCommentCollectionState, "OPENING_BRACKET"), append);
        commandMap.put(new Pair<>(oneLineCommentCollectionState, "CLOSING_BRACKET"), append);
        commandMap.put(new Pair<>(oneLineCommentCollectionState, "OPENING_PARENTHESIS"), append);
        commandMap.put(new Pair<>(oneLineCommentCollectionState, "CLOSING_PARENTHESIS"), append);
        commandMap.put(new Pair<>(oneLineCommentCollectionState, "SEMICOLON"), append);
        commandMap.put(new Pair<>(oneLineCommentCollectionState, "NEW_LINE"), ignore);

        commandMap.put(new Pair<>(manyLinesCommentCollectionState, "STRING"), append);
        commandMap.put(new Pair<>(manyLinesCommentCollectionState, "NEW_LINE"), append);
        commandMap.put(new Pair<>(manyLinesCommentCollectionState, "SPACE"), append);
        commandMap.put(new Pair<>(manyLinesCommentCollectionState, "OPENING_BRACKET"), append);
        commandMap.put(new Pair<>(manyLinesCommentCollectionState, "CLOSING_BRACKET"), append);
        commandMap.put(new Pair<>(manyLinesCommentCollectionState, "OPENING_PARENTHESIS"), append);
        commandMap.put(new Pair<>(manyLinesCommentCollectionState, "CLOSING_PARENTHESIS"), append);
        commandMap.put(new Pair<>(manyLinesCommentCollectionState, "SEMICOLON"), append);
        commandMap.put(new Pair<>(manyLinesCommentCollectionState, "MANY_LINES_COMMENT_CLOSING"), ignore);
    }

    public ICommand getCommand(final State state, final String type) {
        commandMap.getOrDefault(new Pair<>(state, type), ignore);
    }
}
