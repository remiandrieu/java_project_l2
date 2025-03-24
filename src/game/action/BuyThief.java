package game.action;

import game.board.Board;
import game.board.util.Ressource;
import game.player.DemeterPlayer;
import game.player.Player;

/* a class to model the BuyThief action in Demeter Game */
public class BuyThief extends DemeterAction {

    /**
     * Create the BuyThief action
     * @param board the board
     */
    public BuyThief(Board board){
        super("buy a thief", board);
        this.cost.put(Ressource.WOOD, 1);
        this.cost.put(Ressource.ORE, 1);
		this.cost.put(Ressource.WHEAT, 1);
    }

    /**
     * determines if it's possible to buy a thief
     * @param player the player who wants to buy a thief
     * @return true if it's possible, else false
     */
    public boolean isPossible(Player player){
        if (player instanceof DemeterPlayer){
            DemeterPlayer demeterPlayer = (DemeterPlayer) player;
            return super.isPossible(demeterPlayer) && !(demeterPlayer.hasThief());
        }
        return false;
    }

    /**
     * Executes the consequences of the BuyThief action
     * @param player the player
     */
    public void act(Player player){
        DemeterPlayer demeterPlayer = (DemeterPlayer) player;
        super.act(demeterPlayer);
        demeterPlayer.addThief();
        System.out.println(player + " buys a thief\n");
    }

}
