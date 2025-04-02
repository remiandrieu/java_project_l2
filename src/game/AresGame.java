package game;

import game.player.*;

public class AresGame extends Game {

    public Player createPlayer(String playerName){
        return new DemeterPlayer(playerName);
    };

}
