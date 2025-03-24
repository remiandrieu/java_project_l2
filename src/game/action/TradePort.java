package game.action;

import java.util.ArrayList;
import java.util.List;

import game.board.Board;
import game.board.util.Ressource;
import game.building.Building;
import game.building.Port;
import game.player.Player;
import listchooser.InteractiveListChooser;
import listchooser.ListChooser;

/* a class to model trading action */
public class TradePort extends CommonAction{
    
    /**
     * creates a class for trading
     * @param board
     */
    public TradePort(Board board) {
        super("trade port", board);
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
        return hasPort && this.availableRessources(player).size() > 0;
    }

    /**
     * Executes the consequences of the trading
     * @param player the player
     */
    public void act(Player player) {
        System.out.println(player + " trades some ressources using their port.");
        ListChooser<Ressource> lc = new InteractiveListChooser<>();
        Ressource chosenRessource = lc.choose("Which ressource do you spend ?", this.availableRessources(player));
        System.out.println("You've chosen : " + chosenRessource);
		List<Ressource> l = this.spend(player, chosenRessource);
        Ressource chosenRessource2 = lc.choose("Which ressource do you want ?", l);
        System.out.println("You've chosen : " + chosenRessource2);
		this.buy(player, chosenRessource2);
        System.out.println(player + " has exchanged 2 " + chosenRessource + " for 1 " + chosenRessource2 + "\n");
    }

    public void buy(Player player, Ressource chosenRessource2) {
        player.addRessoure(chosenRessource2);
    }

    public List<Ressource> spend(Player player, Ressource chosenRessource) {
        player.removeRessoure(chosenRessource, 2);
        List<Ressource> l = new ArrayList<>();
		for (Ressource r : Ressource.values()){
            l.add(r);
        }
        return l;
    }

    /**
     * checks the available ressources of a player
     * @param player the player
     * @return a list of the available ressources for the given player
     */
    public List<Ressource> availableRessources(Player player){
        ArrayList<Ressource> ressources = new ArrayList<>();
        for (Ressource r: Ressource.values()){
            if (player.getRessources().containsKey(r) && player.getRessources().get(r)>=2){
                ressources.add(r);
            }
        }
        return ressources;
    }
}
