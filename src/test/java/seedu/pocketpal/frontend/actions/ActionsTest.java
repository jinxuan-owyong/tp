package seedu.pocketpal.frontend.actions;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import seedu.pocketpal.actions.Actions;
import seedu.pocketpal.data.EntryTestUtil;
import seedu.pocketpal.frontend.commands.AddCommand;
import seedu.pocketpal.frontend.commands.Command;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("Test Actions")
public class ActionsTest extends EntryTestUtil {
    private static final Actions actions = new Actions();

    @BeforeEach
    void init() {
        actions.reset();
        TEST_BACKEND.clearData();
    }

    @Test
    void retrieveRedoAction_noActions_nullReturned() {
        assertNull(actions.retrieveRedo());
    }

    @Test
    void retrieveUndoAction_noActions_nullReturned() {
        assertNull(actions.retrieveUndo());
    }

    @Test
    void retrieveUndoAction_returnSameCommand_returnSameRedo() {
        final Command executedCommand = new AddCommand(ENTRY_1);
        actions.addExecuted(executedCommand);

        final Command undoCommand = actions.retrieveUndo();
        final Command redoCommand = actions.retrieveRedo();

        assertEquals(executedCommand, undoCommand);
        assertEquals(executedCommand, redoCommand);
    }

    @Test
    void retrieveRedoAction_returnSameCommand_returnSameUndo() {
        final Command executedCommand = new AddCommand(ENTRY_1);
        actions.addExecuted(executedCommand);

        actions.retrieveUndo();
        final Command redoCommand = actions.retrieveRedo();
        final Command undoCommand = actions.retrieveUndo();

        assertEquals(executedCommand, redoCommand);
        assertEquals(executedCommand, undoCommand);
    }
}
