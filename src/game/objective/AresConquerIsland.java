package game.objective;

import game.player.Player;
import game.board.*;

import java.util.*;

public class AresConquerIsland extends AresObjective {

    public AresConquerIsland(Player player, Board board){
        super(player, board, "Conquer an entire island (Have a building on every tile of any island)");
    }

    /**
     * Checks if the player conquered an entire island.
     * @return true if the player conquered an entire island.
     */
    public boolean isAchieved(){
        ArrayList<ArrayList<Coordinates>> islands = null;

        try{
            islands = this.board.detectIslands();
        } catch(Exception e){
        }

        for (ArrayList<Coordinates> island : islands) {
            if (isIslandConquered(island)){
                return true;
            }
        }

        return false;
    }

    /**
     * Checks if the given island is conquered by the player.
     * @param island the island to check if it is conquered
     * @return true if the island is conquered by the player
     */
    public boolean isIslandConquered(ArrayList<Coordinates> island){
        for (Coordinates coordinates : island) {
            try{
                if ( !(((Land) this.board.getTile(coordinates.getX(), coordinates.getY())).hasBuilding() && ((Land) this.board.getTile(coordinates.getX(), coordinates.getY())).getBuilding().getPlayer() == this.player)){
                    return false;
                }
            } catch(Exception e){
                return false;
            }
        }
        return true;
    }
}