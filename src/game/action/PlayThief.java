package game.action;

import java.util.ArrayList;
import java.util.Arrays;
import game.board.Board;
import game.board.util.Ressource;
import game.player.DemeterPlayer;
import game.player.Player;
import listchooser.InteractiveListChooser;
import listchooser.ListChooser;

/* a class to model the PlayThief action in Demeter Game */
public class PlayThief extends DemeterAction {

    /**
     * Create the PlayThief action
     * @param board the board
     */
    public PlayThief(Board board){
        super("use a thief", board);
    }

    /**
     * determines if a player can use a thief
     * @param player the player who wants to use a thief
     * @return true if it's possible, else false
     */
    public boolean isPossible(Player player){
		if (player instanceof DemeterPlayer){
			DemeterPlayer demeterPlayer = (DemeterPlayer) player;
            return demeterPlayer.hasThief();
		}
		return false;
    }

    /**
     * Executes the consequences of the PlayThief action
     * @param player the player
     */
    public void act(Player player, Player[] playersToSteal){
        System.out.println(player + " uses their thief");
        DemeterPlayer demeterPlayer = (DemeterPlayer) player;
        ListChooser<Ressource> lc = new InteractiveListChooser<>();
        Ressource chosenRessource = lc.choose("Which ressource do you want to steal from other players ?", new ArrayList<>(Arrays.asList(Ressource.values())));
        System.out.println("You've chosen : " + chosenRessource + "\n");
        steal(playersToSteal, demeterPlayer, chosenRessource);
    }

    public void steal(Player[] playersToSteal, DemeterPlayer demeterPlayer, Ressource chosenRessource) {
        int sum = 0;
        int tmp;
        for(Player p : playersToSteal){
            tmp = p.getRessources().get(chosenRessource);
            p.removeRessoure(chosenRessource, tmp);
            sum += tmp;
        }
        demeterPlayer.addRessoure(chosenRessource, sum);
        demeterPlayer.removeThief();
    }
}
