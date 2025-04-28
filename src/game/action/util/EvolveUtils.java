package game.action.util;

import java.util.ArrayList;
import java.util.Iterator;

import game.board.Board;
import game.board.Coordinates;
import game.board.InvalidPositionException;
import game.board.Land;
import game.building.Building;
import game.building.LandBuilding;
import game.player.Player;

public class EvolveUtils {

    /**
     * Returns an array of all the coordinates of the player's buildings that can evolve
     * @param player the player's buildings to check
     * @return an array of all the coordinates of the player's buildings that can evolve
     */
    public static ArrayList<Coordinates> buildingsThatCanEvolve(Player player){
        ArrayList<Coordinates> buildingsThatCanEvolveCoordinates = new ArrayList<>(); 
        for (Building building : player.getBuildings()){
                if (building instanceof LandBuilding && !((LandBuilding) building).isEvolved()){
                    buildingsThatCanEvolveCoordinates.add(building.getLand().getCoordinates());
                }
            }
        return buildingsThatCanEvolveCoordinates;
    }

    /**
     * Asks for an buiding and evolves it.
     * @param player the player who wants to evolve his building
    **/
    public static void act(Player player, Board board){
        ArrayList<Coordinates> availableArmies = EvolveUtils.buildingsThatCanEvolve(player);

        System.out.println(player + " wants to upgrade a building.");

        System.out.println("Available Building:");
        Iterator<Coordinates> it = availableArmies.iterator();
        while(it.hasNext()){
            Coordinates coor = it.next();
            System.out.println("X: " + coor.getX() + ", Y: " + coor.getY());
        }

        Coordinates coor = ActionUtils.askCoordinates(availableArmies);
        
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
