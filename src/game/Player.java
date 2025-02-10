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
        for (Ressource r : Ressource.values()){
            this.ressources.put(r, 0);
        }
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

    /**
     * Add a ressource to the player ressources
     * @param r a Ressource
     */
    public void addRessoure(Ressource r) {
        this.ressources.put(r, this.ressources.get(r)+1);
    }

    /**
     * Add a ressource to the player ressources
     * @param r a Ressource
     * @param value the number of ressources to add
     */
    public void addRessoure(Ressource r, int value) {
        this.ressources.put(r, this.ressources.get(r)+value);
    }

    /**
     * Add a ressource to the player ressources
     * @param r a Ressource
     */
    public void removeRessoure(Ressource r) {
        this.ressources.put(r, this.ressources.get(r)-1);
    }

    /**
     * Add a ressource to the player ressources
     * @param r a Ressource
     * @param value the number of ressources to add
     */
    public void removeRessoure(Ressource r, int value) {
        this.ressources.put(r, this.ressources.get(r)-value);
    }


}
