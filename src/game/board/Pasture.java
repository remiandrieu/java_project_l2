package game.board;
import game.board.util.Ressource;

/** a class to model Pasture */
public class Pasture extends Land {

    /**
     * creates a pasture with coordinates
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Pasture(int x, int y){
        super(x, y);
    }

    /** produces the SHEEP ressource
     * @return the ressource SHEEP
     */
    public Ressource produce() {
        return Ressource.SHEEP;
    }
    
    /** creates a string representation of the pasture
     * @return a string representation of the pasture
     */
    public String toString(){
        return "Pasture";
    }
}
