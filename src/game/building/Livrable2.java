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
        Building ares1 = new AresBuilding(player, land, 0);
        System.out.println(board.boardToString(true));
    }
}
