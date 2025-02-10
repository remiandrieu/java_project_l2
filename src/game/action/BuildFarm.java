package game.action;

import game.board.util.Ressource;
import game.player.Player;

public class BuildFarm extends DemeterAction {

    public BuildFarm(Player player){
        super(player, "build a farm");
        this.cost.put(Ressource.WOOD, 1);
        this.cost.put(Ressource.ORE, 1);
    }

    public boolean isPossible(){
        return true;
    }

    public void act(){
    }

}
