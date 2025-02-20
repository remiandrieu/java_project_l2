package game.building;

import game.board.*;

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
        System.out.println(board.boardToString(true));
    }
}
