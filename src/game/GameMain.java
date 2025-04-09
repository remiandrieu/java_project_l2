package game;

import java.util.*;

import listchooser.*;

public class GameMain {

    public static void main(String[] args) {
        List<Game> possibleGames = new ArrayList<>();
        possibleGames.add(new DemeterGame());
        possibleGames.add(new AresGame());
        ListChooser<Game> lc = new InteractiveListChooser<>();
        Game chosenGame = lc.choose("Choose a game !", possibleGames);
        chosenGame.play();
    }

}
