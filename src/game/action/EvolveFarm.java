package game.action;

import java.util.ArrayList;
import java.util.Iterator;

import game.action.util.EvolveUtils;
import game.board.*;
import game.board.util.Ressource;
import game.building.*;
import game.player.Player;

/* a class to model an action that evolve a farm to a big farm in DemeterGame */
public class EvolveFarm extends DemeterAction {

    /**
     * Create the EvolveFarm action with its label
     * @param board the board
     */
    public EvolveFarm(Board board){
        super("evolve farm to big farm", board);
        this.cost.put(Ressource.WOOD, 2);
        this.cost.put(Ressource.WHEAT, 1);
        this.cost.put(Ressource.SHEEP, 1);
    }

    /**
     * returns true if the player has enough ressources to execute the action.
     * @param player the player that executes the action
     * @return true if the player has enough ressources to execute the action
     */
    public boolean isPossible(Player player){
        boolean res = super.isPossible(player);
        
        ArrayList<Coordinates> availableFarms = EvolveUtils.buildingsThatCanEvolve(player,this.board);
        
        return res && !availableFarms.isEmpty();
    }

    /**
     * Evolves the farm into a big farm.
     * @param player the player that executes the action
     */
    public void act(Player player){
        ArrayList<Coordinates> availableFarms = EvolveUtils.buildingsThatCanEvolve(player,this.board);
        System.out.println(player + " wants to upgrade a farm.");

        //On affiche les bâtiments.
        System.out.println("Available Farms:");
        Iterator<Coordinates> it = availableFarms.iterator();
        while(it.hasNext()){
            Coordinates coor = it.next();
            System.out.println("X: " + coor.getX() + ", Y: " + coor.getY());
        }

        Coordinates coor = EvolveUtils.askCoordinates(availableFarms);
        
        super.act(player);
        try {
            Land land = (Land) this.board.getTile(coor.getX(), coor.getY());
            DemeterBuilding building = (DemeterBuilding) land.getBuilding();
            building.evolve();
        } catch (InvalidPositionException e) {
        }
        System.out.println(player + " upgraded the farm at (" + coor.getX() + ", " + coor.getY() + ")\n");
    }
}