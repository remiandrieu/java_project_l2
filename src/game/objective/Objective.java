package game.objective;

import game.board.Board;
import game.player.Player;

/* a class to model an objective */
public abstract class Objective {
    
    /* Attributes */
    protected Player player;
    protected Board board;
    protected String description;

    /**
     * Create an objective 
     * @param player who has the objective
     * @param board the board
     * @param description the description of the objective
     */
    public Objective(Player player, Board board, String description) {
        this.player = player;
        this.board = board;
        this.description = description;
    }

    /**
     * checks if the objective is achieved
     * @return true if the objective is achieved, false otherwise
     */
    public abstract boolean isAchieved();

    /**
     * getter for the description of the label
     * @return the description of the label
     */
    public String getDescription(){
        return this.description;
    }

    /**
     * print the objective with its description and its player
     */
    public void printObjective(){
        System.out.println("The objective for " + this.player.getName() + " is : " + this.description);
    }
}