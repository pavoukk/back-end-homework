package it.sevenbits.formatter.formatters.statemachine;

import java.util.Objects;

/**
 * A container of some two objects
 *
 * @param <T> first object
 * @param <V> second object
 */
public class Pair<T, V> {
    private final T first;
    private final V second;

    /**
     * A constructor with two parameters
     *
     * @param first  set first
     * @param second set seconnd
     */
    public Pair(final T first, final V second) {
        this.first = first;
        this.second = second;
    }

    /**
     * @return first object
     */
    public T getFirst() {
        return first;
    }

    /**
     * @return second object
     */
    public V getSecond() {
        return second;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(first, pair.first) &&
                Objects.equals(second, pair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
