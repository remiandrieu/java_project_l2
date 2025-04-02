package game.objective;

import java.util.ArrayList;
import java.util.List;
import game.board.Board;
import game.board.Coordinates;
import game.board.Land;
import game.player.Player;
import game.building.Building;
import game.building.DemeterBuilding;
import game.building.Port;

/* a class to model the enough points objective for Demeter Game */
public class EnoughPoints extends DemeterObjective {

    /* variables */
    public static final int point = 12;

    /**
     * Create the EnoughPoints objective
     * @param player the player which must achieve this objective
     * @param board the board
     */
    public EnoughPoints(Player player, Board board){
        super(player, board, "You need to get at least " + point + " points.");
    }

    /**
     * checks if the objective is achieved
     * @return true if the objective is achieved, false otherwise
     */
    public boolean isAchieved(){
        List<Building> buildings = this.player.getBuildings();
        int nbPoints = 0;
        for (Building building : buildings){
            if (!(building instanceof Port)){
                DemeterBuilding newBuilding = (DemeterBuilding) building;
                nbPoints += newBuilding.getDimension();
            }   
        }
        ArrayList<ArrayList<Coordinates>> islands = null;
        try{
            islands = this.board.detectIslands(); 
        }
        catch(Exception e){
        }
        int nbOfConqueredIslands = 0;
        for (ArrayList<Coordinates> island : islands) {
            if (isPlayerOnIsland(island)){
                nbOfConqueredIslands += 1;
            }
        }
        if (nbOfConqueredIslands == 2){
            return nbPoints + 1 >= point;
        }
        else if (nbOfConqueredIslands > 2){
            return nbPoints + 2 >= point;
        }
        else {
            return nbPoints >= point;
        }
    } 

    /**
     * checks if a player is implanted on an island (he must own 2 buildings on the same island)
     * @param island the island
     * @return true if the player has 2 or more buildings on the island
     */
    public boolean isPlayerOnIsland(ArrayList<Coordinates> island){
        int nbBuildings = 0;
        for (Coordinates coordinates : island) {
            try{
                if ((((Land) this.board.getTile(coordinates.getX(), coordinates.getY())).hasBuilding() && ((Land) this.board.getTile(coordinates.getX(), coordinates.getY())).getBuilding().getPlayer() == this.player)){
                    nbBuildings += 1;
                }
            } catch(Exception e){
            }
        }
        return nbBuildings >= 2;
    }
}
