package game;

import java.util.Random;

import game.action.*;
import game.objective.*;
import game.player.*;

public class AresGame extends Game {

    protected Player createPlayer(String playerName){
        AresPlayer player = new AresPlayer(playerName);
        player.addWarrior(30);
        return player;
    };

    public void initActions(){
        super.initActions();
        this.actions.add(new BuildArmy(board));
        this.actions.add(new EvolveArmy(board));
        this.actions.add(new BuyWarriors(board));
        this.actions.add(new PlaceWarrior(board));
        this.actions.add(new Attack(board));
        this.actions.add(new BuySecretWeapon(board));
    }

    protected void setPlayerObjective(Player player){
        Objective[] possibleObjectives = {new AresConquerIsland(player, this.board),
                                          new AresConquerTiles(player, this.board),
                                          new AresEnoughWarriors(player, this.board)};
        Objective randomObjective = possibleObjectives[new Random().nextInt(possibleObjectives.length)];
        player.setObjective(randomObjective);
    }

    protected StartAction startBuilding(){
        return new BuildArmyStart(board);
    }

    public String toString(){
        return "ARES";
    }

}
