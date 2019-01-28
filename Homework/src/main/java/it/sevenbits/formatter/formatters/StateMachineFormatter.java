package it.sevenbits.formatter.formatters;

import it.sevenbits.formatter.formatters.statemachine.FormatterStateTransition;
import it.sevenbits.formatter.formatters.statemachine.State;
import it.sevenbits.formatter.io.readers.IReader;
import it.sevenbits.formatter.io.readers.exceptions.ReaderException;
import it.sevenbits.formatter.io.writers.IWriter;
import it.sevenbits.formatter.io.writers.exceptions.WriterException;
import it.sevenbits.formatter.lexer.ILexer;
import it.sevenbits.formatter.lexer.factories.ILexerFactory;
import it.sevenbits.formatter.formatters.statemachine.commands.CommandContext;
import it.sevenbits.formatter.formatters.statemachine.commands.CommandManager;
import it.sevenbits.formatter.lexer.token.IToken;

/**
 * StateMachineFormatter is just like the FileFormatter, but based on State Machine. It's better. Smarter. Higher. Faster.
 */
public class StateMachineFormatter implements IFormatter {
    private ILexerFactory factory;
    private FormatterStateTransition stateTransition;

    /**
     * A constructor with one parameter
     *
     * @param factory is a lexer factory
     */
    public StateMachineFormatter(final ILexerFactory factory) {
        this.factory = factory;
        stateTransition = new FormatterStateTransition();
    }

    @Override
    public void format(final IReader reader, final IWriter writer) throws FormatterException {
        CommandContext commandContext = new CommandContext(writer);
        CommandManager commandManager = new CommandManager(commandContext);
        ILexer lex = factory.createLexer(reader);
        State state = stateTransition.getStartState();
        try {
            IToken token;
            while (lex.hasMoreTokens()) {
                token = lex.readToken();
                commandContext.setToken(token);

                commandManager.getCommand(state, token.getName()).execute();
                state = stateTransition.getNextState(state, token.getName());
            }
        } catch (ReaderException | WriterException e) {
            throw new FormatterException(e);
        }
    }
}
