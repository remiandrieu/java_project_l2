package game.board;
import game.board.util.Ressource;

/** a class to model Forest */
public class Forest extends Land{

    /**
     * creates a forest with coordinates
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Forest(int x, int y){
        super(x, y);
    }

    /** produces the WOOD ressource
     * @return the ressource WOOD
     */
    public Ressource produce() {
        return Ressource.WOOD;
    }

    /** creates a string representation of the forest
     * @return a string representation of the forest
     */
    public String toString() {
        return "Tree";
    }

}
