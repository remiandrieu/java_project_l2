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

    public Player getPlayer() {
        return this.player;
    }

    public Land getLand() {
        return this.land;
    }
}