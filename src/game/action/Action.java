package game.action;
import java.util.*;

import game.board.util.*;

public abstract class Action {
    
    protected Map<Ressource,Integer> cost = new HashMap<>();
    protected String label;

    public Action(String label) {
        this.label = label;
    }

    public abstract boolean isPossible();

    public abstract void act();

    public String getLabel(){
        return this.label;
    }
}
