package game.board;

public class BoardMain {
    public static void main(String[] args) throws InvalidPositionException {
        Board board = new Board(7, 6);
        board.createGrid();
        System.out.println(board.boardToString(false));
    }
}
