package game.action;

import java.util.*;
import game.board.Board;
import game.board.util.*;
import game.player.Player;

/* a class to model an action */
public abstract class Action {
    
    /* Attributes */
    protected Map<Ressource,Integer> cost = new HashMap<>();
    protected String label;
    protected Board board;

    /**
     * Create an action with its label and a board
     * @param label the label of the action
     * @param board the board
     */
    public Action(String label, Board board) {
        this.label = label;
        this.board = board;
    }

    /**
     * Returns true if the player can execute the action
     * By default, it checks if the player needs to have enough ressources.
     * @param player the player
     * @return true if the player can execute the action
     */
    public boolean isPossible(Player player){
        boolean res = true;
        for(Ressource key: cost.keySet()){
            if (player.getRessources().get(key) < cost.get(key)){
                res = false;
            }
        }
        return res;
    }

    /**
     * Executes the consequences of the action
     * By default, it removes the resources from the player
     * @param player the player
     */
    public void act(Player player){
        for(Ressource key: cost.keySet()){
            player.removeRessoure(key, cost.get(key));
        }
    }

    /**
     * returns the label of this action
     * @return the label of this action
     */
    public String getLabel(){
        return this.label;
    }

    /**
     * A String representation of this action
     * @return a String representation of this action
     */
    public String toString(){
        return this.label;
    }
}
