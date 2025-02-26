package game.action;
import java.util.*;
import game.board.util.*;
import game.player.Player;

public abstract class Action {
    
    protected Map<Ressource,Integer> cost = new HashMap<>();
    protected String label;

    public Action(String label) {
        this.label = label;
    }

    public boolean isPossible(Player player){
        boolean res = true;
        for(Ressource key: cost.keySet()){
            if (player.getRessources().get(key) < cost.get(key)){
                res = false;
            }
        }
        return res;
    }


    public abstract void act(Player player);

    public String getLabel(){
        return this.label;
    }
}
