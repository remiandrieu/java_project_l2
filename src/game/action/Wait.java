package game.action;

import game.player.Player;

public class Wait extends CommonAction{

    public Wait(){
        super("wait");
    }

    public boolean isPossible(Player player) {
        return true;
    }

    public void act(Player player) {
    }

}
