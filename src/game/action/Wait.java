package game.action;

import game.board.Board;
import game.player.Player;

/* A class to model the Wait action for both games */
public class Wait extends CommonAction{

    /**
     * creates a class for waiting
     * @param board the board
     */
    public Wait(Board board){
        super("wait", board);
    }

    /**
     * Always returns true
     * @param player the player
     * @return true anyway
     */
    public boolean isPossible(Player player) {
        return true;
    }

    /**
     * Executes the consequences of this action (a String representation for the Wait action)
     * @param player the player
     */
    public void act(Player player) {
        System.out.println(player + " waits\n");
    }

}
