package game.action;

import java.util.ArrayList;
import java.util.Iterator;

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
        
        ArrayList<Coordinates> availableArmies = buildingsThatCanEvolve(player);
        
        return res && !availableArmies.isEmpty();
    }

    /**
     * Asks for an army and evolves it.
     * @param player the player who wants to evolve his army
    **/
    public void act(Player player){
        // On récupère tout les bâtiments non évolués dans un dictionnaire avec leurs coordonnées comme valeurs.
        ArrayList<Coordinates> availableArmies = buildingsThatCanEvolve(player);

        System.out.println(player + " wants to upgrade an army.");

	    //On affiche les bâtiments.
        System.out.println("Available Armies:");
        Iterator<Coordinates> it = availableArmies.iterator();
        while(it.hasNext()){
            Coordinates coor = it.next();
            System.out.println("X: " + coor.getX() + ", Y: " + coor.getY());
        }

        Coordinates coor = askCoordinates(availableArmies);
        
        super.act(player);
        try {
            Land land = (Land) this.board.getTile(coor.getX(), coor.getY());
            AresBuilding building = (AresBuilding) land.getBuilding();
            String oldBuildingString = building.toString();
            building.evolve();
            System.out.println(oldBuildingString + " was upgraded in " + building + "\n");
        } catch (InvalidPositionException e) {
        }
    }

    /**
     * Asks the player for coordinates, doesn't stop until the coordinates are in the list.
     * @param list the list the coordinates must belong to
     * @return the coordinates given by the player
     */
    public Coordinates askCoordinates(ArrayList<Coordinates> list){  
        int x = -1;
        int y = -1;
		boolean correct = false;
        Coordinates coor = new Coordinates(x, y);
        
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

            coor = new Coordinates(x, y);

            if (! list.contains(coor)){
                System.out.println("Incorrect Position");
                correct = false;
            }
        }
        return coor;
    }

    /**
     * Returns an array of all the coordinates of the player's buildings that can evolve
     * @param player the player's buildings to check
     * @return an array of all the coordinates of the player's buildings that can evolve
     */
    public ArrayList<Coordinates> buildingsThatCanEvolve(Player player){
        ArrayList<Coordinates> buildingsThatCanEvolveCoordinates = new ArrayList<>(); 

        for (int x = 0; x < this.board.getLength(); x++) {
            for (int y = 0; y < this.board.getWidth(); y++) {
                try {
                    Tile tile = board.getTile(x, y);
                    if (tile instanceof Land && ((Land) tile).hasBuilding() && ((Land) tile).getBuilding() instanceof AresBuilding) {
                        Land land = (Land) tile;
                        AresBuilding building = (AresBuilding) land.getBuilding();

                        if(building.getPlayer() == player && !building.isEvolved()){
                           buildingsThatCanEvolveCoordinates.add(new Coordinates(x, y));
                        } 
                    }
                } catch (InvalidPositionException e) {
                }
            }
        }
        return buildingsThatCanEvolveCoordinates;
    }
}
