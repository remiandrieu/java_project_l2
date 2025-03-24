package game.action;

import game.board.Board;

/* a class to model an action for AresGame */
public abstract class AresAction extends Action {

    /**
     * Create an action for AresGame with its label and a board
     * @param label the label of the action
     * @param board the board
     */
    public AresAction(String label, Board board){
        super(label, board);
    }
    
}
