package game.action;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import game.board.Board;
import game.board.Fields;
import game.board.InvalidPositionException;
import game.board.Land;
import game.board.util.Ressource;
import game.building.Port;
import game.player.Player;
import listchooser.util.Input;

public class TradePortTest {

    private Player player = new Player("Timoleon");
    private Board board = new Board(10, 10);
    private TradePort trade = new TradePort(board);

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

    @Test
    void testSpend() {
      player.addRessoure(Ressource.ORE);
      player.addRessoure(Ressource.ORE);
      player.addRessoure(Ressource.ORE);
      player.addRessoure(Ressource.ORE);
      assertSame(4, player.getRessources().get(Ressource.ORE));
      trade.spend(player, Ressource.ORE);
      assertSame(2, player.getRessources().get(Ressource.ORE));
      trade.spend(player, Ressource.ORE);
      assertSame(0, player.getRessources().get(Ressource.ORE));
    }
    
    @Test
    void testBuy() {
      assertSame(0, player.getRessources().get(Ressource.WOOD));
      trade.buy(player, Ressource.WOOD);
      assertSame(1, player.getRessources().get(Ressource.WOOD));
      trade.buy(player, Ressource.WOOD);
      assertSame(2, player.getRessources().get(Ressource.WOOD));
    }

    @Test
    void testAvailableRessources() {
        player.addRessoure(Ressource.ORE);
        assertEquals(0, trade.availableRessources(player).size());
        player.addRessoure(Ressource.ORE);
        player.addRessoure(Ressource.SHEEP);
        assertEquals(1, trade.availableRessources(player).size());
        assertEquals(Ressource.ORE, trade.availableRessources(player).get(0));
    }

    @Test
    void testIsPossible() throws InvalidPositionException {
        player.addRessoure(Ressource.ORE);
        player.addRessoure(Ressource.SHEEP);
        assertFalse(trade.isPossible(player));
        player.addRessoure(Ressource.SHEEP);
        assertFalse(trade.isPossible(player));
        board.fillWithSea();
        Land land = new Fields(0, 0);
        player.getBuildings().add(new Port(player, land));
        board.setTile(0, 0, land);
        assertTrue(trade.isPossible(player));
    }

    @Test
    void actTest() throws InvalidPositionException {
      player.addRessoure(Ressource.SHEEP, 2);
      
      board.fillWithSea();
      Land land = new Fields(0, 0);
      player.getBuildings().add(new Port(player, land));
      board.setTile(0, 0, land);
      
      simulateInput("0\n2");
      trade.act(player);
      assertSame(0, player.getRessources().get(Ressource.SHEEP));
      assertSame(1, player.getRessources().get(Ressource.ORE));
    }

}
