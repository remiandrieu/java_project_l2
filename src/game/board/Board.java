package game.board;

import java.util.Random;

/* A class to represent a board */
public class Board {

    /* Attributes */
    protected Tile[][] grid;
    protected final int LENGTH;
    protected final int WIDTH;
    protected final Tile[] PLACABLE_TILES = {Fields, Forest, Mountain, Pasture};
    protected final double PROBABILITY_PICKING_NEW_LOCATION = 0.5;

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

    // TODO : isCorrectLocation method

    /**
     * Set the tile at line x and column y
     * @param x the line number
     * @param y the column number
     * @param tile the tile
     */
    public void setTile(int x, int y, Tile tile){
        return this.grid[x][y] = tile;
    }

    /**
     * Create a new grid according to the rules of the game
     */
    public void createGrid(){
        // At first, fill the grid with Sea tiles
        for (int i = 0; i < this.LENGTH; i++){
            for (int j = 0; j < this.WIDTH; j++){
                this.setTile(i, j, new Sea());
            }
        }

        Random random = new Random();

        // Choose a random number of land to place. This number is between 1/4 and 1/3 of the total number of tiles
        int numberOfLand = (this.LENGTH * this.WIDTH)/4 + random.nextInt((this.LENGTH * this.WIDTH)/3 - (this.LENGTH * this.WIDTH)/4);
        
        // Pick a first random location on the board
        int x = random.nextInt(this.LENGTH);
        int y = random.nextInt(this.WIDTH);
        
        // While there are lands to place
        while (numberOfLand > 1) {

            boolean chooseRandomLocation = false;

            // If it's a sea tile i.e. if you can place a land tile
            if (this.getTile(x, y) instanceof Sea) {
                
                // Choose a random type of Land, place it and decrease the number lands to place by 1.
                Land randomLand = new this.PLACABLE_TILES[random.nextInt(this.PLACABLE_TILES.length)]();
                this.setTile(x, y, randomLand);
                numberOfLand--;

                // Get the neighboring tiles and check if there is land
                Tile[] neighbours = this.getNeighbourTiles(x, y);
                boolean hasLandNeighbour = false;
                boolean allLandNeighbour = true;
                for (Tile neighbourTile : neighbours){
                    if (!(neighbourTile instanceof Sea)){
                        hasLandNeighbour = true;
                    } else {
                        allLandNeighbour = false;
                    }
                }

                /**
                 * If there isn't land in the neighboring tiles, you must place another tile next to it
                 * Else there is a certain probability to place a tile next to it (if there is a sea tile in the neighboring tiles)
                 * In the two cases above, pick a random neighboring tile that is not already a land tile
                 * Otherwise pick an other random location
                 */
                if (!(hasLandNeighbour) || (!(allLandNeighbour) && random.nextDouble(1) > this.PROBABILITY_PICKING_NEW_LOCATION)){
                    
                    // Pick the location of a random neighboring tile that is not already a land tile
                    int[] offset = {-1, 1};
                    int neighbouringX = x + offset[random.nextInt(offset.length)];
                    int neighbouringY = y + offset[random.nextInt(offset.length)];
                    while (!(this.isCorrectLocation(neighbouringX, neighbouringY)) || !(this.getTile(x, y) instanceof Sea)){
                        neighbouringX = x + offset[random.nextInt(offset.length)];
                        neighbouringY = y + offset[random.nextInt(offset.length)];
                    }
                    x = neighbouringX;
                    y = neighbouringY;

                } else {
                    
                    // Pick a random location on the board
                    chooseRandomLocation = true;

                }

            }

            if (chooseRandomLocation){
                // Pick a random location on the board
                x = random.nextInt(this.LENGTH);
                y = random.nextInt(this.WIDTH);
            }
        }
    }
}
