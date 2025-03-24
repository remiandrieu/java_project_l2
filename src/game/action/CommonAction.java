package game.action;

import game.board.Board;

/* an abstract class for common actions */
public abstract class CommonAction extends Action{

    /**
     * Create an common action with its label and a board
     * @param label the label of the action
     * @param board the board
     */
    public CommonAction(String label, Board board) {
        super(label, board);
    }
}
