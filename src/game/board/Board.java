package game.board;

/* A class to represent a board */
public class Board {

    /* Attributes */
    protected Tile[][] grid;
    protected final int LENGTH;
    protected final int WIDTH;

    /**
     * Create a board with a length and a width
     * @param length the length of the board
     * @param width the width of the board
     */
    public Board(int length, int width){
        this.LENGTH = length;
        this.WIDTH = width;
    }

    /**
     * Get the tile at line x and at column y
     * @param x the line number
     * @param y the column number
     * @return The tile at position (x, y)
     */
    public Tile getTile(int x, int y){
        return this.grid[x][y];
    }
}
