package game.action;

import game.board.Board;
import game.building.Building;
import game.building.Port;
import game.player.Player;

/* a class to model trading action */
public class TradePort extends TradeAction{

    /**
     * creates a class for trading
     * @param board the board
     */
    public TradePort(Board board) {
        super("trade port", board);
    }

    protected int getNbRessource(){
        return 2;
    }

    /**
     * Returns true if the player can trade
     * @param player the player
     * @return true if the player can trade, else false
     */
    public boolean isPossible(Player player) {
        boolean hasPort = false;
        for (Building b : player.getBuildings()){
            if(b instanceof Port){
                hasPort = true;
                break;
            }
        }
        return hasPort && super.isPossible(player);
    }


}
