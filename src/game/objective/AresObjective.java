package game.objective;

import game.board.Board;
import game.player.Player;

/* a class to model an objective for Ares Game */
public abstract class AresObjective extends Objective {

    /**
     * Create an objective for Ares Game
     * @param player which must achieve the objective
     * @param board the board
     * @param description the description of the objective
     */
    public AresObjective(Player player, Board board, String description){
        super(player, board, description);
    } 
}
