package game.action;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;

import game.board.*;
import game.board.util.Ressource;
import game.building.DemeterBuilding;
import game.building.Port;
import game.player.*;

public class EvolveFarmTest {
    private static InputStream systemIn;
    private static PrintStream systemOut;
    private ByteArrayOutputStream testOut;

    Board board;
    DemeterPlayer player;
    EvolveFarm action;

    @BeforeClass // sauvegarde de System.in et System.out
    public static void changeSystemOut() {
        System.out.println("tests begin");
        systemIn = System.in;
        systemOut = System.out;
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
    }

    @AfterClass // restauration de System.in et System.out
    public static void restoreSystemInOut() throws IOException {
        System.setIn(systemIn);
        System.setOut(systemOut);
        System.out.println("tests end");
    }

    public static void simulateInput(String input) {   
        InputStream in = new ByteArrayInputStream(input.getBytes()); 
        System.setIn(in);
    }

    @BeforeEach
    public void init(){
        board = new Board(10, 10);
        try{
            board.createGrid();
        }catch(Exception e){

        }
        player = new DemeterPlayer("Timoleon");
        player.addRessoure(Ressource.WHEAT);
        player.addRessoure(Ressource.SHEEP);
        player.addRessoure(Ressource.WOOD, 2);
        action = new EvolveFarm(board);
    }

    @Test
    public void testIsPossible(){
        assertFalse(action.isPossible(player));
        Land land = firstAvailableLand(board, 10, 10);
        DemeterBuilding farm = new DemeterBuilding(player, land);
        player.getBuildings().add(farm);
        System.out.println(player.getBuildings());
        assertTrue(action.isPossible(player));
        farm.evolve();
        assertFalse(action.isPossible(player));
    }
/**
    @Test
    public void testEvolveFarm(){
        Land land = firstAvailableLand(board, 10, 10);
        int[] coord = firstAvailableCoord(board, 10,10);
        DemeterBuilding farm = new DemeterBuilding(player, land);
        player.getBuildings().add(farm);
        System.out.println(coord[0]);
        simulateInput(""+coord[0]);
        //action.act(player);
        
    }
    */

    /**
     * Returns the first available land tile on the board i.e. the first tile that isn't a sea and doesn't have a building
     * @param board the board we want the land on
     * @param length the length of the board
     * @param width the width of the board
     * @return the first available land tile on the board
    */
    public static Land firstAvailableLand(Board board, int length, int width) {
        for (int x = 0; x < length; x++) {
            for (int y = 0; y < width; y++) {
                try {
                    Tile tile = board.getTile(x, y);
                    if (tile instanceof Land && !((Land) tile).hasBuilding()) {
                        return (Land) tile;
                    }
                } catch (InvalidPositionException e) {
                }
            }
        }
        return null;
    }
}
