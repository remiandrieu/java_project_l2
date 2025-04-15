package game.player;

/** a class to model a Demeter player */
public class DemeterPlayer extends Player {

    /* Attribute */
    protected boolean thief;

    /**
     * Create a Demeter player with all attributes of Player and a boolean for thief
     * @param name the name of the player
     */
    public DemeterPlayer(String name){
        super(name);
        this.thief = false;
    }

    /**
     * Return true if the player has a thief, false otherwise
     * @return true if the player has a thief, false otherwise
     */
    public boolean hasThief() {
        return this.thief;
    }

    /**
     * Add the thief to the player
     */
    public void addThief() {
        this.thief = true;
    }

    /**
     * Remove the thief from the player
     */
    public void removeThief() {
        this.thief = false;
    }

}
