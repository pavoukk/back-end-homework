package it.sevenbits.formatter.lexer;

import java.util.HashMap;
import java.util.Map;

public class LexerStateMap {
    private Map<Pair<State, String>, State> stateMap;
    private final State defaultState = new State("DEFAULT");
    public LexerStateMap() {
        State oneLineCommentCollectionState = new State("ONE_LINE_COMMENT_COLLECTION");
        State manyLinesCommentCollectionState = new State("MANY_LINES_COMMENT_COLLECTION");
        State wordCollectionState = new State("WORD_COLLECTION");
        State commentSuspicionState = new State("ONE_LINE_COMMENT_SUSPICION");
        State manyLinesCommentsEndSuspicionState = new State("MANY_LINES_COMMENT_SUSPICION");
        stateMap = new HashMap<>();
        stateMap.put(new Pair<>(defaultState, "OPENING_BRACKET"), defaultState);
        stateMap.put(new Pair<>(defaultState, "CLOSING_BRACKET"), defaultState); //TODO need other state for {}(); or maybe don't
        stateMap.put(new Pair<>(defaultState, "OPENING_PARENTHESIS"), defaultState);
        stateMap.put(new Pair<>(defaultState, "CLOSING_PARENTHESIS"), defaultState);
        stateMap.put(new Pair<>(defaultState, "SEMICOLON"), defaultState);
//        stateMap.put(new Pair<>(defaultState, "ONE_LINE_COMMENT"), oneLineCommentCollectionState);
        stateMap.put(new Pair<>(defaultState, "SLASH"), commentSuspicionState);
//        stateMap.put(new Pair<>(defaultState, "MANY_LINES_COMMENT_OPENING"), manyLinesCommentCollectionState);
        stateMap.put(new Pair<>(defaultState, "STRING"), wordCollectionState);
        stateMap.put(new Pair<>(defaultState, "SPACE"), defaultState);
        stateMap.put(new Pair<>(defaultState, "NEW_LINE"), defaultState);
        stateMap.put(new Pair<>(defaultState, "ASTERISK"), defaultState);
        stateMap.put(new Pair<>(defaultState, "BACKSLASH"), defaultState);

        stateMap.put(new Pair<>(wordCollectionState, "OPENING_BRACKET"), defaultState);
        stateMap.put(new Pair<>(wordCollectionState, "CLOSING_BRACKET"), defaultState);
        stateMap.put(new Pair<>(wordCollectionState, "OPENING_PARENTHESIS"), defaultState);
        stateMap.put(new Pair<>(wordCollectionState, "CLOSING_PARENTHESIS"), defaultState);
        stateMap.put(new Pair<>(wordCollectionState, "SEMICOLON"), defaultState);
        stateMap.put(new Pair<>(wordCollectionState, "STRING"), wordCollectionState);
        stateMap.put(new Pair<>(wordCollectionState, "SPACE"), defaultState);
        stateMap.put(new Pair<>(wordCollectionState, "NEW_LINE"), defaultState);
//        stateMap.put(new Pair<>(wordCollectionState, "ONE_LINE_COMMENT"), defaultState);
        stateMap.put(new Pair<>(wordCollectionState, "SLASH"), defaultState);
//        stateMap.put(new Pair<>(wordCollectionState, "MANY_LINES_COMMENT_OPENING"), defaultState);
//        stateMap.put(new Pair<>(wordCollectionState, "MANY_LINES_COMMENT_CLOSING"), defaultState);
        stateMap.put(new Pair<>(wordCollectionState, "ASTERISK"), defaultState);
        stateMap.put(new Pair<>(wordCollectionState, "BACKSLASH"), defaultState);
        // still i'm gonna use my teacher's advice

        stateMap.put(new Pair<>(commentSuspicionState, "SLASH"), oneLineCommentCollectionState);
        stateMap.put(new Pair<>(commentSuspicionState, "ASTERISK"), manyLinesCommentCollectionState); // else mb goto default

        stateMap.put(new Pair<>(oneLineCommentCollectionState, "OPENING_BRACKET"), oneLineCommentCollectionState);
        stateMap.put(new Pair<>(oneLineCommentCollectionState, "CLOSING_BRACKET"), oneLineCommentCollectionState);
        stateMap.put(new Pair<>(oneLineCommentCollectionState, "OPENING_PARENTHESIS"), oneLineCommentCollectionState);
        stateMap.put(new Pair<>(oneLineCommentCollectionState, "CLOSING_PARENTHESIS"), oneLineCommentCollectionState);
        stateMap.put(new Pair<>(oneLineCommentCollectionState, "SEMICOLON"), oneLineCommentCollectionState);
        stateMap.put(new Pair<>(oneLineCommentCollectionState, "STRING"), oneLineCommentCollectionState);
        stateMap.put(new Pair<>(oneLineCommentCollectionState, "SPACE"), oneLineCommentCollectionState);
        stateMap.put(new Pair<>(oneLineCommentCollectionState, "NEW_LINE"), defaultState);

        stateMap.put(new Pair<>(manyLinesCommentCollectionState, "OPENING_BRACKET"), manyLinesCommentCollectionState);
        stateMap.put(new Pair<>(manyLinesCommentCollectionState, "CLOSING_BRACKET"), manyLinesCommentCollectionState);
        stateMap.put(new Pair<>(manyLinesCommentCollectionState, "OPENING_PARENTHESIS"), manyLinesCommentCollectionState);
        stateMap.put(new Pair<>(manyLinesCommentCollectionState, "CLOSING_PARENTHESIS"), manyLinesCommentCollectionState);
        stateMap.put(new Pair<>(manyLinesCommentCollectionState, "SEMICOLON"), manyLinesCommentCollectionState);
        stateMap.put(new Pair<>(manyLinesCommentCollectionState, "STRING"), manyLinesCommentCollectionState);
        stateMap.put(new Pair<>(manyLinesCommentCollectionState, "SPACE"), manyLinesCommentCollectionState);
        stateMap.put(new Pair<>(manyLinesCommentCollectionState, "NEW_LINE"), manyLinesCommentCollectionState);
        stateMap.put(new Pair<>(manyLinesCommentCollectionState, "MANY_LINES_COMMENT_CLOSING"), defaultState);
    }

    public State getNextState(final State state, final String type) {
        return stateMap.getOrDefault(new Pair<>(state, type), defaultState);
    }

    public State getDefaultState() {
        return defaultState;
    }
}
