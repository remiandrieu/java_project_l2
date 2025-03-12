package game.board;

public class Livrable1 {
    public static void main(String[] args) throws InvalidPositionException {
        int length;
        int width;
        boolean error = false;
        
        if (args.length >= 2){
            try{
                length = Integer.parseInt(args[0]);
                width = Integer.parseInt(args[1]);
                Board board = new Board(length, width);
                board.createGrid();
                System.out.println(board.boardToString(false));
                System.out.println(board.detectIslands());
            } catch (Exception e){
                System.out.println(e);
                error = true;
            }
        } else {
            error = true;
        }

        if (error) {
            System.out.println("How to use :");
            System.out.println("livrable1.java <length> <width>");
            System.out.println("<length> : length of the board >= 3");
            System.out.println("<width> : width of the board >= 3");
        }
    }
}
