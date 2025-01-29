package game.building;

import game.board.*;

public abstract class Building {
    protected Player player;
    protected Land land;

    public Building(Player player, Land land) {
        this.player = player;
        this.land = land;
    }
}