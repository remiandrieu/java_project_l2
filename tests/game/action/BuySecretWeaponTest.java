package game.action;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;
import game.board.*;
import game.board.util.Ressource;
import game.player.*;
import listchooser.util.Input;

public class BuySecretWeaponTest {
    
    Board board;
    AresPlayer player;
    BuySecretWeapon action;

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
    public void init(){
        board = new Board(10,10);
        player = new AresPlayer("Timoleon");
        action = new BuySecretWeapon(board);
    }

    @Test
    public void testIsPossible(){
        player.addRessoure(Ressource.WOOD);
        assertFalse(action.isPossible(player));
        player.addRessoure(Ressource.ORE);
        assertTrue(action.isPossible(player));
    }

    @Test
    public void testAct(){
        player.addRessoure(Ressource.WOOD);
        player.addRessoure(Ressource.ORE);
        assertEquals(0, player.getNbSecretWeapons());
        action.act(player);
        assertEquals(1, player.getNbSecretWeapons());
    }
}
