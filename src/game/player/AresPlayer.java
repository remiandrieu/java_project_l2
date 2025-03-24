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
        int islandIndex = 0;
        loop1 :
        for (ArrayList<Coordinates> island : islands){
            for(Coordinates c : island){
                if (c.equals(co)){
                    break loop1;
                }
            }
            islandIndex++;
        }
        if (islandIndex == islands.size()){
            throw new InvalidPositionException("coordinates doesn't correspond to any island");
        }

        boolean isOccupyingIsland = false;
        loop2:
        for (Coordinates c : islands.get(islandIndex)){
            Land land = (Land) board.getTile(c.getX(), c.getY());
            if (land.hasBuilding()){
                Building building = land.getBuilding();
                if (building.getPlayer().getId() == this.id){
                    isOccupyingIsland = true;
                    break loop2;
                }
            }
        }

        if (isOccupyingIsland)
            return true;
        
        int i;
        boolean isFirstBuilding = true;
        boolean otherIslandsConditions = true;
        boolean isOccupyingOtherIsland;
        boolean hasTwoBuilding;
        boolean hasPort = false;
        loop3 :
        for (i = 0; i < islands.size(); i++){
            if (i != islandIndex){
                isOccupyingOtherIsland = false;
                hasTwoBuilding = false;
                for(Coordinates c : islands.get(i)){
                    Land land = (Land) board.getTile(c.getX(), c.getY());
                    if (land.hasBuilding()){
                        Building building = land.getBuilding();
                        if (building.getPlayer().getId() == this.id){
                            isFirstBuilding = true;
                            if(isOccupyingOtherIsland)
                                hasTwoBuilding = true;
                            isOccupyingIsland = true;
                        }
                        if (building instanceof Port){
                            hasPort = true;
                        }
                    }
                }
                if (isOccupyingIsland && !hasTwoBuilding){
                    otherIslandsConditions = false;
                    break loop3;
                }
            }
        }
        if (isFirstBuilding) return true;
        return otherIslandsConditions && hasPort;
    }
    
    public String toString() {
        return super.toString() + "(" + this.nbWarrior + " warriors)";
    }
    
}
