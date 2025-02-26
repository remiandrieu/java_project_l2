package game.action;

import game.board.util.Ressource;
import game.player.Player;

public class BuildArmy extends AresAction {
    public BuildArmy(){
        super("build an Army");
        this.cost.put(Ressource.WOOD, 1);
        this.cost.put(Ressource.SHEEP, 1);
        this.cost.put(Ressource.WHEAT, 1);
    }

    public boolean isPossible(Player player){
        boolean res = super.isPossible(player);
        return res;
    }

    public void act(Player player){
    }
}
