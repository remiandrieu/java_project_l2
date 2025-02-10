package game.action;

import game.board.util.Ressource;
import game.player.Player;

public class Trade extends CommonAction{
    
    public Trade(Player player) {
        super(player, "trade");
    }

    public boolean isPossible() {
        return true;
    }

    public void act() {
    }

    public Ressource[] availableRessources(){
        Ressource[] ressources = new Ressource[Ressource.values().length];
        return ressources;
    }
}
