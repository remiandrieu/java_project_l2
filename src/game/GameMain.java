package game;

public class GameMain {

    public static void main(String[] args) {
        Game game = (Game) new DemeterGame();
        game.play();
    }

}
