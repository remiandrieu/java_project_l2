package game.board;

/** a class to represent a tile of the board */
public abstract class Tile {

    /* Attribute */
    protected Coordinates coordinates;

    /**
     * creates a tile with coordinates
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Tile(int x, int y){
        this.coordinates = new Coordinates(x, y);
    }

    /**
     * Return tile coordinates
     * @return the coordinates of the tile
     */
    public Coordinates getCoordinates() {
        return coordinates;
    }

}