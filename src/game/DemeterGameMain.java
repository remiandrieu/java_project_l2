package game;

public class DemeterGameMain {

    public static void main(String[] args) {
        try {
            if (args.length != 3) {
                throw new InvalidArgumentException("Invalid number of arguments");
            }
            int length = Integer.parseInt(args[0]);
            int width = Integer.parseInt(args[1]);
            int nbPlayers = Integer.parseInt(args[2]);
            Game game = new DemeterGame();
            game.initGame(length, width, nbPlayers);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
