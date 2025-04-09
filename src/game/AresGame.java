package game;

import game.action.*;
import game.player.*;

public class AresGame extends Game {

    public Player createPlayer(String playerName){
        return new AresPlayer(playerName);
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

    public StartAction startBuilding(){
        return new BuildArmyStart(board);
    }

    public String toString(){
        return "ARES";
    }

}
