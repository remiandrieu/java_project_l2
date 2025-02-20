package game.board;

/** a class to represent the sea tiles */
public class Sea extends Tile {

    /**
     * Return false as seas don't have buildings.
     * @return false
     */
    public boolean hasBuilding(){
        return false;
    } 
}
