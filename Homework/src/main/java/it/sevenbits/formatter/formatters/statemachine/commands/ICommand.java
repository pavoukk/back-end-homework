package it.sevenbits.formatter.formatters.statemachine.commands;

import it.sevenbits.formatter.io.writers.exceptions.WriterException;

/**
 * ICommand is an executable's commands interface
 */
public interface ICommand {

    /**
     * Executes a command
     *
     * @throws WriterException is a Writer's exception
     */
    void execute() throws WriterException;
}