package game.action;

import game.board.Board;

/* a class to model an action for DemeterGame */
public abstract class DemeterAction extends Action {

     /**
     * Create an action for DemeterGame with its label and a board
     * @param label the label of the action
     * @param board the board
     */
    public DemeterAction(String label, Board board){
        super(label, board);
    }

}
