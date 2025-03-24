package game.board;
import game.board.util.Ressource;

/** a class to model Fields */
public class Fields extends Land {

    /**
     * creates a field with coordinates
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Fields(int x, int y){
        super(x, y);
    }

    /** produces the WHEAT ressource
     * @return the ressource WHEAT
     */
    public Ressource produce() {
        return Ressource.WHEAT;
    }

    /** creates a string representation of the fields
     * @return a string representation of the fields
     */
    public String toString(){
        return "Field";
    }

}
