package game.board.util;

import java.util.*;

import game.board.*;

public class BoardUtils {

    /**
     * Returns the first available land tile on the board i.e. the first tile that isn't a sea and doesn't have a building
     * @return the first available land tile on the board
    */
    public static Coordinates firstAvailableCoord(Board board){
        int x = 0;
        int y = 0;
        try{
            Tile tile = board.getTile(0, 0);
            while(x < board.getLength() && ((tile instanceof Sea) || !(tile instanceof Sea) && ((Land) tile).hasBuilding())){
                while(y < board.getWidth() && ((tile instanceof Sea) || !(tile instanceof Sea) && ((Land) tile).hasBuilding())){
                    tile = board.getTile(x, y);
                    if(!(y < board.getWidth() && ((tile instanceof Sea) || !(tile instanceof Sea) && ((Land) tile).hasBuilding()))){
                        return new Coordinates(x, y);
                    }
                    y += 1;
                }
                x += 1;
                y = 0;
            }
        } catch(InvalidPositionException e){
        }
        return new Coordinates(x, y);
    }

    /**
     * Returns the first available land tile on the board i.e. the first tile that isn't a sea and doesn't have a building
     * @return the first available land tile on the board
    */
    public static Land firstAvailableLand(Board board) {
        for (int x = 0; x < board.getLength(); x++) {
            for (int y = 0; y < board.getWidth(); y++) {
                try {
                    Tile tile = board.getTile(x, y);
                    if (tile instanceof Land && !((Land) tile).hasBuilding()) {
                        return (Land) tile;
                    }
                } catch (InvalidPositionException e) {
                }
            }
        }
        return null;
    }

    /**
     * Returns the n first available coordinates on the board
     * @return the n first available coordinates on the board
    */
    public static List<Coordinates> nFirstAvailableCoords(Board board, int n) {
        List<Coordinates> res = new ArrayList<>();
        for (int x = 0; x < board.getLength(); x++) {
            for (int y = 0; y < board.getWidth(); y++) {
                try {
                    Tile tile = board.getTile(x, y);
                    if (tile instanceof Land && !((Land) tile).hasBuilding()) {
                        res.add(new Coordinates(x, y));
                        if (res.size() == n) return res;
                    }
                } catch (InvalidPositionException e) {
                }
            }
        }
        return res;
    }

}
