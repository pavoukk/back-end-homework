package it.sevenbits.formatter.formatters.statemachine;

import java.util.HashMap;
import java.util.Map;

/**
 * FormatterStateMap transits one Formatter state to other
 */
public class FormatterStateMap {
    private Map<Pair<State, String>, State> stateMap;

    private State defaultState = new State("DEFAULT");

    private final String ID = "ID";
    private final String CURLY_BRACKET_OPEN = "CURLY_BRACKET_OPEN";
    private final String CURLY_BRACKET_CLOSED = "CURLY_BRACKET_CLOSED";
    private final String ROUND_BRACKET_OPEN = "ROUND_BRACKET_OPEN";
    private final String ROUND_BRACKET_CLOSED = "ROUND_BRACKET_CLOSED";
    private final String SEMICOLON = "SEMICOLON";
    private final String ONE_LINE_COMMENT = "ONE_LINE_COMMENT";
    private final String MANY_LINES_COMMENT = "MANY_LINES_COMMENT";
    private final String STRING_LITERAL = "STRING_LITERAL";

    /**
     * Creates Formatter state map
     */
    public FormatterStateMap() {
        State idOrLiteralState = new State("ID_OR_LITERAL");
        State curlyBracketOpenState = new State("CURLY_BRACKET_OPEN");
        State curlyBracketClosedState = new State("CURLY_BRACKET_CLOSED");
        State roundBracketOpenState = new State("ROUND_BRACKET_OPEN");
        State roundBracketClosedState = new State("ROUND_BRACKET_CLOSED");
        State semicolonState = new State("SEMICOLON");
        State oneLineCommentState = new State("ONE_LINE_COMMENT");
        State manyLinesCommentState = new State("MANY_LINES_COMMENT");

        stateMap = new HashMap<>();
        stateMap.put(new Pair<>(defaultState, ID), idOrLiteralState);
        stateMap.put(new Pair<>(defaultState, STRING_LITERAL), idOrLiteralState);
        stateMap.put(new Pair<>(defaultState, CURLY_BRACKET_OPEN), curlyBracketOpenState);
        stateMap.put(new Pair<>(defaultState, CURLY_BRACKET_CLOSED), curlyBracketClosedState);
        stateMap.put(new Pair<>(defaultState, ROUND_BRACKET_OPEN), roundBracketOpenState);
        stateMap.put(new Pair<>(defaultState, ROUND_BRACKET_CLOSED), roundBracketClosedState);
        stateMap.put(new Pair<>(defaultState, SEMICOLON), semicolonState);
        stateMap.put(new Pair<>(defaultState, ONE_LINE_COMMENT), oneLineCommentState);
        stateMap.put(new Pair<>(defaultState, MANY_LINES_COMMENT), manyLinesCommentState);

        stateMap.put(new Pair<>(idOrLiteralState, ID), idOrLiteralState);
        stateMap.put(new Pair<>(idOrLiteralState, STRING_LITERAL), idOrLiteralState);
        stateMap.put(new Pair<>(idOrLiteralState, CURLY_BRACKET_OPEN), curlyBracketOpenState);
        stateMap.put(new Pair<>(idOrLiteralState, CURLY_BRACKET_CLOSED), curlyBracketClosedState);
        stateMap.put(new Pair<>(idOrLiteralState, ROUND_BRACKET_OPEN), roundBracketOpenState);
        stateMap.put(new Pair<>(idOrLiteralState, ROUND_BRACKET_CLOSED), roundBracketClosedState);
        stateMap.put(new Pair<>(idOrLiteralState, SEMICOLON), semicolonState);
        stateMap.put(new Pair<>(idOrLiteralState, ONE_LINE_COMMENT), oneLineCommentState);
        stateMap.put(new Pair<>(idOrLiteralState, MANY_LINES_COMMENT), manyLinesCommentState);

        stateMap.put(new Pair<>(curlyBracketOpenState, ID), idOrLiteralState);
        stateMap.put(new Pair<>(curlyBracketOpenState, STRING_LITERAL), idOrLiteralState);
        stateMap.put(new Pair<>(curlyBracketOpenState, CURLY_BRACKET_OPEN), curlyBracketOpenState);
        stateMap.put(new Pair<>(curlyBracketOpenState, CURLY_BRACKET_CLOSED), curlyBracketClosedState);
        stateMap.put(new Pair<>(curlyBracketOpenState, ROUND_BRACKET_OPEN), roundBracketOpenState);
        stateMap.put(new Pair<>(curlyBracketOpenState, ROUND_BRACKET_CLOSED), roundBracketClosedState);
        stateMap.put(new Pair<>(curlyBracketOpenState, SEMICOLON), semicolonState);
        stateMap.put(new Pair<>(curlyBracketOpenState, ONE_LINE_COMMENT), oneLineCommentState);
        stateMap.put(new Pair<>(curlyBracketOpenState, MANY_LINES_COMMENT), manyLinesCommentState);

        stateMap.put(new Pair<>(curlyBracketClosedState, ID), idOrLiteralState);
        stateMap.put(new Pair<>(curlyBracketClosedState, STRING_LITERAL), idOrLiteralState);
        stateMap.put(new Pair<>(curlyBracketClosedState, CURLY_BRACKET_OPEN), curlyBracketOpenState);
        stateMap.put(new Pair<>(curlyBracketClosedState, CURLY_BRACKET_CLOSED), curlyBracketClosedState);
        stateMap.put(new Pair<>(curlyBracketClosedState, ROUND_BRACKET_OPEN), roundBracketOpenState);
        stateMap.put(new Pair<>(curlyBracketClosedState, ROUND_BRACKET_CLOSED), roundBracketClosedState);
        stateMap.put(new Pair<>(curlyBracketClosedState, SEMICOLON), semicolonState);
        stateMap.put(new Pair<>(curlyBracketClosedState, ONE_LINE_COMMENT), oneLineCommentState);
        stateMap.put(new Pair<>(curlyBracketClosedState, MANY_LINES_COMMENT), manyLinesCommentState);

        stateMap.put(new Pair<>(roundBracketOpenState, ID), idOrLiteralState);
        stateMap.put(new Pair<>(roundBracketOpenState, STRING_LITERAL), idOrLiteralState);
        stateMap.put(new Pair<>(roundBracketOpenState, CURLY_BRACKET_OPEN), curlyBracketOpenState);
        stateMap.put(new Pair<>(roundBracketOpenState, CURLY_BRACKET_CLOSED), curlyBracketClosedState);
        stateMap.put(new Pair<>(roundBracketOpenState, ROUND_BRACKET_OPEN), roundBracketOpenState);
        stateMap.put(new Pair<>(roundBracketOpenState, ROUND_BRACKET_CLOSED), roundBracketClosedState);
        stateMap.put(new Pair<>(roundBracketOpenState, SEMICOLON), semicolonState);
        stateMap.put(new Pair<>(roundBracketOpenState, ONE_LINE_COMMENT), oneLineCommentState);
        stateMap.put(new Pair<>(roundBracketOpenState, MANY_LINES_COMMENT), manyLinesCommentState);

        stateMap.put(new Pair<>(roundBracketClosedState, ID), idOrLiteralState);
        stateMap.put(new Pair<>(roundBracketClosedState, STRING_LITERAL), idOrLiteralState);
        stateMap.put(new Pair<>(roundBracketClosedState, CURLY_BRACKET_OPEN), curlyBracketOpenState);
        stateMap.put(new Pair<>(roundBracketClosedState, CURLY_BRACKET_CLOSED), curlyBracketClosedState);
        stateMap.put(new Pair<>(roundBracketClosedState, ROUND_BRACKET_OPEN), roundBracketOpenState);
        stateMap.put(new Pair<>(roundBracketClosedState, ROUND_BRACKET_CLOSED), roundBracketClosedState);
        stateMap.put(new Pair<>(roundBracketClosedState, SEMICOLON), semicolonState);
        stateMap.put(new Pair<>(roundBracketClosedState, ONE_LINE_COMMENT), oneLineCommentState);
        stateMap.put(new Pair<>(roundBracketClosedState, MANY_LINES_COMMENT), manyLinesCommentState);

        stateMap.put(new Pair<>(semicolonState, ID), idOrLiteralState);
        stateMap.put(new Pair<>(semicolonState, STRING_LITERAL), idOrLiteralState);
        stateMap.put(new Pair<>(semicolonState, CURLY_BRACKET_OPEN), curlyBracketOpenState);
        stateMap.put(new Pair<>(semicolonState, CURLY_BRACKET_CLOSED), curlyBracketClosedState);
        stateMap.put(new Pair<>(semicolonState, ROUND_BRACKET_OPEN), roundBracketOpenState);
        stateMap.put(new Pair<>(semicolonState, ROUND_BRACKET_CLOSED), roundBracketClosedState);
        stateMap.put(new Pair<>(semicolonState, SEMICOLON), semicolonState);
        stateMap.put(new Pair<>(semicolonState, ONE_LINE_COMMENT), oneLineCommentState);
        stateMap.put(new Pair<>(semicolonState, MANY_LINES_COMMENT), manyLinesCommentState);

        stateMap.put(new Pair<>(oneLineCommentState, ID), idOrLiteralState);
        stateMap.put(new Pair<>(oneLineCommentState, STRING_LITERAL), idOrLiteralState);
        stateMap.put(new Pair<>(oneLineCommentState, CURLY_BRACKET_OPEN), curlyBracketOpenState);
        stateMap.put(new Pair<>(oneLineCommentState, CURLY_BRACKET_CLOSED), curlyBracketClosedState);
        stateMap.put(new Pair<>(oneLineCommentState, ROUND_BRACKET_OPEN), roundBracketOpenState);
        stateMap.put(new Pair<>(oneLineCommentState, ROUND_BRACKET_CLOSED), roundBracketClosedState);
        stateMap.put(new Pair<>(oneLineCommentState, SEMICOLON), semicolonState);
        stateMap.put(new Pair<>(oneLineCommentState, ONE_LINE_COMMENT), oneLineCommentState);
        stateMap.put(new Pair<>(oneLineCommentState, MANY_LINES_COMMENT), manyLinesCommentState);

        stateMap.put(new Pair<>(manyLinesCommentState, ID), idOrLiteralState);
        stateMap.put(new Pair<>(manyLinesCommentState, STRING_LITERAL), idOrLiteralState);
        stateMap.put(new Pair<>(manyLinesCommentState, CURLY_BRACKET_OPEN), curlyBracketOpenState);
        stateMap.put(new Pair<>(manyLinesCommentState, CURLY_BRACKET_CLOSED), curlyBracketClosedState);
        stateMap.put(new Pair<>(manyLinesCommentState, ROUND_BRACKET_OPEN), roundBracketOpenState);
        stateMap.put(new Pair<>(manyLinesCommentState, ROUND_BRACKET_CLOSED), roundBracketClosedState);
        stateMap.put(new Pair<>(manyLinesCommentState, SEMICOLON), semicolonState);
        stateMap.put(new Pair<>(manyLinesCommentState, ONE_LINE_COMMENT), oneLineCommentState);
        stateMap.put(new Pair<>(manyLinesCommentState, MANY_LINES_COMMENT), manyLinesCommentState);
    }

    /**
     * Gets default state
     *
     * @return the next state
     */
    public State getStartState() {
        return defaultState;
    }

    /**
     * Gets next state
     *
     * @param current is the current state
     * @param type    is the current character's type
     * @return the next state
     */
    public State getNextState(final State current, final String type) {
        return stateMap.getOrDefault(new Pair<>(current, type), defaultState);
    }
}
