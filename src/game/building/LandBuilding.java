package game.building;
import java.util.HashMap;
import java.util.Map;

import game.board.*;
import game.board.util.Ressource;
import game.player.*;

/* a class to represent the classic buildings */
public abstract class LandBuilding extends Building {

    /* attributes */
    protected int dimension;
    protected Map<Ressource, Integer> evolveCost;

    /**
     * creates a building with its player, land and dimension
     * @param player the player who owns this building
     * @param land the land where the building is placed
     * @param dimension the dimension of the building
     */
    public LandBuilding(Player player, Land land, int dimension){
        super(player, land);
        this.dimension = dimension;
        this.evolveCost = new HashMap<>();
    }

    /** gets the dimension of this building
     * @return this building's dimension
     */
    public int getDimension() {
        return this.dimension;
    }

    /**
     * Returns the cost of evolution of the building
     * @return the cost of evolution of the building
     */
    public Map<Ressource, Integer> getEvolveCost() {
        return this.evolveCost;
    }

    /** Creates a string representation of the building */
    public abstract String toString();

    /**
     * Returns true if the building is evolve.
     * @return true if the building is evolve.
     */
    public abstract boolean isEvolved();

    /**
     * Evolves the farm into a cultivation. If the building is already an exploitation, it does nothing.
     */
    public abstract void evolve();
}
