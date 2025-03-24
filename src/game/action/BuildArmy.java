package game.action;

import game.board.Board;
import game.board.Coordinates;
import game.board.InvalidPositionException;
import game.board.Land;
import game.board.Sea;
import game.board.util.Ressource;
import game.building.AresBuilding;
import game.player.AresPlayer;
import game.player.Player;
import listchooser.util.Input;

/* a class to model the BuildArmy action in Ares Game */
public class BuildArmy extends AresAction {

    /**
     * Create the buildArmy action
     * @param board the board
     */
    public BuildArmy(Board board){
        super("Build an Army", board);
        this.cost.put(Ressource.WOOD, 1);
        this.cost.put(Ressource.SHEEP, 1);
        this.cost.put(Ressource.WHEAT, 1);
    }

    /**
     * Possible if player has at least 1 warriors in his stock
     */
    public boolean isPossible(Player player){
        if (!(player instanceof AresPlayer)){
            return false;
        };
        for (int x = 0; x < this.board.getLength(); x++){
            for (int y = 0; y < this.board.getWidth(); y++){
                try {
                    if(this.board.getTile(x, y) instanceof Land && !((Land) this.board.getTile(x, y)).hasBuilding() && ((AresPlayer) player).islandsConditions(this.board, new Coordinates(x, y))){
                        return (super.isPossible(player) && ((AresPlayer) player).getNbWarrior() > 0);
                    }
                } catch (InvalidPositionException e) {
                }
            }
        }
        return false;
    }

    /**
     * builds an army if possible
     * @param player the player who wants to build an army
     */
    public void act(Player player){
        int x = -1;
        int y = -1;
		boolean correct = false;
        
        while (!correct) {
            try {
                System.out.println(player + " wants to build an army.\nAt which coordinates ?");
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
                if (!this.board.isCorrectLocation(x, y)){
                    System.out.println("tile out of grid");
                    correct = false;
                }
                else if (this.board.getTile(x, y) instanceof Sea){
                    System.out.println("tile is not a land");
                    correct = false;
                }
                else if (((Land)this.board.getTile(x, y)).hasBuilding()){
                    System.out.println("tile already has a building");
                    correct = false;
                }
                else if (!((AresPlayer) player).islandsConditions(this.board, new Coordinates(x, y))){
                    System.out.println("Is not a valid position");
                    correct = false;
                }
            }
            catch (InvalidPositionException e) {
                correct = false;
            }
        } 
        int numberOfWarriors = -1;
        correct = false;
        while (!correct) {
            correct = true;
            System.out.println("How many Warriors? : ");
            try {
                numberOfWarriors = Input.readInt();
                if (numberOfWarriors<1){
                    System.out.println("The minimum number is 1");
                    correct = false;
                }
                else if(numberOfWarriors>5 || numberOfWarriors>((AresPlayer) player).getNbWarrior()){
                    System.out.println("The number is to big");
                    correct = false;
                }
            } catch (java.io.IOException e) {
                System.out.println("Please, enter a number");
                correct = false;
            }
        }
        build(player, x, y, numberOfWarriors);
        System.out.println(player + " builds an army at (" + x + ", " + y + ")\n");
    }

    public void build(Player player, int x, int y, int numberOfWarriors) {
        super.act(player);
        try {
            player.getBuildings().add(new AresBuilding(player, (Land) this.board.getTile(x, y), numberOfWarriors));
            ((AresPlayer) player).removeWarrior(numberOfWarriors);
        } catch (InvalidPositionException e) {
        }
    }
}
