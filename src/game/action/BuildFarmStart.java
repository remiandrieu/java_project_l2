package game.action;

import game.board.Board;
import game.board.InvalidPositionException;
import game.board.Land;
import game.board.Sea;
import game.building.DemeterBuilding;
import game.player.Player;
import listchooser.util.Input;

/* a class to model the BuildFarm action in Ares Game */
public class BuildFarmStart extends StartAction {

    /**
     * Create the BuildFarm action
     * @param board the board
     */
    public BuildFarmStart(Board board){
        super("build a farm at the start of the game", board);
    }

    /**
     * determines if it's possible to build a farm
     * @param player the player who wants to build a farm
     * @return true if it's possible, else false
     */
    public boolean isPossible(Player player){
        for (int x = 0; x < this.board.getLength(); x++){
            for (int y = 0; y < this.board.getWidth(); y++){
                try {
                    if(this.board.getTile(x, y) instanceof Land && !((Land) this.board.getTile(x, y)).hasBuilding()){
                        return super.isPossible(player);
                    }
                } catch (InvalidPositionException e) {
                }
            }
        }
        return false;
    }

    /**
     * builds an farm if possible
     * @param player the player who wants to build an farm
     */
    public void act(Player player){
        int x = -1;
        int y = -1;
		boolean correct = false;
        try {
            System.out.println(player + " : place your " + ((player.getBuildings().size()==0)?"first":"second") + " building !");

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
        } catch (InvalidPositionException e) {
        }

        System.out.println(player + " builds a farm at (" + x + ", " + y + ")\n");
        build(player, x, y);
    }

    /**
     * A useful method to build a farm in BuildFarm action
     * @param player the player who builds the farm
     * @param x the abscissa where the player wants to build the farm
     * @param y the ordinate where the player wants to build the farm
     */
    public void build(Player player, int x, int y) {
        super.act(player);
        try {
            player.getBuildings().add(new DemeterBuilding(player, (Land) this.board.getTile(x, y)));
        } catch (InvalidPositionException e) {
        }
    }

}
