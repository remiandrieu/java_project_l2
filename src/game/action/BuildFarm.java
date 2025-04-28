package game.action;

import game.action.util.BuildUtils;
import game.board.Board;
import game.board.Coordinates;
import game.board.InvalidPositionException;
import game.board.Land;
import game.board.util.Ressource;
import game.building.DemeterBuilding;
import game.player.Player;

/* a class to model the BuildFarm action in Ares Game */
public class BuildFarm extends DemeterAction {

    /**
     * Create the BuildFarm action
     * @param board the board
     */
    public BuildFarm(Board board){
        super("build a farm", board);
        this.cost.put(Ressource.WOOD, 1);
        this.cost.put(Ressource.ORE, 1);
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
        System.out.println(player + " wants to build a farm.\nAt which coordinates ?");
        Coordinates coor = BuildUtils.askCoordinates(this.board);
        System.out.println(player + " builds a farm at (" + coor.getX() + ", " + coor.getY() + ")\n");
        build(player, coor.getX(), coor.getY());
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
