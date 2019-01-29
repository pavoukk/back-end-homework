package it.sevenbits.formatter.lexer;

import it.sevenbits.formatter.io.readers.exceptions.ReaderException;
import it.sevenbits.formatter.lexer.exceptions.LexerException;
import it.sevenbits.formatter.lexer.statemachine.LexerStateTransition;
import it.sevenbits.formatter.lexer.statemachine.State;
import it.sevenbits.formatter.lexer.statemachine.commands.CommandContext;
import it.sevenbits.formatter.lexer.statemachine.commands.CommandManager;
import it.sevenbits.formatter.lexer.token.IToken;
import it.sevenbits.formatter.io.readers.IReader;
import it.sevenbits.formatter.lexer.token.Token;

import java.util.HashMap;
import java.util.Map;

/**
 * Lexer is an ILexer's implementation.
 * It gets IReader object while creating and translates object's info into tokens.
 */
public class Lexer implements ILexer {
    private IReader reader;
    private Map<Character, String> symbolTypesMap;
    private CommandContext commandContext;
    private CommandManager commandManager;
    private LexerStateTransition lexerStateTransition;

    /**
     * Constructor with one parameter
     *
     * @param reader is a characters stream
     */
    public Lexer(final IReader reader) {
        this.reader = reader;
        commandContext = new CommandContext(new StringBuilder());
        commandManager = new CommandManager(commandContext);
        lexerStateTransition = new LexerStateTransition();

        symbolTypesMap = new HashMap<>();
        symbolTypesMap.put('{', "CURLY_BRACKET_OPEN");
        symbolTypesMap.put('}', "CURLY_BRACKET_CLOSED");
        symbolTypesMap.put(';', "SEMICOLON");
        symbolTypesMap.put('(', "ROUND_BRACKET_OPEN");
        symbolTypesMap.put(')', "ROUND_BRACKET_CLOSED");
        symbolTypesMap.put('\n', "NEW_LINE");
        symbolTypesMap.put(' ', "SPACE");
        symbolTypesMap.put('/', "SLASH");
        symbolTypesMap.put('*', "ASTERISK");
        symbolTypesMap.put('\"', "STRING_LITERAL");
    }

    @Override
    public IToken readToken() throws LexerException {
        State state = lexerStateTransition.getDefaultState();
        char character;
        if (commandContext.getReservedSymbol() != 0) {
            character = commandContext.getReservedSymbol();
            commandContext.setReservedSymbol('\0');
            commandContext.setCurrentSymbol(character);
            state = transitState(character, state);
        }

        while (hasMoreTokens() && !commandContext.isPoison()) {
            try {
                character = reader.read();
            } catch (ReaderException e) {
                throw new LexerException("Cannot read any more characters", e);
            }
            commandContext.setCurrentSymbol(character);
            state = transitState(character, state);
        }

        IToken token = new Token(commandContext.getLexemeType(), commandContext.getLexemeBuffer().toString());
        commandContext.getLexemeBuffer().setLength(0);
        commandContext.setLexemeType(null);
        commandContext.setPoison(false);
        return token;
    }

    /**
     * Transits current state to the next state
     *
     * @param character is the current character
     * @param state     is the current state
     * @return the next state
     */
    private State transitState(final char character, final State state) {
        String type = symbolTypesMap.getOrDefault(character, "ID");
        commandManager.getCommand(state, type).execute();
        return lexerStateTransition.getNextState(state, type);
    }

    @Override
    public boolean hasMoreTokens() throws LexerException {
        try {
            return reader.hasNext() || commandContext.getReservedSymbol() != 0;
        } catch (ReaderException e) {
            throw new LexerException("Cannot call reader", e);
        }
    }
}
