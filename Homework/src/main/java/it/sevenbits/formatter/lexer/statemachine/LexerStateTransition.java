package it.sevenbits.formatter.lexer.statemachine;

public class LexerStateTransition {
    private LexerStateMap stateMap;

    public LexerStateTransition() {
        stateMap = new LexerStateMap();
    }

    public State getNextState(final State state, final String type) {
        return stateMap.getNextState(state, type);
    }

    public State getDefaultState() {
        return stateMap.getDefaultState();
    }
}
