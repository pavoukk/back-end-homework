package it.sevenbits.formatter.formatters.statemachine;

/**
 * FormatterStateTransition operates with FormatterStateMap
 */
public class FormatterStateTransition {
    private FormatterStateMap stateMap;

    /**
     * A constructor with no parameters
     */
    public FormatterStateTransition() {
        stateMap = new FormatterStateMap();
    }

    /**
     * @return default state of state machine
     */
    public State getStartState() {
        return stateMap.getStartState();
    }

    /**
     * Gets next state
     *
     * @param current current state
     * @param type    current type of character
     * @return next state
     */
    public State getNextState(final State current, final String type) {
        return stateMap.getNextState(current, type);
    }
}
