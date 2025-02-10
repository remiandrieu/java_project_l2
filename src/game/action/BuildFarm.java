package game.action;

import game.board.util.Ressource;
import game.player.Player;

public class BuildFarm extends DemeterAction {

    public BuildFarm(){
        super("build a farm");
        this.cost.put(Ressource.WOOD, 1);
        this.cost.put(Ressource.ORE, 1);
    }

    public boolean isPossible(Player player){
        return true;
    }

    public void act(Player player){
    }

}
