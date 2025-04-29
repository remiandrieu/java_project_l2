package game.action;

import game.board.Board;
import game.board.util.Ressource;
import game.player.AresPlayer;
import game.player.Player;

public class BuyWarriors extends AresAction {
    /**
     * Create the buildArmy action
     * @param board the board
     */
    public BuyWarriors(Board board){
        super("Buy 5 warriors", board);
        this.cost.put(Ressource.ORE, 1);
        this.cost.put(Ressource.SHEEP, 2);
        this.cost.put(Ressource.WHEAT, 2);
    }

    /**
     * Action is possible when you have enough ressources.
     */


    /**
     * Adds 5 warriors to the player
     * @param player the player
     */
    public void act(Player player){
        super.act(player);
        ((AresPlayer) player).addWarrior(5);
        System.out.println(player + " buys 5 warriors\n");
    }
}
