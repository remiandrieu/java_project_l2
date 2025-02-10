package game.building;

import game.board.*;

public class Livrable2 {
    public static void main(String[] args) throws InvalidPositionException {
        Board board = new Board(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        board.createGrid();
        System.out.println(board.boardToString(false));
        System.out.println(board);
        

    }
}
