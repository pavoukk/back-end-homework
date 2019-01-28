package it.sevenbits.formatter.formatters.statemachine;

import java.util.Objects;

/**
 * State of Lexer automate
 */
public class State {
    private String state;

    /**
     * A constructor with one parameter
     *
     * @param state name of the state
     */
    public State(final String state) {
        this.state = state;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        State state1 = (State) o;
        return Objects.equals(state, state1.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state);
    }

    @Override
    public String toString() {
        return state;
    }
}
