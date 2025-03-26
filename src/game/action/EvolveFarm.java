package game.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
        
        ArrayList<Coordinates> availableFarms = buildingsThatCanEvolve(player);
        
        return res && !availableFarms.isEmpty();
    }

    /**
     * Evolves the farm into a big farm.
     * @param player the player that executes the action
     */
    public void act(Player player){
        ArrayList<Coordinates> availableFarms = buildingsThatCanEvolve(player);
        System.out.println(player + " wants to upgrade a farm.");

        //On affiche les bâtiments.
        System.out.println("Available Farms:");
        Iterator<Coordinates> it = availableFarms.iterator();
        while(it.hasNext()){
            Coordinates coor = it.next();
            System.out.println("X: " + coor.getX() + ", Y: " + coor.getY());
        }

        Coordinates coor = askCoordinates(availableFarms);
        
        super.act(player);
        try {
            Land land = (Land) this.board.getTile(coor.getX(), coor.getY());
            DemeterBuilding building = (DemeterBuilding) land.getBuilding();
            building.evolve();
        } catch (InvalidPositionException e) {
        }
        System.out.println(player + " upgraded the farm at (" + coor.getX() + ", " + coor.getY() + ")\n");
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
                    if (tile instanceof Land && ((Land) tile).hasBuilding() && ((Land) tile).getBuilding() instanceof DemeterBuilding) {
                        Land land = (Land) tile;
                        DemeterBuilding building = (DemeterBuilding) land.getBuilding();

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