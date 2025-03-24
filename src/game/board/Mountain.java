package game.board;
import game.board.util.Ressource;

/** a class to model Mountain */
public class Mountain extends Land {

    /**
     * creates a mountain with coordinates
     * @param x the x coordinate
     * @param y the y coordinate
     */
    public Mountain(int x, int y){
        super(x, y);
    }

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
