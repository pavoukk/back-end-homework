package it.sevenbits.formatter.lexer.statemachine;

import java.util.HashMap;
import java.util.Map;

/**
 * LexerStateMap transits one Lexer state to other
 */
public class LexerStateMap {
    private Map<Pair<State, String>, State> stateMap;
    private final State defaultState = new State("DEFAULT");

    private final String ID = "ID";
    private final String SPACE = "SPACE";
    private final String NEW_LINE = "NEW_LINE";
    private final String SEMICOLON = "SEMICOLON";
    private final String CURLY_BRACKET_OPEN = "CURLY_BRACKET_OPEN";
    private final String CURLY_BRACKET_CLOSED = "CURLY_BRACKET_CLOSED";
    private final String ROUND_BRACKET_OPEN = "ROUND_BRACKET_OPEN";
    private final String ROUND_BRACKET_CLOSED = "ROUND_BRACKET_CLOSED";
    private final String SLASH = "SLASH";
    private final String ASTERISK = "ASTERISK";
    private final String STRING_LITERAL = "STRING_LITERAL";

    /**
     * Creates Lexer state map
     */
    public LexerStateMap() {
        State idCollectionState = new State("ID_COLLECTION");
        State stringLiteralCollectionState = new State("STRING_LITERAL_COLLECTION_STATE");
        State oneLineCommentCollectionState = new State("ONE_LINE_COMMENT_COLLECTION");
        State commentSuspicionState = new State("ONE_LINE_COMMENT_SUSPICION");
        State manyLinesCommentCollectionState = new State("MANY_LINES_COMMENT_COLLECTION");
        State manyLinesCommentsEndSuspicionState = new State("MANY_LINES_COMMENT_SUSPICION");

        stateMap = new HashMap<>();
        stateMap.put(new Pair<>(defaultState, CURLY_BRACKET_OPEN), defaultState);
        stateMap.put(new Pair<>(defaultState, CURLY_BRACKET_CLOSED), defaultState);
        stateMap.put(new Pair<>(defaultState, ROUND_BRACKET_OPEN), defaultState);
        stateMap.put(new Pair<>(defaultState, ROUND_BRACKET_CLOSED), defaultState);
        stateMap.put(new Pair<>(defaultState, SEMICOLON), defaultState);
        stateMap.put(new Pair<>(defaultState, SPACE), defaultState);
        stateMap.put(new Pair<>(defaultState, NEW_LINE), defaultState);
        stateMap.put(new Pair<>(defaultState, ASTERISK), defaultState);
        stateMap.put(new Pair<>(defaultState, ID), idCollectionState);
        stateMap.put(new Pair<>(defaultState, STRING_LITERAL), stringLiteralCollectionState);
        stateMap.put(new Pair<>(defaultState, SLASH), commentSuspicionState);

        stateMap.put(new Pair<>(idCollectionState, CURLY_BRACKET_OPEN), defaultState);
        stateMap.put(new Pair<>(idCollectionState, CURLY_BRACKET_CLOSED), defaultState);
        stateMap.put(new Pair<>(idCollectionState, ROUND_BRACKET_OPEN), defaultState);
        stateMap.put(new Pair<>(idCollectionState, ROUND_BRACKET_CLOSED), defaultState);
        stateMap.put(new Pair<>(idCollectionState, SEMICOLON), defaultState);
        stateMap.put(new Pair<>(idCollectionState, SPACE), defaultState);
        stateMap.put(new Pair<>(idCollectionState, NEW_LINE), defaultState);
        stateMap.put(new Pair<>(idCollectionState, SLASH), defaultState);
        stateMap.put(new Pair<>(idCollectionState, ASTERISK), defaultState);
        stateMap.put(new Pair<>(idCollectionState, STRING_LITERAL), defaultState);
        stateMap.put(new Pair<>(idCollectionState, ID), idCollectionState);

        stateMap.put(new Pair<>(stringLiteralCollectionState, STRING_LITERAL), defaultState);
        stateMap.put(new Pair<>(stringLiteralCollectionState, CURLY_BRACKET_OPEN), stringLiteralCollectionState);
        stateMap.put(new Pair<>(stringLiteralCollectionState, CURLY_BRACKET_CLOSED), stringLiteralCollectionState);
        stateMap.put(new Pair<>(stringLiteralCollectionState, ROUND_BRACKET_OPEN), stringLiteralCollectionState);
        stateMap.put(new Pair<>(stringLiteralCollectionState, ROUND_BRACKET_CLOSED), stringLiteralCollectionState);
        stateMap.put(new Pair<>(stringLiteralCollectionState, ID), stringLiteralCollectionState);
        stateMap.put(new Pair<>(stringLiteralCollectionState, SEMICOLON), stringLiteralCollectionState);
        stateMap.put(new Pair<>(stringLiteralCollectionState, SPACE), stringLiteralCollectionState);
        stateMap.put(new Pair<>(stringLiteralCollectionState, NEW_LINE), stringLiteralCollectionState);
        stateMap.put(new Pair<>(stringLiteralCollectionState, SLASH), stringLiteralCollectionState);
        stateMap.put(new Pair<>(stringLiteralCollectionState, ASTERISK), stringLiteralCollectionState);

        stateMap.put(new Pair<>(commentSuspicionState, CURLY_BRACKET_OPEN), defaultState);
        stateMap.put(new Pair<>(commentSuspicionState, CURLY_BRACKET_CLOSED), defaultState);
        stateMap.put(new Pair<>(commentSuspicionState, ROUND_BRACKET_OPEN), defaultState);
        stateMap.put(new Pair<>(commentSuspicionState, ROUND_BRACKET_CLOSED), defaultState);
        stateMap.put(new Pair<>(commentSuspicionState, SEMICOLON), defaultState);
        stateMap.put(new Pair<>(commentSuspicionState, ID), defaultState);
        stateMap.put(new Pair<>(commentSuspicionState, SPACE), defaultState);
        stateMap.put(new Pair<>(commentSuspicionState, NEW_LINE), defaultState);
        stateMap.put(new Pair<>(commentSuspicionState, STRING_LITERAL), defaultState);
        stateMap.put(new Pair<>(commentSuspicionState, SLASH), oneLineCommentCollectionState);
        stateMap.put(new Pair<>(commentSuspicionState, ASTERISK), manyLinesCommentCollectionState);

        stateMap.put(new Pair<>(oneLineCommentCollectionState, NEW_LINE), defaultState);
        stateMap.put(new Pair<>(oneLineCommentCollectionState, CURLY_BRACKET_OPEN), oneLineCommentCollectionState);
        stateMap.put(new Pair<>(oneLineCommentCollectionState, CURLY_BRACKET_CLOSED), oneLineCommentCollectionState);
        stateMap.put(new Pair<>(oneLineCommentCollectionState, ROUND_BRACKET_OPEN), oneLineCommentCollectionState);
        stateMap.put(new Pair<>(oneLineCommentCollectionState, ROUND_BRACKET_CLOSED), oneLineCommentCollectionState);
        stateMap.put(new Pair<>(oneLineCommentCollectionState, SEMICOLON), oneLineCommentCollectionState);
        stateMap.put(new Pair<>(oneLineCommentCollectionState, ID), oneLineCommentCollectionState);
        stateMap.put(new Pair<>(oneLineCommentCollectionState, SPACE), oneLineCommentCollectionState);
        stateMap.put(new Pair<>(oneLineCommentCollectionState, SLASH), oneLineCommentCollectionState);
        stateMap.put(new Pair<>(oneLineCommentCollectionState, ASTERISK), oneLineCommentCollectionState);
        stateMap.put(new Pair<>(oneLineCommentCollectionState, STRING_LITERAL), oneLineCommentCollectionState);

        stateMap.put(new Pair<>(manyLinesCommentCollectionState, ASTERISK), manyLinesCommentsEndSuspicionState); // maybe end of the comment
        stateMap.put(new Pair<>(manyLinesCommentCollectionState, CURLY_BRACKET_OPEN), manyLinesCommentCollectionState);
        stateMap.put(new Pair<>(manyLinesCommentCollectionState, CURLY_BRACKET_CLOSED), manyLinesCommentCollectionState);
        stateMap.put(new Pair<>(manyLinesCommentCollectionState, ROUND_BRACKET_OPEN), manyLinesCommentCollectionState);
        stateMap.put(new Pair<>(manyLinesCommentCollectionState, ROUND_BRACKET_CLOSED), manyLinesCommentCollectionState);
        stateMap.put(new Pair<>(manyLinesCommentCollectionState, SEMICOLON), manyLinesCommentCollectionState);
        stateMap.put(new Pair<>(manyLinesCommentCollectionState, ID), manyLinesCommentCollectionState);
        stateMap.put(new Pair<>(manyLinesCommentCollectionState, SPACE), manyLinesCommentCollectionState);
        stateMap.put(new Pair<>(manyLinesCommentCollectionState, NEW_LINE), manyLinesCommentCollectionState);
        stateMap.put(new Pair<>(manyLinesCommentCollectionState, SLASH), manyLinesCommentCollectionState);
        stateMap.put(new Pair<>(manyLinesCommentCollectionState, STRING_LITERAL), manyLinesCommentCollectionState);

        stateMap.put(new Pair<>(manyLinesCommentsEndSuspicionState, SLASH), defaultState); // end of the comment
        stateMap.put(new Pair<>(manyLinesCommentsEndSuspicionState, CURLY_BRACKET_OPEN), manyLinesCommentCollectionState);
        stateMap.put(new Pair<>(manyLinesCommentsEndSuspicionState, CURLY_BRACKET_CLOSED), manyLinesCommentCollectionState);
        stateMap.put(new Pair<>(manyLinesCommentsEndSuspicionState, ROUND_BRACKET_OPEN), manyLinesCommentCollectionState);
        stateMap.put(new Pair<>(manyLinesCommentsEndSuspicionState, ROUND_BRACKET_CLOSED), manyLinesCommentCollectionState);
        stateMap.put(new Pair<>(manyLinesCommentsEndSuspicionState, SEMICOLON), manyLinesCommentCollectionState);
        stateMap.put(new Pair<>(manyLinesCommentsEndSuspicionState, ID), manyLinesCommentCollectionState);
        stateMap.put(new Pair<>(manyLinesCommentsEndSuspicionState, SPACE), manyLinesCommentCollectionState);
        stateMap.put(new Pair<>(manyLinesCommentsEndSuspicionState, NEW_LINE), manyLinesCommentCollectionState);
        stateMap.put(new Pair<>(manyLinesCommentsEndSuspicionState, ASTERISK), manyLinesCommentCollectionState);
        stateMap.put(new Pair<>(manyLinesCommentsEndSuspicionState, STRING_LITERAL), manyLinesCommentCollectionState);
    }

    /**
     * Gets next state
     *
     * @param state is the current state
     * @param type  is the current character's type
     * @return the next state
     */
    public State getNextState(final State state, final String type) {
        return stateMap.getOrDefault(new Pair<>(state, type), defaultState);
    }

    /**
     * Gets default state
     *
     * @return the next state
     */
    public State getDefaultState() {
        return defaultState;
    }
}
