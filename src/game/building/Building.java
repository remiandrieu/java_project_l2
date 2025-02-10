package game.building;

import game.board.*;
import game.player.*;

/* a class to represent the buildings */
public abstract class Building {

    /* attributes */
    protected Player player;
    protected Land land;

    /**
     * creates a building with its player and land
     * @param player the player who owns this building
     * @param land the land where the building is standing
     */
    public Building(Player player, Land land) {
        this.player = player;
        this.land = land;
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
    
    /**
     * Returns the string presentation for the board.
     * @return a string representation for 
     */
    public String toString() {
        return this.land.toString() + " " + this.player.toString();
    }

}