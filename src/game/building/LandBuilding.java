package game.building;
import game.board.*;
import game.board.util.*;
import game.player.*;

/** a class to represent the classic buildings */
public abstract class LandBuilding extends Building {

    /** attributes */
    protected int dimension;

    /**
     * creates a building with its dimension
     * @param dimension the dimension of the building
     */
    public LandBuilding(Player player, Land land, int dimension){
        super(player, land);
        this.dimension = dimension;
    }

    /** gets the dimension of this building
     * @return this building's dimension
     */
    public int getDimension() {
        return this.dimension;
    }

    /** collects the ressources produced by this building's landland
     * @return the ressource produced
     */
    public Ressource collectRessources(){
        return this.land.produce();
    }
}
