package game.action;

import game.action.util.BuildUtils;
import game.board.Board;
import game.board.Coordinates;
import game.board.InvalidPositionException;
import game.board.Land;
import game.board.util.Ressource;
import game.building.Port;
import game.player.AresPlayer;
import game.player.Player;

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
        Coordinates coor = new Coordinates(-1, -1);
		boolean correct = false;
        try {
            System.out.println(player + " wants to build a port.\nAt which coordinates ?");
            while (!correct) {
            	correct = true;
                coor = BuildUtils.askCoordinates(this.board);
                if (this.board.landNeighbour(coor.getX(), coor.getY())[1]){
                    System.out.println("tile not next to a sea tile");
                    correct = false;
                }
                else if (player instanceof AresPlayer && !((AresPlayer) player).islandsConditions(this.board, coor)){
                    System.out.println("Is not a valid position");
                    correct = false;
                }
            }
        } catch (InvalidPositionException e) {
        }
        
        build(player, coor.getX(), coor.getY());
        System.out.println(player + " builds a port at (" + coor.getX() + ", " + coor.getY() + ")\n");
    }

    /**
     * A useful method to build a port in BuildPort action
     * @param player the player who builds the port
     * @param x the abscissa where the player wants to build the port
     * @param y the ordinate where the player wants to build the port
     */
    public void build(Player player, int x, int y) {
        super.act(player);
        try {
            player.getBuildings().add(new Port(player, (Land) this.board.getTile(x, y)));
        } catch (InvalidPositionException e) {
        }
    }

}
