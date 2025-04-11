package game;

import static org.junit.jupiter.api.Assertions.*;
import java.io.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;

public class DemeterGameTest {
    private static InputStream systemIn;
    private static PrintStream systemOut;

    Game game;

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
        game = new DemeterGame();
    }

    @Test
    public void initPlayersTest(){
        /* With this simulateInput, testing :
         * - at least 2 players
         * - player can't have an empty name
         * - player can't have the same name as an other player
         */
        simulateInput("-1\n1\n2\n\nabc\nabc\ndef");
        this.game.initPlayers();
        assertEquals(2, this.game.getPlayers().size());
        assertEquals("abc", this.game.getPlayers().get(0).getName());
        assertEquals("def", this.game.getPlayers().get(1).getName());
    }

}
