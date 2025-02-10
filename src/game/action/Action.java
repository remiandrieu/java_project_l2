package game.action;
import java.util.*;
import game.board.util.*;
import game.player.Player;

public abstract class Action {
    
    protected Map<Ressource,Integer> cost = new HashMap<>();
    protected Player player;
    protected String label;

    public Action(Player player ,String label) {
        this.player = player;
        this.label = label;
    }

    public abstract boolean isPossible();

    public abstract void act();

    public String getLabel(){
        return this.label;
    }
}
