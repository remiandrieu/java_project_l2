package game.player;

import java.util.ArrayList;

import game.board.Board;
import game.board.Coordinates;
import game.board.InvalidPositionException;
import game.board.Land;
import game.building.Building;
import game.building.Port;

public class AresPlayer extends Player {

    protected int nbSecretWeapons;
    protected int nbWarrior;

    /**
     * Create a Ares player with all attributes of Player and a nbWarrior
     * @param name the name of the player
     */
    public AresPlayer(String name){
        super(name);
        this.nbWarrior = 0;
        this.nbSecretWeapons = 0;
    }

    public int getNbSecretWeapons(){
        return this.nbSecretWeapons;
    }

    public void addSecretWeapon(){
        this.nbSecretWeapons += 1;
    }

    public void removeSecretWeapon(){
        this.nbSecretWeapons -= 1;
    } 

    public int getNbWarrior() {
        return this.nbWarrior;
    }

    public void addWarrior(int n){
        this.nbWarrior += n; 
    }

    public void removeWarrior(int n){
        this.nbWarrior -= n; 
    }

    /**
     * Return true if all following conditions are met :
     * - the coordinates corresponds to an island that the player isn't occupying
     * - the player have at least one port on an island he is occupying
     * - on all islands the player is occupying, they have at least 2 buildings
     * @param board the board
     * @return the conditions
     * 
     */
    public boolean islandsConditions(Board board, Coordinates co) throws InvalidPositionException {
        ArrayList<ArrayList<Coordinates>> islands = board.detectIslands();

        int islandIndex = -1;
        for (int i = 0; i < islands.size(); i++) {
            if (islands.get(i).contains(co)) {
                islandIndex = i;
                break;
            }
        }
        
        if (islandIndex == -1) {
            throw new InvalidPositionException("coordinates doesn't correspond to any island");
        }
        
        boolean occupiesTargetIsland = false;
        for (Coordinates c : islands.get(islandIndex)) {
            Land land = (Land) board.getTile(c.getX(), c.getY());
            if (land.hasBuilding() && land.getBuilding().getPlayer().getId() == this.id) {
                occupiesTargetIsland = true;
                break;
            }
        }
        
        if (occupiesTargetIsland) {
            return true;
        }
        
        int totalPlayerBuildings = 0;
        boolean hasPort = false;
        boolean allOccupiedIslandsHaveTwoBuildings = true;
        
        for (int i = 0; i < islands.size(); i++) {
            int islandPlayerBuildings = 0;
            boolean islandHasPlayerBuilding = false;
            
            for (Coordinates c : islands.get(i)) {
                Land land = (Land) board.getTile(c.getX(), c.getY());
                if (land.hasBuilding()) {
                    Building building = land.getBuilding();
                    if (building.getPlayer().getId() == this.id) {
                        islandPlayerBuildings++;
                        islandHasPlayerBuilding = true;
                        
                        if (building instanceof Port) {
                            hasPort = true;
                        }
                    }
                }
            }
            
            totalPlayerBuildings += islandPlayerBuildings;
            
            if (islandHasPlayerBuilding && islandPlayerBuildings < 2) {
                allOccupiedIslandsHaveTwoBuildings = false;
                break;
            }
        }

        if (totalPlayerBuildings == 0) {
            return true;
        }
        
        return allOccupiedIslandsHaveTwoBuildings && hasPort;
    }
    
    public String toString() {
        return super.toString() + "(" + this.nbWarrior + " warriors)";
    }
    
}
