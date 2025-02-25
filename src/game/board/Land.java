package game.board;
import game.board.util.Ressource;
import game.building.*;

/** an abstract class to represent a land */
public abstract class Land extends Tile {
    protected Building building;

    /**
     * Changes the building of the land.
     * @param building
     */
    public void changeBuilding(Building building){
        this.building = building;
    }

    /**
     * Return true if the land has a building
     * @return true if the land has a building.
     */
    public boolean hasBuilding(){
        return building != null;
    } 

    /** creates an abstract method to produce the different ressources of the board
     * @return the ressource
     */
    public abstract Ressource produce();

    /** creates a string representation of the ressource in this land
     * @return a string representation of the ressource in this land
     */
    public abstract String toString();

    /**
     * @return the building
     */
    public Building getBuilding() {
        return building;
    }
}
