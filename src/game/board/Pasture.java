package game.board;
import game.board.util.Ressource;

/** a class to model Pasture */
public class Pasture extends Land {

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
