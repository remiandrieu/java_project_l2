package game.action;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import game.board.*;
import game.board.util.Ressource;
import game.building.Building;
import game.player.DemeterPlayer;
import listchooser.util.Input;

public class Livrable3demeter {

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
            System.out.println("Livrable3demeter.java <length> <width>");
            System.out.println("<length> : length of the board >= 10");
            System.out.println("<width> : width of the board >= 10");
            return;
        }

        storeSystemIn();

        Board board = new Board(length, width);
        board.createGrid();
        
        DemeterPlayer player = new DemeterPlayer("Timoleon");
        player.addRessoure(Ressource.WHEAT, 10);
        player.addRessoure(Ressource.SHEEP, 10);
        player.addRessoure(Ressource.WOOD, 10);
        player.addRessoure(Ressource.ORE, 10);

        BuildFarm a1 = new BuildFarm(board);
        Coordinates co = firstAvailableCoord(board, false);
        String inputString = co.getX() + "\n" + co.getY();
        simulateInput(inputString);
        a1.act(player);

        EvolveFarm a2 = new EvolveFarm(board);
        simulateInput(inputString);
        a2.act(player);

        BuildPort a3 = new BuildPort(board);
        co = firstAvailableCoord(board, true);
        inputString = co.getX() + "\n" + co.getY();
        simulateInput(inputString);
        a3.act(player);

        Trade a4 = new Trade(board);
        inputString = "0\n1";
        simulateInput(inputString);
        a4.act(player);

        TradePort a5 = new TradePort(board);
        inputString = "2\n3";
        simulateInput(inputString);
        a5.act(player);

        BuyThief a6 = new BuyThief(board);
        a6.act(player);
        
        System.out.println("Player ressources : " + player);
        System.out.println("Player buildings : " + player.getBuildings());
        List<Coordinates> coords = new ArrayList<>();
        for (Building b : player.getBuildings())
            coords.add(b.getLand().getCoordinates());
        System.out.println("Player occupied tiles : " + coords);
        System.out.println(board.boardToString(true));


        restoreSystemIn();

    }

    /**
     * Returns the first available land tile on the board i.e. the first tile that isn't a sea and doesn't have a building
     * @param board the board we want the land on
     * @param length the length of the board
     * @param width the width of the board
     * @return the first available land tile on the board
    */
    public static Coordinates firstAvailableCoord(Board board, boolean needsSeaNeighbour){
        int x = 0;
        int y = -1;
        boolean stop = false;
        Tile tile;
        Land land;
        while (!stop && x < board.getLength()) {
            y += 1;
            if (y == board.getWidth()){
                y = 0;
                x += 1;
            }
            try {
                tile = board.getTile(x, y);
                if (!(tile instanceof Sea)){
                    land = (Land) tile;
                    if (!land.hasBuilding()){
                        if (!needsSeaNeighbour || !board.landNeighbour(x, y)[1])
                            stop = true;
                    }
                }
            } catch (InvalidPositionException e) {}
        }
        return new Coordinates(x, y);
    }
}
