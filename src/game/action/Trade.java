package game.action;

import game.board.Board;
/* a class to model trading action */
public class Trade extends TradeAction{

    /**
     * creates a class for trading
     * @param board
     */
    public Trade(Board board) {
        super("trade", board);
    }

    protected int getNbRessource(){
        return 3;
    }

}
