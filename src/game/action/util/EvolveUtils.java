package game.action.util;

import java.util.ArrayList;
import java.util.Iterator;

import game.board.Board;
import game.board.Coordinates;
import game.board.InvalidPositionException;
import game.board.Land;
import game.board.Tile;
import game.building.DemeterBuilding;
import game.building.LandBuilding;
import game.player.Player;
import listchooser.util.Input;

public class EvolveUtils {
    
    /**
     * Asks the player for coordinates, doesn't stop until the coordinates are in the list.
     * @param list the list the coordinates must belong to
     * @return the coordinates given by the player
     */
    public static Coordinates askCoordinates(ArrayList<Coordinates> list) {
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
    public static ArrayList<Coordinates> buildingsThatCanEvolve(Player player, Board board){
        ArrayList<Coordinates> buildingsThatCanEvolveCoordinates = new ArrayList<>(); 

        for (int x = 0; x < board.getLength(); x++) {
            for (int y = 0; y < board.getWidth(); y++) {
                try {
                    Tile tile = board.getTile(x, y);
                    if (tile instanceof Land && ((Land) tile).hasBuilding() && ((Land) tile).getBuilding() instanceof DemeterBuilding) {
                        Land land = (Land) tile;
                        
                        LandBuilding building = (LandBuilding) land.getBuilding();

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

    /**
     * Asks for an buiding and evolves it.
     * @param player the player who wants to evolve his building
    **/
    public static void act(Player player, Board board){
        ArrayList<Coordinates> availableArmies = EvolveUtils.buildingsThatCanEvolve(player,board);

        System.out.println(player + " wants to upgrade a building.");

        System.out.println("Available Building:");
        Iterator<Coordinates> it = availableArmies.iterator();
        while(it.hasNext()){
            Coordinates coor = it.next();
            System.out.println("X: " + coor.getX() + ", Y: " + coor.getY());
        }

        Coordinates coor = EvolveUtils.askCoordinates(availableArmies);
        
        try {
            Land land = (Land) board.getTile(coor.getX(), coor.getY());
            LandBuilding building = (LandBuilding) land.getBuilding();
            String oldBuildingString = building.toString();
            building.evolve();
            System.out.println(oldBuildingString + " was upgraded in " + building + "\n");
        } catch (InvalidPositionException e) {
        }
    }
}
