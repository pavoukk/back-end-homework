package it.sevenbits.formatter.lexer.statemachine;

/**
 * LexerStateTransition operates with LexerStateMap
 */
public class LexerStateTransition {
    private LexerStateMap stateMap;

    /**
     * A constructor with no parameters
     */
    public LexerStateTransition() {
        stateMap = new LexerStateMap();
    }

    /**
     * Gets next state
     *
     * @param state current state
     * @param type  current type of character
     * @return next state
     */
    public State getNextState(final State state, final String type) {
        return stateMap.getNextState(state, type);
    }

    /**
     * @return default state of state machine
     */
    public State getDefaultState() {
        return stateMap.getDefaultState();
    }
}
