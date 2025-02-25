package game.building;
import game.board.*;
import game.board.util.Ressource;
import game.player.*;

/* a class to represent the buildings for Demeter Game */
public class DemeterBuilding extends LandBuilding {
    // The dimension can replace the level: if it's equal to 1
    // it is not evolved, if it's equal to 2, it is.

    /**
     * creates a building used in Demeter Game
     * @param player the player who owns this building
     * @param land the land where the building is placed
     */
    public DemeterBuilding(Player player, Land land){
        super(player, land, 1);
        this.cost.put(Ressource.WOOD, 1);
        this.cost.put(Ressource.ORE, 1);
    }

    /**
     * Evolves the farm into a cultivation. If the building is already an exploitation, it does nothing.
     */
    public void evolve(){
        this.dimension = 2;
        this.cost.clear();
        this.cost.put(Ressource.WOOD, 2);
        this.cost.put(Ressource.WHEAT, 1);
        this.cost.put(Ressource.SHEEP, 1);
    }

    /**
     * Returns false if the building is a farm, 
     * true if the building is a cultivation.
     * @return the state of the building.
     */
    public boolean isEvolved(){
        return this.dimension == 2;
    }
    
    /**
     * The player who owns the building will collect the resources of the land.
     * If the building is evolved, does it a second time.
     */
    public void playerCollectRessources() {
        super.playerCollectRessources();

        if (this.isEvolved()){
            super.playerCollectRessources(); 
        }
    }

    public String toString() {
        if (this.isEvolved()){
            return "Big farm";
        }else{
            return "Farm";
        } 
    }

}
