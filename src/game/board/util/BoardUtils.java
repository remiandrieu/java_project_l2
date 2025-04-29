package game.board.util;

import java.util.*;

import game.board.*;
import game.player.AresPlayer;
import game.player.Player;

/* A class to model the useful methods for the board */
public class BoardUtils {

    /**
     * Returns the first available land tile on the board i.e. the first tile that isn't a sea and doesn't have a building
     * @return the first available land tile on the board
    */
    public static Coordinates firstAvailableCoords(Board board) {
        for (int x = 0; x < board.getLength(); x++) {
            for (int y = 0; y < board.getWidth(); y++) {
                try {
                    Tile tile = board.getTile(x, y);
                    if (tile instanceof Land && !((Land) tile).hasBuilding()) {
                        return new Coordinates(x, y);
                    }
                } catch (InvalidPositionException e) {
                }
            }
        }
        return null;
    }

    /**
     * Returns the first available land tile on the board i.e. the first tile that isn't a sea and doesn't have a building
     * @param board the board we want the land on
     * @param needsSeaNeighbour if we want to have a land next to the sea
     * @param player if player != null, checks the islands conditions
     * @return the first available land tile on the board
    */
    public static Coordinates firstAvailableCoord(Board board, boolean needsSeaNeighbour, Player player){
        int x = 0;
        int y = -1;
        boolean stop = false;
        Tile tile;
        Land land;
        while (!stop && x < board.getLength()) {
            y += 1;
            if (y == board.getWidth()){
                y = 0;
                x += 1;
            }
            try {
                tile = board.getTile(x, y);
                if (!(tile instanceof Sea)){
                    land = (Land) tile;
                    if (!land.hasBuilding()){
                        if (!needsSeaNeighbour || !board.landNeighbour(x, y)[1])
                            if (player == null || player instanceof AresPlayer){
                                if (((AresPlayer) player).islandsConditions(board, new Coordinates(x, y)))
                                    stop = true;
                            }
                            else {
                                stop = true; 
                            }
                    }
                }
            } catch (InvalidPositionException e) {}
        }
        return new Coordinates(x, y);
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

    /**
     * Returns the first available land tile on the board i.e. the first tile that isn't a sea and doesn't have a building
     * @param board the board we want the land on
     * @return the first available land tile on the board
    */
    public static Land firstAvailableLand(Board board) {
        try {
            Coordinates co = firstAvailableCoords(board);
            Tile res = board.getTile(co.getX(), co.getY());
            return (Land) res;
        } catch (Exception e) {
        }
        return null;
    }

}
