package game;

import java.util.Random;

import game.action.*;
import game.objective.*;
import game.player.*;

/* a class to model a game of Ares */
public class AresGame extends Game {

    /**
     * Create an Ares player
     * @param playerName the player's name
     * @return return an Ares player
     */
    protected Player createPlayer(String playerName){
        AresPlayer player = new AresPlayer(playerName);
        player.addWarrior(30);
        return player;
    };

    /**
     * Initialize the possible actions of the game
     */
    public void initActions(){
        super.initActions();
        this.actions.add(new BuildArmy(board));
        this.actions.add(new EvolveArmy(board));
        this.actions.add(new BuyWarriors(board));
        this.actions.add(new PlaceWarrior(board));
        this.actions.add(new Attack(board));
        this.actions.add(new BuySecretWeapon(board));
    }

    /**
     * Set the objective of the player in parameters
     * @param player a player
     */
    protected void setPlayerObjective(Player player){
        Objective[] possibleObjectives = {new AresConquerIsland(player, this.board),
                                          new AresConquerTiles(player, this.board),
                                          new AresEnoughWarriors(player, this.board)};
        Objective randomObjective = possibleObjectives[new Random().nextInt(possibleObjectives.length)];
        player.setObjective(randomObjective);
    }

    /**
     * Get an action used to place the first buildings of the game
     * @return return an action used to place the first buildings of the game
     */
    protected StartAction startBuilding(){
        return new BuildArmyStart(board);
    }

    /**
     * The game's name
     */
    public String toString(){
        return "ARES";
    }

}
