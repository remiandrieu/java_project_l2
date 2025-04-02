package game.objective;

import game.board.Board;
import game.player.Player;

/* a class to model an objective for Demeter Game */
public abstract class DemeterObjective extends Objective {

    /**
     * Create an objective for Demeter Game
     * @param player which must achieve the objective
     * @param board the board
     * @param description the description of the objective
     */
    public DemeterObjective(Player player, Board board, String description){
        super(player, board, description);
    }
}
