package game.action;

import game.board.Board;
import game.board.util.Ressource;
import game.player.AresPlayer;
import game.player.Player;

/* a class to model the BuyThief action in Demeter Game */
public class BuySecretWeapon extends AresAction {

    /**
     * Create the BuyThief action
     * @param board the board
     */
    public BuySecretWeapon(Board board){
        super("buy a secret weapon", board);
        this.cost.put(Ressource.WOOD, 1);
        this.cost.put(Ressource.ORE, 1);
    }

    /**
     * determines if it's possible to buy a thief
     * @param player the player who wants to buy a thief
     * @return true if it's possible, else false
     */
    public boolean isPossible(Player player){
        if (player instanceof AresPlayer){
            AresPlayer AresPlayer = (AresPlayer) player;
            return super.isPossible(AresPlayer);
        }
        return false;
    }

    /**
     * Executes the consequences of the BuyThief action
     * @param player the player
     */
    public void act(Player player){
        AresPlayer demeterPlayer = (AresPlayer) player;
        super.act(demeterPlayer);
        demeterPlayer.addSecretWeapon();
        System.out.println(player + " buys a secret weapon\n");
    }

}
