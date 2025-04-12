package game.objective;

import game.board.Board;
import game.building.Building;
import game.building.LandBuilding;
import game.player.Player;

public class AresEnoughWarriors extends AresObjective{
    
    /* variables */
    public final int nbWarriors;

    /**
     * Create the EnoughWarriors objective
     * @param player the player which must achieve this objective
     * @param board the board
     */
    public AresEnoughWarriors(Player player, Board board){
        super(player, board, "Have "+board.getLength() + board.getWidth()+20+" warriors in buildings");
        this.nbWarriors = board.getLength() + board.getWidth()+20;
    }

    /**
     * Checks if the player has nbWarrios warriors.
     * @return true if the player has nbWarrios warriors.
     */
    public boolean isAchieved(){
        int nb = 0;
        for(Building building : this.player.getBuildings()){
            if (building instanceof LandBuilding){
                nb += ((LandBuilding)building).getDimension();
            }
        }
        return nb >= this.nbWarriors;
    }
}
