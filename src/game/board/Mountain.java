package game.board;
import game.board.util.Ressource;

public class Mountain extends Land {

    public Ressource produce() {
        return Ressource.ORE;
    }

    public String toString(){
        return "Mountain";
    }

}
