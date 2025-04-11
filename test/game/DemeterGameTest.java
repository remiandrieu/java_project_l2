package game;

import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import listchooser.util.Input;

import org.junit.jupiter.api.*;

public class DemeterGameTest {
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
        this.game = new DemeterGame();
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
        assertEquals("abc", this.game.getPlayers().get(0).getName());
        assertEquals("def", this.game.getPlayers().get(1).getName());
    }

    @Test
    public void initBoardTest(){
        simulateInput("2\nabc\ndef");
        this.game.initPlayers();
        this.game.initBoard();
        assertEquals(10, this.game.getBoard().getLength());
        assertEquals(10, this.game.getBoard().getWidth());
    }
}