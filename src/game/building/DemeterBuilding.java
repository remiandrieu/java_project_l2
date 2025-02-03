package game.building;
import game.board.*;
import game.player.*;

/* a class to represent the buildings for Demeter Game */
public class DemeterBuilding extends LandBuilding {

    /**
     * creates a building used in Demeter Game
     * @param player the player who owns this building
     * @param land the land where the building is placed
     * @param dimension the dimension of the building
     */
    public DemeterBuilding(Player player, Land land, int dimension){
        super(player, land, dimension);
    }
    
}
