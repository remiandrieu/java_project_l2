package game.player;
import java.util.*;
import game.board.util.*;
import game.building.*;
import game.objective.Objective;

/** a class to model a player */
public class Player {

    protected String name;
    protected Map<Ressource, Integer> ressources;

    protected final int id;
    private static int currentId = 0;
    protected List<Building> buildings;
    protected Objective objective;

    /**
     * Create a player with a name, an empty HashMap for ressources and an empty ArrayList for buildings
     * @param name the name of the player
     */
    public Player(String name){
        currentId++;
        this.name = name;
        this.ressources = new HashMap<>();
        this.buildings = new ArrayList<>();
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
     * Get the buildings of the player
     * @return the buildings of the player
     */
    public List<Building> getBuildings() {
        return this.buildings;
    }

    /**
     * Get the objective of the player
     * @return the objective of the player
     */
    public Objective getObjective() {
        return this.objective;
    }

    /**
     * Set the objective of the player
     * @param objective an objective
     */
    public void setObjective(Objective objective) {
        this.objective = objective;
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

    
    public String toString() {
        String inventory = "";
        for (Ressource r : Ressource.values()){
            inventory += r.toString() + "(" + this.ressources.get(r) + ") ";
        }
        return this.name + ": [ " + inventory + "]";
    }


}
