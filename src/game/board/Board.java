package game.board;

import java.util.Random;

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
        this.grid = new Tile[LENGTH][WIDTH];
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

    public void createGrid(){
        for (int i = 0; i < this.LENGTH; i++){
            for (int j = 0; j < this.WIDTH; j++){
                this.setTile(i, j, new Sea());
            }
        }

        Random random = new Random();
        int numberOfLand = (this.LENGTH * this.WIDTH)/4 + random.nextInt((this.LENGTH * this.WIDTH)/3 - (this.LENGTH * this.WIDTH)/4);

        while (numberOfLand > 1) {
            
            int x = random.nextInt(this.LENGTH);
            int y = random.nextInt(this.WIDTH);

            if (this.getTile(x, y) instanceof Sea) {
                
                // TODO : replace Land by a random land child
                this.setTile(x, y, Land);
                numberOfLand--;

                Tile[] neighbours = this.getNeighbourTiles(x, y);
                boolean hasLandNeighbour = false;
                for (Tile neighbourTile : neighbours){
                    if (!(neighbourTile instanceof Sea)){
                        hasLandNeighbour = true;
                    }
                }

                // TODO : if 0 land neighbour place another land else place an other land with a certain probability

            }
        }
    }
}
