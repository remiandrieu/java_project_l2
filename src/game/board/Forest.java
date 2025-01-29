package game.board;
import game.board.util.Ressource;

/** a closs to model Forest */
public class Forest extends Land{

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
