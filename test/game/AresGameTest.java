package game;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.List;

import listchooser.util.Input;

import game.board.*;
import game.board.util.*;
import game.building.*;
import game.player.AresPlayer;
import game.action.*;

public class AresGameTest {
    private static InputStream originalSystemIn;
    private static PrintStream originalSystemOut;

    Game game;

    @BeforeAll
    public static void saveOriginalStreams() {
        originalSystemIn = System.in;
        originalSystemOut = System.out;
        System.out.println("Tests begin");
    }

    @AfterAll
    public static void restoreOriginalStreams() {
        System.setIn(originalSystemIn);
        System.setOut(originalSystemOut);
        System.out.println("Tests end");
    }

    @BeforeEach
    public void init() {
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
        this.game = new AresGame();
    }

    public void simulateInput(String input) {   
        InputStream in = new ByteArrayInputStream(input.getBytes()); 
        System.setIn(in);
        Input.setInputStream(in);
    }

    @Test
    public void initPlayersTest(){
        simulateInput("-1\n1\n2\n\nabc\nabc\ndef");
        this.game.initPlayers();
        assertEquals(2, this.game.getPlayers().size());
        AresPlayer player1 = (AresPlayer) this.game.getPlayers().get(0);
        assertEquals("abc", player1.getName());
        assertEquals(30, player1.getNbWarrior());
        AresPlayer player2 = (AresPlayer) this.game.getPlayers().get(1);
        assertEquals("def", player2.getName());
        assertEquals(30, player2.getNbWarrior());
    }

    @Test
    public void initBoardTest(){
        simulateInput("2\nabc\ndef");
        this.game.initPlayers();
        this.game.initBoard();
        assertEquals(10, this.game.getBoard().getLength());
        assertEquals(10, this.game.getBoard().getWidth());
    }

    @Test
    public void placeFirstBuildingsTest() throws InvalidPositionException{
        simulateInput("2\nabc\ndef");
        this.game.initPlayers();
        this.game.initBoard();
        List<Coordinates> coords = BoardUtils.nFirstAvailableCoords(this.game.getBoard(), 4);
        String inputString = "";
        for (Coordinates co : coords){
            inputString += co.getX() + "\n" + co.getY() + "\n";
        }
        simulateInput(inputString);
        this.game.placeFirstBuildings();
        for (Building b : this.game.getPlayers().get(0).getBuildings()){
            assertTrue(b instanceof AresBuilding);
        }
        for (Building b : this.game.getPlayers().get(1).getBuildings()){
            assertTrue(b instanceof AresBuilding);
        }
    }

    @Test
    public void initActionsTest() throws InvalidPositionException{
        this.game.initActions();
        for (Action ac : this.game.getActions()){
            assertFalse(ac instanceof DemeterAction);
        }
    }
}