package game.action.util;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;

import game.board.*;
import game.building.AresBuilding;
import game.player.Player;
import listchooser.util.Input;

public class EvolveUtilsTest {
    private static InputStream systemIn;
    private static PrintStream systemOut;

    @BeforeClass // sauvegarde de System.in et System.out
    public static void storeSystemIn() {
        systemIn = System.in;
        systemOut = System.out;
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
    }

    @AfterClass
    public static void restoreSystemIn() throws IOException {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    public static void simulateInput(String input) {   
        InputStream in = new ByteArrayInputStream((input + "\n").getBytes()); 
        Input.setInputStream(in);
    }

    @Test
    public void testBuildingsThatCanEvolve(){
        Board board = new Board(10, 10);
        try {
            Player player = new Player("timo");
            Land forest = new Forest(0,0);
            board.setTile(0, 0, forest);
            Land field = new Fields(0, 1);
            board.setTile(0, 1, field);
            AresBuilding building1 = new AresBuilding(player, field, 1);
            AresBuilding building2 = new AresBuilding(player, forest, 1);
            building2.evolve();
            player.getBuildings().add(building1);
            player.getBuildings().add(building2);
            ArrayList<Coordinates> coords = EvolveUtils.buildingsThatCanEvolve(player);
            assertTrue(coords.size()==1);
            assertEquals(new Coordinates(0, 1),coords.get(0));
        } catch (InvalidPositionException e) {
        }
    }

    @Test
    public void testAct(){
        Board board = new Board(10, 10);
        try {
            Player player = new Player("timo");
            Land forest = new Forest(0,0);
            board.setTile(0, 0, forest);
            Land field = new Fields(0, 1);
            board.setTile(0, 1, field);
            AresBuilding building1 = new AresBuilding(player, field, 1);
            AresBuilding building2 = new AresBuilding(player, forest, 1);
            building2.evolve();
            player.getBuildings().add(building1);
            player.getBuildings().add(building2);
            
            simulateInput("1\ntimo\n1\n2\n0\n0\n0\n1");
            EvolveUtils.act(player, board);
            assertTrue(building1.isEvolved());
        } catch (InvalidPositionException e) {
        }
    }
}
