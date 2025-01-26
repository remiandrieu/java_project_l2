package game.board;
import game.board.util.Ressource;

public abstract class Land extends Tile {
    
    public abstract Ressource produce();

    public abstract String toString();
    
}
