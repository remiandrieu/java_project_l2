package game.action;

import game.board.Board;
import game.board.Coordinates;
import game.board.InvalidPositionException;
import game.board.Land;
import game.board.Sea;
import game.board.util.Ressource;
import game.building.Port;
import game.player.AresPlayer;
import game.player.Player;
import listchooser.util.Input;

/* a class to model the BuildPort action in Ares Game */
public class BuildPort extends CommonAction {

    /**
     * Create the BuildFarm action
     * @param board the board
     */
    public BuildPort(Board board){
        super("build a port", board);
        this.cost.put(Ressource.WOOD, 1);
        this.cost.put(Ressource.SHEEP, 2);
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
                    if(this.board.getTile(x, y) instanceof Land && !((Land) this.board.getTile(x, y)).hasBuilding() && !(this.board.landNeighbour(x, y)[1]) && (!(player instanceof AresPlayer) || ((AresPlayer) player).islandsConditions(this.board, new Coordinates(x, y)))){
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
            System.out.println(player + " wants to build a port.\nAt which coordinates ?");
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
                else if (this.board.landNeighbour(x, y)[1]){
                    System.out.println("tile not next to a sea tile");
                    correct = false;
                }
                else if (player instanceof AresPlayer && !((AresPlayer) player).islandsConditions(this.board, new Coordinates(x, y))){
                    System.out.println("Is not a valid position");
                    correct = false;
                }
            }
        } catch (InvalidPositionException e) {
        }
        
        build(player, x, y);
        System.out.println(player + " builds a port at (" + x + ", " + y + ")\n");
        

    }

    public void build(Player player, int x, int y) {
        super.act(player);
        try {
            player.getBuildings().add(new Port(player, (Land) this.board.getTile(x, y)));
        } catch (InvalidPositionException e) {
        }
    }

}
