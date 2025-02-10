package game.building;
import game.board.*;
import game.player.*;

/* a class to represent the buildings for Demeter Game */
public class DemeterBuilding extends LandBuilding {
    protected boolean level; //Level of the building, false if farm, true if cultivation

    /**
     * creates a building used in Demeter Game
     * @param player the player who owns this building
     * @param land the land where the building is placed
     * @param dimension the dimension of the building
     */
    public DemeterBuilding(Player player, Land land, int dimension){
        super(player, land, dimension);
        this.level = false;
    }

    /**
     * Evolves the farm into a cultivation. If the building is already an exploitation, it does nothing.
     */
    public void evolve(){
        if (!this.level){
            this.level = true;
        }
    }

    /**
     * Returns false if the building is a farm, 
     * true if the building is a cultivation.
     * @return the state of the building.
     */
    public boolean isEvolved(){
        return level;
    }
    

}
