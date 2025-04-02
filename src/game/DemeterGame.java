package game;

import game.player.*;

public class DemeterGame extends Game {

    public Player createPlayer(String playerName){
        return new DemeterPlayer(playerName);
    };

}
