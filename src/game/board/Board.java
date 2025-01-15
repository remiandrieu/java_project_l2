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
     * Get the tile at line x and column y
     * @param x the line number
     * @param y the column number
     * @return The tile at position (x, y)
     */
    public Tile getTile(int x, int y){
        return this.grid[x][y];
    }

    /**
     * Get the neighbours of the tile at line x and column y
     * @param x the line number
     * @param y the column number
     * @return The neighbours of the tile at position (x, y)
     */
    public Tile[] getNeighbourTiles(int x, int y){
        Tile[] res = new Tile[4];
        if (x-1 > 0)
            res[0] = this.getTile(x-1, y);
        if (y+1 < this.WIDTH)
            res[1] = this.getTile(x, y+1);
        if (x+1 < this.LENGTH)
            res[2] = this.getTile(x+1, y);
        if (y-1 > 0)
            res[3] = this.getTile(x, y-1);
        return res;
    }

    /**
     * Set the tile at line x and column y
     * @param x the line number
     * @param y the column number
     * @param tile the tile
     */
    public void setTile(int x, int y, Tile tile){
        return this.grid[x][y] = tile;
    }
}
