package game.action;

import game.board.util.Ressource;

public class Trade extends CommonAction{
    
    public Trade() {
        super("trade");
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
