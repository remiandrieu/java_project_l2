package game.action;

import static org.junit.jupiter.api.Assertions.*;
import java.io.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;

import game.board.*;
import game.board.util.*;
import game.building.AresBuilding;
import game.building.Port;
import game.player.*;

public class BuildArmyTest {
    private static InputStream systemIn;
    private static PrintStream systemOut;

    Board board;
    AresPlayer player;
    BuildArmy action;

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
        board = new Board(10,10);
        try{
            board.createGrid();
        } catch(Exception e){
            
        }
        player = new AresPlayer("Player");
        player.addRessource(Ressource.WOOD);
        player.addRessource(Ressource.SHEEP);
        action = new BuildArmy(board);
    }

    @Test
    public void testIsPossible(){
        assertFalse(action.isPossible(player));
        player.addRessource(Ressource.WHEAT);
        assertFalse(action.isPossible(player));
        player.removeRessource(Ressource.WHEAT);
        player.addWarrior(1);
        assertFalse(action.isPossible(player));
        player.addRessource(Ressource.WHEAT);
        assertTrue(action.isPossible(player));
    }

    @Test
    public void testBuild() throws IOException {
        player.addRessource(Ressource.WHEAT);
        player.addWarrior(10);
        Land land = BoardUtils.firstAvailableLand(board);
        new Port(player, land);
        
        land = BoardUtils.firstAvailableLand(board);

        assertEquals(1, player.getRessources().get(Ressource.WHEAT));
        assertEquals(1, player.getRessources().get(Ressource.WOOD));
        assertEquals(1, player.getRessources().get(Ressource.SHEEP));
        assertFalse(land.hasBuilding());

        action.build(player, land.getCoordinates().getX(), land.getCoordinates().getY(), 4);

        assertEquals(0, player.getRessources().get(Ressource.WHEAT));
        assertEquals(0, player.getRessources().get(Ressource.WOOD));
        assertEquals(0, player.getRessources().get(Ressource.SHEEP));
        assertTrue(land.hasBuilding());
    }

    @Test
    public void testFullBoard(){
        player.addWarrior(10);
        player.addRessource(Ressource.WHEAT);
        Land land = BoardUtils.firstAvailableLand(board);
        while(land != null){
            assertTrue(action.isPossible(player));
            new Port(player, land);
            land = BoardUtils.firstAvailableLand(board);
        }

        assertFalse(action.isPossible(player));        
    }

    @Test
    public void testAct() throws InvalidPositionException {
        player.addRessource(Ressource.ORE);
        player.addRessource(Ressource.WOOD);
        player.addWarrior(10);

        Coordinates coord = BoardUtils.firstAvailableCoords(board);
        String input = coord.getX() + "\n" + coord.getY() + "\n3";
        simulateInput(input);
        action.act(player);
        assertTrue(((Land) board.getTile(coord.getX(), coord.getY())).hasBuilding());
        assertTrue(player.getBuildings().get(0) instanceof AresBuilding);
    }

}
