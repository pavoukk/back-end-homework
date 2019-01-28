package it.sevenbits.formatter.formatters.statemachine.commands;

import it.sevenbits.formatter.io.writers.exceptions.WriterException;

/**
 * ICommand is an executable's commands interface
 */
public interface ICommand {

    /**
     * Executes a command
     */
    void execute() throws WriterException;
}