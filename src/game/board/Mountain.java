package game.board;
import game.board.util.Ressource;

/** a class to model Mountain */
public class Mountain extends Land {

    /** produces the ORE ressource
     * @return the ressource ORE
     */
    public Ressource produce() {
        return Ressource.ORE;
    }

    
    /** creates a string representation of the moutain
     * @return a string representation of the mountain
     */
    public String toString(){
        return "Mountain";
    }

}
