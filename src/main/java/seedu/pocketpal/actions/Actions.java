package seedu.pocketpal.actions;

import seedu.pocketpal.frontend.commands.Command;

import java.util.EmptyStackException;
import java.util.Stack;
import java.util.logging.Logger;

/**
 * Used to keep track of commands to perform redo/undo actions
 */
public class Actions {
    private static final Logger logger = Logger.getLogger(Actions.class.getName());
    private final Stack<Command> undoActions;
    private final Stack<Command> redoActions;

    public Actions() {
        undoActions = new Stack<>();
        redoActions = new Stack<>();
    }

    /**
     * Store the command after it has been executed.
     * Note: This action will clear any redo actions.
     *
     * @param command Command to be stored
     */
    public void addExecuted(Command command) {
        assert command != null;
        logger.info("Adding executed command: " + command.getClass().getName());
        undoActions.push(command);
        redoActions.clear();
    }

    /**
     * Retrieve the Command to perform the redo action.
     *
     * @return Most recently undone Command if any, null otherwise
     */
    public Command retrieveRedo() {
        try {
            logger.info("Retrieve redo action");
            Command redoCommand = redoActions.pop();
            assert redoCommand != null;
            undoActions.push(redoCommand);
            return redoCommand;
        } catch (EmptyStackException e) {
            logger.info("No redo actions available");
            return null;
        }
    }

    /**
     * Retrieve the Command to perform the undo action.
     *
     * @return Most recently executed Command if any, null otherwise
     */
    public Command retrieveUndo() {
        try {
            logger.info("Retrieve undo action");
            Command undoCommand = undoActions.pop();
            assert undoCommand != null;
            redoActions.push(undoCommand);
            return undoCommand;
        } catch (EmptyStackException e) {
            logger.info("No undo actions available");
            return null;
        }
    }

    /**
     * Clear all stored data
     */
    public void reset() {
        redoActions.clear();
        undoActions.clear();
    }
}
