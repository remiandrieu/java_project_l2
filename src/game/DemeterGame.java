package game;

import java.util.Random;

import game.action.*;
import game.objective.*;
import game.player.*;

public class DemeterGame extends Game {

    protected Player createPlayer(String playerName){
        return new DemeterPlayer(playerName);
    };

    /**
     * Initialize the possible actions of the game
     */
    public void initActions(){
        super.initActions();
        this.actions.add(new BuildFarm(board));
        this.actions.add(new EvolveFarm(board));
        this.actions.add(new BuyThief(board));
        this.actions.add(new PlayThief(board));
    }

    /**
     * Set the objective of the player in parameters
     * @param player a player
     */
    protected void setPlayerObjective(Player player){
        Objective[] possibleObjectives = {new EnoughPoints(player, this.board)};
        Objective randomObjective = possibleObjectives[new Random().nextInt(possibleObjectives.length)];
        player.setObjective(randomObjective);
    }

    /**
     * Get an action used to place the first buildings of the game
     * @return return an action used to place the first buildings of the game
     */
    protected StartAction startBuilding(){
        return new BuildFarmStart(board);
    }

    /**
     * The game's name
     */
    public String toString(){
        return "DEMETER";
    }

}
