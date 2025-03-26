package game.action;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;

import game.board.*;
import game.building.AresBuilding;
import game.player.*;
import listchooser.util.Input;

public class PlaceWarriorTest {
    Board board;
    AresPlayer player;
    AresBuilding aresBuilding;
    PlaceWarrior placeWarrior;

    private static InputStream systemIn;

    @BeforeClass
    public static void storeSystemIn() {
      systemIn = System.in;
    }

    @AfterClass
    public static void restoreSystemIn() throws IOException {
      System.setIn(systemIn);
    }

    public static void simulateInput(String input) {   
        InputStream in = new ByteArrayInputStream((input + "\n").getBytes()); 
        Input.setInputStream(in);
    }

    @BeforeEach
    public void setUp() {
        board = new Board(10, 10);
        try {
            board.fillWithSea();
        } catch (InvalidPositionException e) {
        }
        player = new AresPlayer("timoleon");
        placeWarrior = new PlaceWarrior(board);
        Land land = new Forest(5, 5);
        try {
            board.setTile(5, 5, land);
        } catch (InvalidPositionException e) {
        }
        aresBuilding = new AresBuilding(this.player, land, 2);
        player.getBuildings().add(this.aresBuilding);
        player.addWarrior(5);
    }

    @Test
    public void testSuccessfulWarriorPlacement() {
        assertTrue(placeWarrior.isPossible(player));
        simulateInput("5\n5\n3");
        placeWarrior.act(this.player);
        assertEquals(2, this.player.getNbWarrior());
        assertEquals(5, this.aresBuilding.getDimension());
    }

    @Test
    public void testIsPossibleNoAresBuildings() {
        player.getBuildings().clear();
        assertFalse(placeWarrior.isPossible(player));
    }

    @Test
    public void testIspossibleNoWarriors() {
        player.removeWarrior(player.getNbWarrior());        
        assertFalse(placeWarrior.isPossible(player));
    }

}