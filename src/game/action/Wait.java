package game.action;

import game.board.Board;
import game.player.Player;

public class Wait extends CommonAction{

    public Wait(Board board){
        super("wait", board);
    }

    public boolean isPossible(Player player) {
        return true;
    }

    public void act(Player player) {
        System.out.println(player + " waits\n");
    }

}
