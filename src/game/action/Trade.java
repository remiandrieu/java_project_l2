package game.action;

import game.board.util.Ressource;
import game.player.Player;

public class Trade extends CommonAction{
    
    public Trade(Player player) {
        super("trade");
    }

    public boolean isPossible(Player player) {
        return true;
    }

    public void act(Player player) {
    }

    public Ressource[] availableRessources(){
        Ressource[] ressources = new Ressource[Ressource.values().length];
        return ressources;
    }
}
