package game;

import game.action.*;
import game.player.*;

public class DemeterGame extends Game {

    protected Player createPlayer(String playerName){
        return new DemeterPlayer(playerName);
    };

    public void initActions(){
        super.initActions();
        this.actions.add(new BuildFarm(board));
        this.actions.add(new EvolveFarm(board));
        this.actions.add(new BuyThief(board));
        this.actions.add(new PlayThief(board));
    }

    protected StartAction startBuilding(){
        return new BuildFarmStart(board);
    }

    public String toString(){
        return "DEMETER";
    }

}
