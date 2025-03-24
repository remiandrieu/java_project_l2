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

/** a class to model EvolveArmy action */
public class EvolveArmy extends AresAction {

    /**
     * creates an EvolveArmy action
     * @param board
     */
    public EvolveArmy(Board board){
        super("Evolve army into camp", board);
        this.cost.put(Ressource.WOOD, 2);
        this.cost.put(Ressource.ORE, 3);
    }

    /**
     * Is possible if the player has enough ressources to execute the action, and owns at least one non-evolved building.
     * @param player the player who wants to evolve his army
     * @return true if it's possible, else false
     */
    public boolean isPossible(Player player){
        boolean res = super.isPossible(player);
        
        List<Building> availableBuildings = player.getBuildings();
        boolean allBuildingsEvolved = true;

        for(Building building: availableBuildings) {
            if (building instanceof AresBuilding && !((AresBuilding) building).isEvolved()){
                allBuildingsEvolved = false;
            }
        }
        return res && !allBuildingsEvolved;
    }

    /**
     * Asks for an army and evolves it.
     * @param player the player who wants to evolve his army
    **/
    public void act(Player player){
        int x = -1;
        int y = -1;
        // On récupère tout les bâtiments non évolués dans un dictionnaire avec leurs coordonnées comme valeurs.
        HashMap<AresBuilding, int[]> availableArmies = new HashMap<>();
        try{
            System.out.println(player + " wants to upgrade an army.");
            for(int i=0; i < this.board.getLength(); i++){
                for(int j = 0; j < this.board.getWidth(); j++){
                    Tile tile = this.board.getTile(i, j);
                    if (!(tile instanceof Sea) && ((Land) tile).hasBuilding() && ((Land) tile).getBuilding() instanceof AresBuilding){
                        AresBuilding building = (AresBuilding) ((Land) tile).getBuilding();
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
        //On affiche les bâtiments.
        System.out.println("Available Armies:");
        Set<AresBuilding> keys = availableArmies.keySet();
        Iterator<AresBuilding> it = keys.iterator();
        while(it.hasNext()){
            AresBuilding aresBuilding = it.next();
            System.out.println("X: " + availableArmies.get(aresBuilding)[0] + ", Y: " + availableArmies.get(aresBuilding)[1]);
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
                AresBuilding aresBuilding = it.next();
                res = (coord[0] == availableArmies.get(aresBuilding)[0]) && (coord[1] == availableArmies.get(aresBuilding)[1]);
            }

            if (!res){
                System.out.println("This tile doesn't have an army.");
                correct = false;
           }
        }
        
        super.act(player);
        try {
            Land land = (Land) this.board.getTile(x, y);
            AresBuilding building = (AresBuilding) land.getBuilding();
            String oldBuildingString = building.toString();
            building.evolve();
            System.out.println(oldBuildingString + " was upgraded in " + building + "\n");
        } catch (InvalidPositionException e) {
        }
    }
    
}
