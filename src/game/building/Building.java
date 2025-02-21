package game.building;

import game.board.*;
import game.board.util.Ressource;
import game.player.*;

import java.util.*;

/* a class to represent the buildings */
public abstract class Building {

    /* attributes */
    protected Player player;
    protected Land land;
    protected Map<Ressource, Integer> cost;

    /**
     * creates a building with its player and land
     * @param player the player who owns this building
     * @param land the land where the building is standing
     */
    public Building(Player player, Land land) {
        this.player = player;
        this.land = land;
        this.cost =  new HashMap<>();
        land.changeBuilding(this);
    }

    /**
     * Returns the building's owner.
     * @return the building's owner.
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Returns the land where the building is standing.
     * @return the land where the building is standing.
     */
    public Land getLand() {
        return this.land;
    }

    public Map<Ressource, Integer> getCost(){
        return cost;
    }

    /** collects the ressources produced by this building's landland
     * @return the ressource produced
     */
    public Ressource collectRessources(){
        return this.land.produce();
    }

    /**
     * The player who owns the building will collect the resources of the land.
     */
    public void playerCollectRessources(){
        this.player.addRessoure(this.collectRessources());
    }

}