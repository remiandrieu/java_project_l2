package game.board;
import game.board.util.Ressource;

public class Pasture extends Land {

    public Ressource produce() {
        return Ressource.SHEEP;
    }
    
    public String toString(){
        return "Pasture";
    }
}
