package game.action;

import game.action.util.BuildUtils;
import game.board.Board;
import game.board.Coordinates;
import game.board.InvalidPositionException;
import game.board.Land;
import game.building.AresBuilding;
import game.player.AresPlayer;
import game.player.Player;

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
		System.out.println(player + " : place your " + ((player.getBuildings().size()==0)?"first":"second") + " army !");
        Coordinates coor = BuildUtils.askCoordinates(this.board);
        build(player, coor.getX(), coor.getY(), 1);
        System.out.println(player + " builds an army at (" + coor.getX() + ", " + coor.getY() + ") with 1 warrior\n");
    }

    /**
     * A useful method to build an army in BuildArmy action
     * @param player the player who builds its army
     * @param x the abscissa where the player wants to build its army
     * @param y the ordinate where the player wants to build its army
     * @param numberOfWarriors the number of warriors in the army
     */
    public void build(Player player, int x, int y, int numberOfWarriors) {
        super.act(player);
        try {
            player.getBuildings().add(new AresBuilding(player, (Land) this.board.getTile(x, y), numberOfWarriors));
        } catch (InvalidPositionException e) {
        }
    }
}
