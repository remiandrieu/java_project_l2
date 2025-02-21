package game.board;

/** a class to represent a tile of the board */
public abstract class Tile {
    /**
     * Return true if the tile has a building
     * @return true if the tile has a building.
     */
    public abstract boolean hasBuilding();

    /**
     * Returns position of the tile on the board it's associated with
     * @return the position of this tile
     */
    public int[] getPosition(){ //A FAIRE
        return new int[2]; 
    }  
}