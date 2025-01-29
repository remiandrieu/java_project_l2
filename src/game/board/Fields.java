package game.board;
import game.board.util.Ressource;

/** a closs to model Fields */
public class Fields extends Land {

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
