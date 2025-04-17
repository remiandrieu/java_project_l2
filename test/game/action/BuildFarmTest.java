package game.action;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;

import game.board.*;
import game.board.util.*;
import game.building.DemeterBuilding;
import game.building.Port;
import game.player.*;
import listchooser.util.Input;

public class BuildFarmTest {
    private static InputStream systemIn;
    private static PrintStream systemOut;
    

    Board board;
    DemeterPlayer player;
    BuildFarm action;

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
        InputStream in = new ByteArrayInputStream((input + "\n").getBytes()); 
        Input.setInputStream(in);
    }

    @BeforeEach
    public void init(){
        board = new Board(10,10);
        try{
            board.createGrid();
        } catch(Exception e){

        }
        player = new DemeterPlayer("Player");
        player.addRessoure(Ressource.WOOD);
        action = new BuildFarm(board);
    }

    @Test
    public void testIsPossible(){
        assertFalse(action.isPossible(player));
        player.addRessoure(Ressource.ORE);
        assertTrue(action.isPossible(player));
    }

    @Test
    public void testBuild() throws IOException {
        player.addRessoure(Ressource.ORE);
        Land land = BoardUtils.firstAvailableLand(board);;
        new Port(player, land);
        
        Coordinates coord = BoardUtils.firstAvailableCoords(board);
        land = BoardUtils.firstAvailableLand(board);;

        assertEquals(1, player.getRessources().get(Ressource.WOOD));
        assertEquals(1, player.getRessources().get(Ressource.ORE));
        assertFalse(land.hasBuilding());

        action.build(player, coord.getX(), coord.getY());

        assertEquals(0, player.getRessources().get(Ressource.WOOD));
        assertEquals(0, player.getRessources().get(Ressource.ORE));
        assertTrue(land.hasBuilding());
        assertTrue(player.getBuildings().get(0) instanceof DemeterBuilding);
    }

    @Test
    public void testAct() throws InvalidPositionException {
        player.addRessoure(Ressource.ORE);
        player.addRessoure(Ressource.WOOD);

        Coordinates coord = BoardUtils.firstAvailableCoords(board);
        String input = coord.getX() + "\n" + coord.getY();
        simulateInput(input);
        action.act(player);
        assertTrue(((Land) board.getTile(coord.getX(), coord.getY())).hasBuilding());
        assertTrue(player.getBuildings().get(0) instanceof DemeterBuilding);
    }

    @Test
    public void testFullBoard(){
        player.addRessoure(Ressource.ORE);
        Land land = BoardUtils.firstAvailableLand(board);;
        while(land != null){
            assertTrue(action.isPossible(player));
            new Port(player, land);
            land = BoardUtils.firstAvailableLand(board);;
               
        }

        assertFalse(action.isPossible(player));        
    }

}
