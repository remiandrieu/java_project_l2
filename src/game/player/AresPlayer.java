package game.player;

public class AresPlayer extends Player {
    
    protected int nbWarrior;

    /**
     * Create a Ares player with all attributes of Player and a nbWarrior
     * @param name the name of the player
     */
    public AresPlayer(String name){
        super(name);
        this.nbWarrior = 0;
    }

    public int getNbWarrior() {
        return nbWarrior;
    }

    public void addWarrior(int n){
        this.nbWarrior += n; 
    }

    public void removeWarrior(int n){
        this.nbWarrior -= n; 
    }
    
    
}
