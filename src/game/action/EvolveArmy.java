package game.action;

import java.util.ArrayList;

import game.action.util.EvolveUtils;
import game.board.*;
import game.board.util.Ressource;
import game.player.Player;

/** a class to model EvolveArmy action */
public class EvolveArmy extends AresAction {

    /**
     * creates an EvolveArmy action
     * @param board
     */
    public EvolveArmy(Board board){
        super("Evolve army into camp", board);
        this.cost.put(Ressource.WOOD, 2);
        this.cost.put(Ressource.ORE, 3);
    }

    /**
     * Is possible if the player has enough ressources to execute the action, and owns at least one non-evolved building.
     * @param player the player who wants to evolve his army
     * @return true if it's possible, else false
     */
    public boolean isPossible(Player player){
        boolean res = super.isPossible(player);
        
        ArrayList<Coordinates> availableArmies = EvolveUtils.buildingsThatCanEvolve(player,this.board);
        
        return res && !availableArmies.isEmpty();
    }

    /**
     * Asks for an army and evolves it.
     * @param player the player who wants to evolve his army
    **/
    public void act(Player player){
        EvolveUtils.act(player,this.board);
        super.act(player);
    }

}
