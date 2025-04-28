package game.action.util;

import game.board.Board;
import game.board.Coordinates;
import game.board.InvalidPositionException;
import game.board.Land;
import game.board.Sea;

public class BuildUtils {
    /**
     * Asks the player for coordinates.
     * @return the coordinates given by the player
     */
    public static Coordinates askCoordinates(Board board) {
		boolean correct = false;
        Coordinates coor = new Coordinates(-1, -1);
        try {
            while (!correct) {
            	correct = true;

                coor = ActionUtils.askCoordinates();

                if (!board.isCorrectLocation(coor.getX(), coor.getY())){
                    System.out.println("tile out of grid");
                    correct = false;
                }
                else if (board.getTile(coor.getX(), coor.getY()) instanceof Sea){
                    System.out.println("tile is not a land");
                    correct = false;
                }
                else if (((Land)board.getTile(coor.getX(), coor.getY())).hasBuilding()){
                    System.out.println("tile already has a building");
                    correct = false;
                }
            }
        } catch (InvalidPositionException e) {
        }
        return coor;
    }
}
