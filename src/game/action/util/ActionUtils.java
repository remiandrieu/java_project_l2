package game.action.util;

import game.board.*;

public class ActionUtils {
    
    
    /**
     * Common logic to validate tile position and properties
     */
    public static boolean validateTilePosition(Board board, int x, int y, boolean checkBuilding) {
        try {
            if (!board.isCorrectLocation(x, y)) {
                System.out.println("tile out of grid");
                return false;
            }
            else if (board.getTile(x, y) instanceof Sea) {
                System.out.println("tile is not a land");
                return false;
            }
            else if (checkBuilding && ((Land)board.getTile(x, y)).hasBuilding()) {
                System.out.println("tile already has a building");
                return false;
            }
            return true;
        } catch (InvalidPositionException e) {
            return false;
        }
    }
}
