package game.building;

import game.board.*;
import game.player.Player;

public class Livrable2 {
    public static void main(String[] args) throws InvalidPositionException {
        int length = 10;
        int width = 10;
        if (args.length > 2){
            length = Integer.parseInt(args[0]);
            width = Integer.parseInt(args[1]);
        }
        Board board = new Board(length, width);
        board.createGrid();
        Player player = new Player("player");
        Land land;
        try{
            int x = 0;
            int y = 0;
            Tile tile = board.getTile(0, 0);
            while(x < length && (tile instanceof Sea) && (!tile.hasBuilding())){
                while(y < width && (tile instanceof Sea) && (!tile.hasBuilding())){
                    tile = board.getTile(x, y);
                    y += 1;
                }
                x += 1;
                y = 0;
            }
            land = (Land) tile;
        } catch(InvalidPositionException e){
            return;
        }
        Building ares1 = new AresBuilding(player, land, 0);
        System.out.println(board.boardToString(true));
    }
}