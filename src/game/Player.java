package game;
import java.util.*;
import game.board.util.*;

/** a class to model a player */
public abstract class Player {

    protected String name;
    protected Map<Ressource, Integer> ressources;
    // protected List<Building> buildings;
    // protected Objective objective;

    /**
     * Create a board with a name and an empty HashMap for ressources
     * @param name the name of the player
     */
    public Player(String name){
        this.name = name;
        this.ressources = new HashMap<>();
    }

    /**
     * Get the name of the player
     * @return the name of the player
     */
    public String getName() {
        return name;
    }

    /**
     * Get the ressources of the player
     * @return the ressources of the player
     */
    public Map<Ressource, Integer> getRessources() {
        return ressources;
    }


}
