package game.action;

import game.board.Board;

/* an abstract class for start actions */
public abstract class StartAction extends Action{

    /**
     * Create a start action with its label and a board
     * @param label the label of the action
     * @param board the board
     */
    public StartAction(String label, Board board) {
        super(label, board);
    }
}
