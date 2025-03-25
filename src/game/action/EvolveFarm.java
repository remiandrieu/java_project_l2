package game.action;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import game.board.*;
import game.board.util.Ressource;
import game.building.*;
import game.player.Player;
import listchooser.util.Input;

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
        
        List<Building> availableBuildings = player.getBuildings();
        boolean allBuildingsEvolved = true;

        for(Building building: availableBuildings) {
            if (building instanceof DemeterBuilding && !((DemeterBuilding) building).isEvolved()){
                allBuildingsEvolved = false;
            }
        }
        return res && !allBuildingsEvolved;
    }

    /**
     * Evolves the farm into a big farm.
     * @param player the player that executes the action
     */
    public void act(Player player){
        int x = -1;
        int y = -1;
        // On récupère tout les bâtiments non évolués dans un dictionnaire avec leurs coordonnées comme valeurs.
        HashMap<DemeterBuilding, int[]> availableArmies = new HashMap<>();
        try{
            for(int i=0; i < this.board.getLength(); i++){
                for(int j = 0; j < this.board.getWidth(); j++){
                    Tile tile = this.board.getTile(i, j);
                    if (!(tile instanceof Sea) && ((Land) tile).hasBuilding() && ((Land) tile).getBuilding() instanceof DemeterBuilding){
                        DemeterBuilding building = (DemeterBuilding) ((Land) tile).getBuilding();
                        if (! building.isEvolved() && player.getBuildings().contains(building)){
                            int[] coord = {i, j};
                            availableArmies.put(building, coord);
                        }
                    }
                }
            }
        }
        catch(Exception e){
        }
        System.out.println(player + " wants to upgrade a farm.");
        //On affiche les bâtiments.
        System.out.println("Available Farms:");
        Set<DemeterBuilding> keys = availableArmies.keySet();
        Iterator<DemeterBuilding> it = keys.iterator();
        while(it.hasNext()){
            DemeterBuilding demeterBuilding = it.next();
            System.out.println("X: " + availableArmies.get(demeterBuilding)[0] + ", Y: " + availableArmies.get(demeterBuilding)[1]);
        }

	    boolean correct = false;
        boolean res = false;
        while (!correct) {
            correct = true;
            System.out.println("enter the x coordinate: ");
        	try {
        		x = Input.readInt();
            } catch (java.io.IOException e) {
            	System.out.println("Please, enter a number");
                correct = false;
                continue;
        	}
            System.out.println("enter the y coordinate: ");
            try {
        		y = Input.readInt();
        	} catch (java.io.IOException e) {
            	System.out.println("Please, enter a number");
                correct = false;
                continue;
        	}
            int[] coord = {x, y};

            res = false;
            it = keys.iterator();
            while(it.hasNext() && !res){
                DemeterBuilding demeterBuilding = it.next();
                res = (coord[0] == availableArmies.get(demeterBuilding)[0]) && (coord[1] == availableArmies.get(demeterBuilding)[1]);
            }

            if (!res){
                System.out.println("This tile doesn't have an army.");
                correct = false;
           }
        }
        
        super.act(player);
        try {
            Land land = (Land) this.board.getTile(x, y);
            DemeterBuilding building = (DemeterBuilding) land.getBuilding();
            building.evolve();
        } catch (InvalidPositionException e) {
        }
        System.out.println(player + " upgraded the farm at (" + x + ", " + y + ")\n");
    }

}