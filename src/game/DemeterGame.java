package game;

import game.action.*;
import game.action.StartAction;
import game.player.*;

public class DemeterGame extends Game {

    public Player createPlayer(String playerName){
        return new DemeterPlayer(playerName);
    };

    public void initActions(){
        super.initActions();
        this.actions.add(new BuildFarm(board));
        this.actions.add(new EvolveFarm(board));
        this.actions.add(new BuyThief(board));
        this.actions.add(new PlayThief(board));
    }

    public StartAction startBuilding(){
        return new BuildFarmStart(board);
    }

    public String toString(){
        return "DEMETER";
    }

}
