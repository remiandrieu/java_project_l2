package game.building;

import game.board.*;
import game.board.util.Ressource;
import game.player.*;

public class AresBuilding extends LandBuilding{
    protected boolean level; //Level of the building, false if army, true if camp
    // As the dimension is equal to the number of warriors,
    // it seems intuitive to omit a variable numberOfWarriors and to use the dimension instead.

    public AresBuilding(Player player, Land land, int numberWarriors){
        super(player, land, numberWarriors);
        this.level = false;
        this.cost.put(Ressource.WOOD, 1);
        this.cost.put(Ressource.SHEEP, 1);
        this.cost.put(Ressource.WHEAT, 1);
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
     * Evolves the building into an army. If the building is already an army, it does nothing.
     */
    public void evolve(){
        this.level = true;
        this.cost.clear();
        this.cost.put(Ressource.WOOD, 2);
        this.cost.put(Ressource.ORE, 3);
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
        super.playerCollectRessources();

        if (this.isEvolved()){
            super.playerCollectRessources(); 
        }
    }

}