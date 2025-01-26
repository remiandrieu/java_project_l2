package game.board;
import game.board.util.Ressource;

public class Forest extends Land{

    public Ressource produce() {
        return Ressource.WOOD;
    }

    public String toString() {
        return "Tree";
    }

}
