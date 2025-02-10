package game.action;

import game.player.Player;

public class Wait extends CommonAction{
    protected String label = "abstract CommonAction";

    public Wait(Player player){
        super(player, "wait");
    }

    public boolean isPossible() {
        return true;
    }

    public void act() {
    }

}
