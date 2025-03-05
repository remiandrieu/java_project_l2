package game.board;

import java.util.*;

import game.board.util.Ressource;

/** a class to model a board */
public class Board {

    /* Attributes */
    protected Tile[][] grid;
    protected final int LENGTH;
    protected final int WIDTH;
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
     * @throws InvalidPositionException if x y out of grid
    */
    public Tile getTile(int x, int y) throws InvalidPositionException{
        if (isCorrectLocation(x, y)){
            return this.grid[x][y];
        }
        throw new InvalidPositionException("coordinates " + x + ", " + y + " out of grid");
    }

    /**
     * Get the neighbours of the tile at line x and column y
     * @param x the line number
     * @param y the column number
     * @return The neighbours of the tile at position (x, y)
     * @throws InvalidPositionException if x y out of grid
    */
    public Tile[] getNeighbourTiles(int x, int y) throws InvalidPositionException{
        if (!isCorrectLocation(x, y)){
            throw new InvalidPositionException("coordinates " + x + ", " + y + " out of grid");
        }
        Tile[] res = new Tile[4];
        try{
            if (x-1 >= 0)
                res[0] = this.getTile(x-1, y);
            if (y+1 < this.WIDTH)
                res[1] = this.getTile(x, y+1);
            if (x+1 < this.LENGTH)
                res[2] = this.getTile(x+1, y);
            if (y-1 >= 0)
                res[3] = this.getTile(x, y-1);
        } catch (InvalidPositionException e){
            System.out.println();
        }
        return res;
    }

    /**
     * Get the coordinates of the neighbours of the tile at line x and column y
     * @param x the line number
     * @param y the column number
     * @return The coordinates of the neighbours of the tile at position (x, y)
     * @throws InvalidPositionException if x y out of grid
    */
    public int[][] getNeighbourCoordinates(int x, int y) throws InvalidPositionException{
        if (!isCorrectLocation(x, y)){
            throw new InvalidPositionException("coordinates " + x + ", " + y + " out of grid");
        }
        int[][] res = new int[4][];
        
        if (x-1 >= 0){
            int[] co = {x-1, y};
            res[0] = co;
        }
        if (y+1 < this.WIDTH){
            int[] co = {x-1, y+1};
            res[1] = co;
        }
        if (x+1 < this.LENGTH){
            int[] co = {x+1, y};
            res[2] = co;
        }
        if (y-1 >= 0){
            int[] co = {x, y-1};
            res[3] = co;
        }
        return res;
    }

    /**
     * Checks if a location is on the grid
     * @param x the line number
     * @param y the column number
     * @return true if the location is correct, false otherwise
     */
    public boolean isCorrectLocation(int x, int y){
        return x > -1 && x < this.LENGTH &&  y > -1 && y < this.WIDTH;
    }

    /**
     * Set the tile at line x and column y
     * @param x the line number
     * @param y the column number
     * @param tile the tile
     * @throws InvalidPositionException if x y out of grid
    */
    public void setTile(int x, int y, Tile tile) throws InvalidPositionException{
        if (isCorrectLocation(x, y)){
            this.grid[x][y] = tile;
        }
        else{
            throw new InvalidPositionException("coordinates " + x + ", " + y + " out of grid");
        }
    }

    /**
     * Get a random type of Land 
     * @return a random type of Land
     */
    public Land getRandomTypeOfLand(){
        Random random = new Random();
        Ressource[] ressources = Ressource.values();
        Ressource randomRessource = ressources[random.nextInt(ressources.length)];
        switch(randomRessource){
            case WHEAT: 
                return new Fields();
            case SHEEP:
                return new Pasture();
            case ORE:
                return new Mountain();
            default:
                return new Forest();
        }
    }


    /**
     * Get the coordinates of a random neighbour of a tile in parameters
     * @param x the line number
     * @param y the column number
     * @return coordinates of a random neighbour
     */
    public int[] getRandomCoordinatesNeighbour(int x, int y){
        Random random = new Random();
        int[] offset = {-1, 1};
        int[] res = {x, y};
        if (random.nextInt(2) == 0){
            res[0] += offset[random.nextInt(offset.length)];
        }
        else {
            res[1] += offset[random.nextInt(offset.length)];
        }
        return res;
    }

    /**
     * fill the grid with Sea tiles
     * @throws InvalidPositionException
     */
    public void fillWithSea() throws InvalidPositionException{
        for (int i = 0; i < this.LENGTH; i++){
            for (int j = 0; j < this.WIDTH; j++){
                this.setTile(i, j, new Sea());
            }
        }
    }

    /**
     * check if there is no land or no sea around
     * @param x the line number
     * @param y the column number
     * @return a array of 2 booleans hasLandNeighbour and allLandNeighbour
     * @throws InvalidPositionException
     */
    public boolean[] landNeighbour(int x, int y) throws InvalidPositionException{
        Tile[] neighbours = this.getNeighbourTiles(x, y);
        boolean hasLandNeighbour = false;
        boolean allLandNeighbour = true;
        for (Tile neighbourTile : neighbours){
            if (neighbourTile instanceof Sea){
                allLandNeighbour = false;
            }
            if (neighbourTile instanceof Land){
                hasLandNeighbour = true;
            }
        }
        boolean[] res = {hasLandNeighbour,allLandNeighbour};
        return res;
    }

    /**
     * Pick the location of a random neighboring tile that is a sea
     * @param x the line number
     * @param y the column number
     * @return the coordinates of the neighboring tile
     * @throws InvalidPositionException
     */
    public int[] randomSeaNeighbour(int x, int y) throws InvalidPositionException{
        int[] randomNeighbourCoordinates = {-1,-1};
        while (!this.isCorrectLocation(randomNeighbourCoordinates[0], randomNeighbourCoordinates[1]) || !(this.getTile(randomNeighbourCoordinates[0], randomNeighbourCoordinates[1]) instanceof Sea)){
            randomNeighbourCoordinates = getRandomCoordinatesNeighbour(x, y);
        }
        return randomNeighbourCoordinates;
    }

    /**
     * Create a new grid according to the rules of the game
     * @throws InvalidPositionException 
    */
    public void createGrid() throws InvalidPositionException{
        
        // Fill the grid with Sea tiles
        fillWithSea();
        
        Random random = new Random();

        // Choose a random number of land to place. This number is between 1/4 and 1/3 of the total number of tiles
        int numberOfLand = (this.LENGTH * this.WIDTH)/4 + 1 + random.nextInt((this.LENGTH * this.WIDTH)/3 - (this.LENGTH * this.WIDTH)/4);
        
        // Pick a first random location on the board
        int x = random.nextInt(this.LENGTH);
        int y = random.nextInt(this.WIDTH);
        boolean hasLandNeighbour = false;
        
        // While there are lands to place
        while (numberOfLand > 1 || !hasLandNeighbour) {

            boolean chooseRandomLocation = true;

            // If it's a sea tile i.e. if you can place a land tile
            if (this.getTile(x, y) instanceof Sea) {
                
                // Choose a random type of Land, place it and decrease the number lands to place by 1.
                Land randomLand = this.getRandomTypeOfLand();
                this.setTile(x, y, randomLand);
                numberOfLand--;

                // Get the neighboring tiles and check if there is land
                boolean[] booleanLandNeighbour = landNeighbour(x, y);
                hasLandNeighbour = booleanLandNeighbour[0];
                boolean allLandNeighbour = booleanLandNeighbour[1];

                /**
                 * If there isn't land in the neighboring tiles, you must place another tile next to it
                 * Else there is a certain probability to place a tile next to it (if there is a sea tile in the neighboring tiles)
                 * In the two cases above, pick a random neighboring tile that is not already a land tile
                 * Otherwise pick an other random location
                 */
                if (!hasLandNeighbour || (!allLandNeighbour && (random.nextDouble() < this.PROBABILITY_PICKING_NEW_LOCATION))){
                    int[] coordinates = randomSeaNeighbour(x, y);
                    x = coordinates[0];
                    y = coordinates[1];
                    chooseRandomLocation = false;
                } 

            }
            if (chooseRandomLocation){
                // Pick a random location on the board
                x = random.nextInt(this.LENGTH);
                y = random.nextInt(this.WIDTH);
            }
        }
    }

    /**
     * Detects the island of this board
     * @return a set of sets (islands) of lists of coordinates
     * @throws InvalidPositionException
     */
    public Set<Set<int[]>> detectIslands() throws InvalidPositionException {
        Set<Set<int[]>> res = new HashSet<>();
        Queue<int[]> file = new LinkedList<>();
        Set<int[]> visitees = new HashSet<>();
        for(int i = 0; i < this.LENGTH; i++){
            for(int j = 0; j < this.WIDTH; j++){
                Tile current = this.getTile(i, j);
                int[] coordinates = {i, j};
                if (current instanceof Land && !(visitees.contains(coordinates))){
                    file.add(coordinates);
                    visitees.add(coordinates);
                }
                if (file.size() != 0){
                    Set<int[]> current_island = new HashSet<>();
                    while(file.size() != 0){
                        int[] tuile = file.remove();
                        current_island.add(tuile);
                        int[][] neighbourCoordinates = this.getNeighbourCoordinates(tuile[0], tuile[1]);
                        for(int[] voisin : neighbourCoordinates){
                            if (this.getTile(voisin[0], voisin[1]) instanceof Land && !(visitees.contains(voisin))){
                                file.add(voisin);
                            }
                        }
                    }
                    res.add(current_island);                  
                }
            }
        }
        return res;
    }


    /**
     * Get a String representation of the board
     * @param buildings true to include buildings in the representation
     * @return a String representation of the board
     * @throws InvalidPositionException 
    */
        public String boardToString(boolean buildings) throws InvalidPositionException{
        String res = "";
        for (int x = 0; x < this.LENGTH; x++){
            for (int y = 0; y < this.WIDTH; y++){
                Tile tile = this.getTile(x, y);
                if (tile instanceof Sea){
                    res += ".   ";
                } else if (buildings && ((Land) tile).hasBuilding()){
                    res += "" + tile.toString().charAt(0) + ((Land) tile).getBuilding().toString().charAt(0) + ((Land) tile).getBuilding().getPlayer().getId() + " ";
                } else {
                    res += tile.toString().charAt(0) + "   ";
                }      
            }
            res += '\n';
        }
        return res;
    }

}
