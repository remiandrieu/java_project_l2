package game.building;

import game.board.*;
import game.player.*;

public abstract class Building {
    protected Player player;
    protected Land land;

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
        return "";
    }

}