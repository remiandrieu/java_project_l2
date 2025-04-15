package game.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import game.board.*;
import game.player.*;
import listchooser.util.Input;
import game.building.*;

/* A class to model the Attack action of Ares Game */
public class Attack extends AresAction {
    
    /**
     * Create the buildArmy action
     * @param board the board
     */
    public Attack(Board board){
        super("Attack!", board);
    }

    /** One of the neighbor is a building from another player.*/
    public boolean isPossible(Player player){
        if(!(player instanceof AresPlayer)){
            return false;
        }
        for (int x = 0; x < this.board.getLength(); x++) {
            for (int y = 0; y < this.board.getWidth(); y++) {
                try {
                    Tile tile = board.getTile(x, y);
                    if (tile instanceof Land && ((Land) tile).hasBuilding() && ((Land) tile).getBuilding() instanceof AresBuilding){
                        Land land = (Land) tile;
                        Building building = land.getBuilding();

                        if(building.getPlayer() == player){
                            Tile[] neighbors = this.board.getNeighbourTiles(x, y);
                            
                            for (Tile neighbor : neighbors) {
                                if (neighbor instanceof Land && ((Land) neighbor).hasBuilding() && ((Land) tile).getBuilding() instanceof AresBuilding && player != ((Land) neighbor).getBuilding().getPlayer()){
                                    return super.isPossible(player);
                                } 
                            }
                        } 
                    }
                } catch (InvalidPositionException e) {
                }
            }
        }
        return false;
    }

    /** 
     * Attacks one of the neighbor, the players throw dices according the number of warriors in their buildings.
     * The one with the most points wins
     * You can use a secret weapon to buy another dice.
     * @param player the player who executes this action
    */
    public void act(Player player){
        if(!(player instanceof AresPlayer)){
            return;
        }
        AresPlayer attackingPlayer = (AresPlayer) player;


        ArrayList<Coordinates> buildingsThatCanAttackCoordinates = buildingsThatCanAttackCoordinates(attackingPlayer);
        //Potentiel problème d'inversion de X et Y.
        
        // Affichage des buildings qui peuvent attaquer
        System.out.println("Armies that can attack:");

        affichageBuildingCoordinates(buildingsThatCanAttackCoordinates);
        
        //Input pour le batiment qui va attaquer

        System.out.println("Choose an army that will attack: ");

        Coordinates coorAttackingBuilding = askCoordinates(buildingsThatCanAttackCoordinates);

        System.out.println();

        //Récupération des coordonnées voisines avec un batiment ennemi et affichage
        ArrayList<Coordinates> neighboringBuildingsCoordinates = new ArrayList<>();
        
        Coordinates[] neighbors = null;

        try{ 
            neighbors = this.board.getNeighbourCoordinates(coorAttackingBuilding);
        }catch(Exception e){     
        }

        for (Coordinates coorNeighbor : neighbors) {
            try{ 
                if (this.board.isCorrectLocation(coorNeighbor.getX(), coorNeighbor.getY())){ 
                    Tile neighbor = this.board.getTile(coorNeighbor.getX(), coorNeighbor.getY());
                    if (neighbor instanceof Land && ((Land) neighbor).hasBuilding() && attackingPlayer != ((Land) neighbor).getBuilding().getPlayer()  && ((Land) neighbor).getBuilding() instanceof AresBuilding){
                        neighboringBuildingsCoordinates.add(coorNeighbor);
                    }
                }  
            }catch(Exception e){
            }  
        } 
       

        System.out.println("Armies that you can attack:");

        affichageBuildingCoordinates(neighboringBuildingsCoordinates);

        System.out.println("Choose an army to attack: ");

        Coordinates coorBuildingToAttack = askCoordinates(neighboringBuildingsCoordinates);
        AresPlayer otherPlayer = null;

        try {
            otherPlayer = (AresPlayer) (((Land) (this.board.getTile(coorBuildingToAttack.getX(), coorBuildingToAttack.getY()))).getBuilding().getPlayer());
        } catch (InvalidPositionException e) {
        }

        System.out.println();

        boolean attackingPlayerSecretWeapon = checkAndAskForSecretWeapon(attackingPlayer);
        boolean otherPlayerSecretWeapon = checkAndAskForSecretWeapon(otherPlayer);
        
        System.out.println("Time to battle!");
        
        int attackingPlayerScore = 0;
        int otherPlayerScore = 0;
        while(attackingPlayerScore == otherPlayerScore){
            System.out.println(attackingPlayer.getName() + "'s turn:");
            attackingPlayerScore = scoreThrowingDice(coorAttackingBuilding, attackingPlayerSecretWeapon);

            System.out.println(otherPlayer.getName() + "'s turn:");
            otherPlayerScore = scoreThrowingDice(coorBuildingToAttack, otherPlayerSecretWeapon);

            if(attackingPlayerScore == otherPlayerScore){
                System.out.println("Egalité! On recommence.");
            }
        }
        
        System.out.println();

        if(attackingPlayerScore > otherPlayerScore){
            System.out.println(attackingPlayer.getName() + " won the battle!");
            System.out.println(otherPlayer.getName() + " loses one warrior in his building.");
            removesWarriorsBuilding(otherPlayer, coorBuildingToAttack);
        } else {
            System.out.println(otherPlayer.getName() + " won the battle!");
            System.out.println(attackingPlayer.getName() + " loses one warrior in his building.");
            removesWarriorsBuilding(attackingPlayer, coorAttackingBuilding);
        } 
        
    }

    /**
     * Removes one warrior from the losing player's building, if the building has no warriors, removes it.
     * @param player the losing player
     * @param coor the coordinates of the building where the warrior is removed
     */
    public void removesWarriorsBuilding(AresPlayer player, Coordinates coor){
        AresBuilding building = null;
        Land land = null;

        try {
            building = (AresBuilding) ((Land) (this.board.getTile(coor.getX(), coor.getY()))).getBuilding();
            land = (Land) (this.board.getTile(coor.getX(), coor.getY()));
        } catch (InvalidPositionException e) {
        }

        building.removeWarrior();

        if (building.getDimension() == 0){
            System.out.println("No more warriors, the building is destroyed!");
            player.getBuildings().remove(building);
            land.changeBuilding(null);
        } 
    } 
    
    /**
     * Simulates the throwing of dice for the given building location.
     * @param coor The coordinates of the building engaged in battle
     * @param secretWeapon if true, throws one more die.
     * @return the score the building got, i.e. the sum of all the dice.
     */
    public int scoreThrowingDice(Coordinates coor, boolean secretWeapon){
        int res = 0;
        int nbWarriors = 0;
        int nbThrows = 0;

        try {
            AresBuilding building = (AresBuilding) ((Land) (this.board.getTile(coor.getX(), coor.getY()))).getBuilding();
            nbWarriors = building.getDimension();
        } catch (InvalidPositionException e) {
        }

        if(nbWarriors <= 3){
            nbThrows = 1;
        } else if (nbWarriors <=7){
            nbThrows = 2;
        } else {
            nbThrows = 3;
        }

        if (secretWeapon) {
            nbThrows += 1;
        }

        System.out.println();
        System.out.println("You have "+nbThrows+ " roll(s).");
        Random randomNumbers = new Random();
        for(int i = 0; i < nbThrows; i++){
            int die = randomNumbers.nextInt(6) + 1;
            System.out.println("Die roll n°" + (i+1) + ": " + die);
            res += die;
        }
        System.out.println();
        System.out.println("Your score is " + res);
        System.out.println();
        
        return res;
    } 

    /**
     * Checks if the player has a secret weapon, if he does, asks him if he wants to use it. Returns the answer.
     * If the player doesn't have secret weapons returns false.
     * @param player the player to check
     * @return true if the player has a secret weapon and wants to use it
     */
    public boolean checkAndAskForSecretWeapon(AresPlayer player){
        String input = "";

        if(player.getNbSecretWeapons() <= 0){
            return false;
        } 
        
        while (true) {
            System.out.println(player.getName() + ", you have a secret weapon, do you want to use it? (y/n)");
        	input = Input.readString();
            if (input.equals("y")){
                player.removeSecretWeapon();
                return true;
            } else if (input.equals("n")){
                return false;
            } else {
                System.out.println("Incorrect Input");
            } 
        }
    } 

    /**
     * Prints the list of coordinates of buildings as well as the number of warriors in that building.
     * @param list the list must be a list of coordinates of tiles with a building on them.
     */
    public void affichageBuildingCoordinates(ArrayList<Coordinates> list){
        Iterator<Coordinates> it = list.iterator();
        while(it.hasNext()){
            Coordinates coor = it.next();
            try {
                AresBuilding building = (AresBuilding) ((Land) (this.board.getTile(coor.getX(), coor.getY()))).getBuilding();
                System.out.println("X: " + coor.getX() + ", Y: " + coor.getY() + " , Number of Warriors: " + building.getDimension());
            } catch (InvalidPositionException e) {
            }
        }

        System.out.println();
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
     * Returns an array of all the coordinates of the player's buildings that can attack
     * i.e. buildings that have a neighboring building from another player
     * @param player the player's buildings to check
     * @return an array of all the coordinates of the player's buildings that can attack
     */
    public ArrayList<Coordinates> buildingsThatCanAttackCoordinates(Player player){
        ArrayList<Coordinates> buildingsThatCanAttackCoordinates = new ArrayList<>(); 

        for (int x = 0; x < this.board.getLength(); x++) {
            for (int y = 0; y < this.board.getWidth(); y++) {
                try {
                    Tile tile = board.getTile(x, y);
                    if (tile instanceof Land && ((Land) tile).hasBuilding() && ((Land) tile).getBuilding() instanceof AresBuilding) {
                        Land land = (Land) tile;
                        Building building = land.getBuilding();

                        if(building.getPlayer() == player){
                            Tile[] neighbors = this.board.getNeighbourTiles(x, y);
                            
                            for (Tile neighbor : neighbors) {
                                if (neighbor instanceof Land && ((Land) neighbor).hasBuilding() && player != ((Land) neighbor).getBuilding().getPlayer() && ((Land) neighbor).getBuilding() instanceof AresBuilding){
                                    buildingsThatCanAttackCoordinates.add(new Coordinates(x, y));
                                    break;
                                } 
                            }
                        } 
                    }
                } catch (InvalidPositionException e) {
                }
            }
        }
        return buildingsThatCanAttackCoordinates;
    }
}
