package it.sevenbits.formatter.formatters.statemachine.commands;

import it.sevenbits.formatter.io.writers.exceptions.WriterException;

public class WriteTokenCommand implements ICommand {
    private CommandContext commandContext;
    private ICommand next;

    public WriteTokenCommand(final CommandContext commandContext) {
        this.commandContext = commandContext;
    }

    public WriteTokenCommand(final CommandContext commandContext, final ICommand next) {
        this.commandContext = commandContext;
        this.next = next;
    }

    @Override
    public void execute() {
        String lexeme = commandContext.getToken().getLexeme();
        for (char el: lexeme.toCharArray()) {
            try {
                commandContext.getWriter().write(el);
                next();
            } catch (WriterException e) {
                e.printStackTrace(); //TODO add exception to execute signature
            }
        }
    }

    private void next() {
        if (next != null) {
            next.execute();
        }
    }
}
