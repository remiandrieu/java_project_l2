package game.objective;

import game.board.Board;
import game.player.Player;

/* a class to model the conquer nb tiles objective for Ares Game */
public class AresConquerTiles extends AresObjective{
    
    /* variables */
    public final int nbTiles;

    /**
     * Create the ConquerTiles objective
     * @param player the player which must achieve this objective
     * @param board the board
     */
    public AresConquerTiles(Player player, Board board){
        super(player, board, "Conquer "+(board.getLength()+board.getWidth())/2+" tiles");
        this.nbTiles = (board.getLength()+board.getWidth())/2;
    }
    
    /**
     * Checks if the player conquered nbTiles tiles.
     * @return true if the player conquered an nbTiles tiles.
     */
    public boolean isAchieved(){
        return this.player.getBuildings().size()>=nbTiles;
    }
}
