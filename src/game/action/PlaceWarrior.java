package game.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import game.action.util.ActionUtils;
import game.board.*;
import game.building.*;
import game.player.*;
import listchooser.util.Input;

/** a class to model PlaceWarrior action */
public class PlaceWarrior extends AresAction {

    /**
     * creates an PlaceWarrior action
     * @param board
     */
    public PlaceWarrior(Board board){
        super("Place warriors in army or camp", board);
    }

    /**
     * Is possible if the player has enough ressources to execute the action, and owns at least one army or one camp.
     * @param player the player who wants to place warriors
     * @return true if it's possible, else false
     */
    public boolean isPossible(Player player){
        boolean res = super.isPossible(player);
        
        List<Building> availableBuildings = player.getBuildings();
        boolean atLeastOneAresBuilding = false;

        for(Building building: availableBuildings) {
            if (building instanceof AresBuilding){
                atLeastOneAresBuilding = true;
                break;
            }
        }
        return res && atLeastOneAresBuilding && ((AresPlayer) player).getNbWarrior() > 0;
    }

    /**
     * Asks for an army or a camp and add warriors in it.
     * @param player the player who wants add warriors in an army or a camp
    **/
    public void act(Player player){
        int numberOfWarriors = -1;
        ArrayList<Coordinates> availableBuilding = new ArrayList<>();
        try{
            System.out.println(player + " adds some warriors to one of his camps.");
            for (Building building : player.getBuildings()){
                if (building instanceof AresBuilding){
                    availableBuilding.add(building.getLand().getCoordinates());
                }
            }
        }
        catch(Exception e){
        }

        System.out.println("Available Building:");
        Iterator<Coordinates> it = availableBuilding.iterator();
        while(it.hasNext()){
            Coordinates coor = it.next();
            System.out.println("X: " + coor.getX() + ", Y: " + coor.getY());
        }
        Coordinates coor = ActionUtils.askCoordinates(availableBuilding);
        boolean correct = false;
        while (!correct) {
            correct = true;
            System.out.println("How many Warriors? : ");
            try {
                numberOfWarriors = Input.readInt();
                if (numberOfWarriors<1){
                    System.out.println("The minimum number is 1");
                    correct = false;
                }
                else if(numberOfWarriors>((AresPlayer) player).getNbWarrior()){
                    System.out.println("The number is to big");
                    correct = false;
                }
            } catch (java.io.IOException e) {
                System.out.println("Please, enter a number");
                correct = false;
            }
        }
        try {
            Land land = (Land) this.board.getTile(coor.getX(), coor.getY());
            AresBuilding building = (AresBuilding) land.getBuilding();
            building.addWarriors(numberOfWarriors);
            ((AresPlayer) player).removeWarrior(numberOfWarriors);
            System.out.println(player + " placed " + numberOfWarriors + " warriors on " + building + "\n");
        } catch (InvalidPositionException e) {
        }
        
    }
    
}
