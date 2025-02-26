package game.action;

import game.board.util.Ressource;
import game.player.Player;

public class ActionMain {
    
    public static void main(String[] args){
        Player player = new Player("Timoleon");
        Trade trade = new Trade();
        player.addRessoure(Ressource.ORE);
        player.addRessoure(Ressource.ORE);
        player.addRessoure(Ressource.ORE);
        player.addRessoure(Ressource.SHEEP);
        player.addRessoure(Ressource.SHEEP);
        player.addRessoure(Ressource.SHEEP);
        trade.act(player);
        System.out.println(player.getRessources().get(Ressource.ORE));
        System.out.println(player.getRessources().get(Ressource.WOOD));
    }
}
