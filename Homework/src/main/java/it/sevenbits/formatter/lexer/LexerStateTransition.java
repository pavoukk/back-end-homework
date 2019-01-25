package it.sevenbits.formatter.lexer;

public class LexerStateTransition {
    private LexerStateMap stateMap;

    public LexerStateTransition() {
        stateMap = new LexerStateMap();
    }

    public State getNextState(final State state, final Symbol symbol) {
        return stateMap.getNextState(state, symbol.getType());
    }

    public State getDefaultState() {
        return stateMap.getDefaultState();
    }
}
