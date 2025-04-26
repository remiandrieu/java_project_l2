package game.action;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import game.board.*;
import game.board.util.BoardUtils;
import game.board.util.Ressource;
import game.building.Building;
import game.player.AresPlayer;
import listchooser.util.Input;

public class Livrable3ares {

    private static InputStream systemIn;

    public static void storeSystemIn() {
      systemIn = System.in;
    }

    public static void restoreSystemIn() throws IOException {
      System.setIn(systemIn);
    }

    public static void simulateInput(String input) {   
        InputStream in = new ByteArrayInputStream((input + "\n").getBytes()); 
        Input.setInputStream(in);
    }
    
    public static void main(String[] args) throws InvalidPositionException , IOException{

        int length = 1;
        int width = 1;
        if (args.length == 2){
            length = Integer.parseInt(args[0]);
            width = Integer.parseInt(args[1]);
        }
        if (args.length != 2 || length < 10 || width < 10){
            System.out.println("How to use :");
            System.out.println("Livrable3ares.java <length> <width>");
            System.out.println("<length> : length of the board >= 10");
            System.out.println("<width> : width of the board >= 10");
            return;
        }

        storeSystemIn();

        Board board = new Board(length, width);
        board.createGrid();
        
        AresPlayer player = new AresPlayer("Timoleon");
        player.addRessource(Ressource.WHEAT, 10);
        player.addRessource(Ressource.SHEEP, 10);
        player.addRessource(Ressource.WOOD, 10);
        player.addRessource(Ressource.ORE, 10);
        player.addWarrior(30);

        BuildArmy a1 = new BuildArmy(board);
        Coordinates co = BoardUtils.firstAvailableCoord(board, false, player);
        String inputString = co.getX() + "\n" + co.getY();
        simulateInput(inputString + "\n3");
        a1.act(player);
        
        PlaceWarrior a2 = new PlaceWarrior(board);
        simulateInput(inputString + "\n1");
        a2.act(player);
        simulateInput(inputString + "\n1");
        a2.act(player);
        
        EvolveArmy a3 = new EvolveArmy(board);
        simulateInput(inputString);
        a3.act(player);

        BuyWarriors a4 = new BuyWarriors(board);
        a4.act(player);

        BuildPort a5 = new BuildPort(board);
        co = BoardUtils.firstAvailableCoord(board, true, player);
        inputString = co.getX() + "\n" + co.getY();
        simulateInput(inputString);
        a5.act(player);

        Trade a6 = new Trade(board);
        inputString = "0\n1";
        simulateInput(inputString);
        a6.act(player);

        BuySecretWeapon a7 = new BuySecretWeapon(board);
        a7.act(player);
        
        System.out.println("Player ressources : " + player);
        System.out.println("Player buildings : " + player.getBuildings());
        List<Coordinates> coords = new ArrayList<>();
        for (Building b : player.getBuildings())
            coords.add(b.getLand().getCoordinates());
        System.out.println("Player occupied tiles : " + coords);
        System.out.println(board.boardToString(true));


        restoreSystemIn();

    }

}
