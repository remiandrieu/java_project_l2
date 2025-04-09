package game.action;

import game.board.Board;
import game.board.InvalidPositionException;
import game.board.Land;
import game.board.Sea;
import game.building.AresBuilding;
import game.player.AresPlayer;
import game.player.Player;
import listchooser.util.Input;

/* a class to model the BuildArmy action in Ares Game */
public class BuildArmyStart extends StartAction {

    /**
     * Create the buildArmy action
     * @param board the board
     */
    public BuildArmyStart(Board board){
        super("Build an Army at the start of the game", board);
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
                    if(this.board.getTile(x, y) instanceof Land && !((Land) this.board.getTile(x, y)).hasBuilding()){
                        return (super.isPossible(player));
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
                System.out.println(player + " : place your " + ((player.getBuildings().size()==0)?"first":"second") + " army !");
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
            }
            catch (InvalidPositionException e) {
                correct = false;
            }
        }
        build(player, x, y, 1);
        System.out.println(player + " builds an army at (" + x + ", " + y + ") with 1 warrior\n");
    }

    public void build(Player player, int x, int y, int numberOfWarriors) {
        super.act(player);
        try {
            player.getBuildings().add(new AresBuilding(player, (Land) this.board.getTile(x, y), numberOfWarriors));
        } catch (InvalidPositionException e) {
        }
    }
}
