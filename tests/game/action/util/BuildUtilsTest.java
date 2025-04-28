package game.action.util;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;

import game.board.*;
import game.building.AresBuilding;
import game.player.Player;
import listchooser.util.Input;

public class BuildUtilsTest {
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
    public void buildingsThatCanEvolve(){
        Board board = new Board(10, 10);
        try {
            board.fillWithSea();
            Player player = new Player("timo");
            board.setTile(0, 0, new Forest(0, 0));
            Land land = new Fields(0, 1);
            board.setTile(0, 1, land);
            new AresBuilding(player, land, 1);
        } catch (InvalidPositionException e) {
        }

        simulateInput("1\ntimo\n1\n2\n0\n1\n0\n0");
        Coordinates coord = BuildUtils.askCoordinates(board);
        assertTrue(coord.equals(new Coordinates(0, 0)));
    }
}
