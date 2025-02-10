package game.action;

import game.board.util.Ressource;

public class BuildFarm extends DemeterAction {

    public BuildFarm(String label){
        super(label);
        this.cost.put(Ressource.WOOD, 1);
        this.cost.put(Ressource.ORE, 1);
    }

    public boolean isPossible(){
        return true;
    }

    public void act(){
    }

}
