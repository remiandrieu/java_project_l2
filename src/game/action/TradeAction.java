package game.action;

import java.util.ArrayList;
import java.util.List;

import game.board.Board;
import game.board.util.Ressource;
import game.player.Player;
import listchooser.InteractiveListChooser;
import listchooser.ListChooser;

public abstract class TradeAction extends CommonAction{
    /**
     * creates a class for trading action
     * @param board the board
     */
    public TradeAction(String label, Board board) {
        super(label, board);
    }

    protected abstract int getNbRessource();

    /**
     * Returns true if the player can trade
     * @param player the player
     * @return true if the player can trade, else false
     */
    public boolean isPossible(Player player) {
        return this.availableRessources(player).size() > 0;
    }

    /**
     * Executes the consequences of the trading
     * @param player the player
     */
    public void act(Player player) {
        System.out.println(player + " trades some ressources.");
        ListChooser<Ressource> lc = new InteractiveListChooser<>();
        Ressource chosenRessource = lc.choose("Which ressource do you spend ?", this.availableRessources(player));
        System.out.println("You've chosen : " + chosenRessource);
		List<Ressource> l = this.spend(player, chosenRessource);
        Ressource chosenRessource2 = lc.choose("Which ressource do you want ?", l);
        System.out.println("You've chosen : " + chosenRessource2);
		this.buy(player, chosenRessource2);
        System.out.println(player + " has exchanged "+ this.getNbRessource() + " " + chosenRessource + " for 1 " + chosenRessource2 + "\n");
    }

    /**
     * Adds the given ressource to the player's inventory.
     * @param player the player who is buying the resource
     * @param chosenRessource the ressource to be added to the player's inventory
     */
    public void buy(Player player, Ressource chosenRessource) {
        player.addRessource(chosenRessource);
    }

    /**
     * Removes three units of the given ressource from the player's inventory,
     * and returns a list containing all possible ressources.
     * @param player the player who is spending the ressources
     * @param chosenRessource the ressource removed
     * @return a list of all available resources
     */
    public List<Ressource> spend(Player player, Ressource chosenRessource) {
        player.removeRessource(chosenRessource, getNbRessource());
        List<Ressource> l = new ArrayList<>();
		for (Ressource r : Ressource.values()){
            l.add(r);
        }
        return l;
    }

    /**
     * checks the available ressources of a player
     * @param player the player
     * @param nbRessource the number of ressource to trade
     * @return a list of the available ressources for the given player
     */
    public List<Ressource> availableRessources(Player player){
        ArrayList<Ressource> ressources = new ArrayList<>();
        for (Ressource r: Ressource.values()){
            if (player.getRessources().containsKey(r) && player.getRessources().get(r)>=this.getNbRessource()){
                ressources.add(r);
            }
        }
        return ressources;
    }
}
