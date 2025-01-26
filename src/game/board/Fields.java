package game.board;
import game.board.util.Ressource;

public class Fields extends Land {

    public Ressource produce() {
        return Ressource.WHEAT;
    }

    public String toString(){
        return "Field";
    }

}
