package game.player;
import java.util.*;
import game.board.util.*;

/** a class to model a player */
public class Player {

    protected String name;
    protected Map<Ressource, Integer> ressources;

    protected final int id;
    private static int currentId = 0;

    // protected List<Building> buildings;
    // protected Objective objective;

    /**
     * Create a board with a name and an empty HashMap for ressources
     * @param name the name of the player
     */
    public Player(String name){
        currentId++;
        this.name = name;
        this.ressources = new HashMap<>();
        this.id = currentId;

        for (Ressource r : Ressource.values()){
            this.ressources.put(r, 0);
        }
    }

    /**
     * Get the name of the player
     * @return the name of the player
     */
    public String getName() {
        return this.name;
    }

    /**
     * Get the ressources of the player
     * @return the ressources of the player
     */
    public Map<Ressource, Integer> getRessources() {
        return this.ressources;
    }

    /**
     * Get the id of the player
     * @return the id of the player
     */
    public int getId() {
        return this.id;
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
     * Removes a ressource from the player ressources
     * @param r a Ressource
     */
    public void removeRessoure(Ressource r) {
        this.ressources.put(r, this.ressources.get(r)-1);
    }

    /**
     * Removes a given amount of ressources from the player ressources
     * @param r a Ressource
     * @param value the number of ressources to remove
     */
    public void removeRessoure(Ressource r, int value) {
        this.ressources.put(r, this.ressources.get(r)-value);
    }


}
