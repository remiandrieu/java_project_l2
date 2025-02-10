package game.action;

import game.player.Player;

public abstract class CommonAction extends Action{

    public CommonAction(Player player, String label) {
        super(player, label);
    }
}
