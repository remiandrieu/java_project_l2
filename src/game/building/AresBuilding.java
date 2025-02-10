package game.building;

import game.board.*;
import game.player.*;

public class AresBuilding extends LandBuilding{
    protected boolean level; //Level of the building, false if army, true if camp
    // As the dimension is equal to the number of warriors,
    // it seems intuitive to omit a variable numberOfWarriors and to use the dimension instead.

    public AresBuilding(Player player, Land land, int numberWarriors){
        super(player, land, numberWarriors);
        this.level = false;
    }

    /**
     * Returns false if the building is an army, 
     * true if the building is a camp.
     * @return the state of the building.
     */
    public boolean isEvolved(){
        return level;
    }

    /**
     * Evolves the building into an army. If the building is already an army does nothing.
     */
    public void evolve(){
        if (!this.level){
            this.level = true;
        }
    }

    /**
     * Adds warriors to the building.
     * @param warriors the number of warriors to add
     */
    public void addWarriors(int warriors){
        this.dimension += warriors;
    }

    /**
     * The player who owns the building will collect the resources of the land.
     * If the building is evolved, does it a second time.
     */
    public void playerCollectRessources() {
        this.player.addResource(this.collectRessources());

        if (this.isEvolved()){
            this.player.addResource(this.collectRessources()); 
        }
    }

}