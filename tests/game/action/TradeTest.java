package game.action;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;

import game.board.Board;
import game.board.util.Ressource;
import game.player.Player;

public class TradeTest {

    private Player player = new Player("Timoleon");
    private Board board = new Board(10, 10);
    private Trade trade = new Trade(board);

    @Test
    void testSpend() {
      player.addRessource(Ressource.ORE);
      player.addRessource(Ressource.ORE);
      player.addRessource(Ressource.ORE);
      player.addRessource(Ressource.ORE);
      player.addRessource(Ressource.ORE);
      player.addRessource(Ressource.ORE);
      assertSame(6, player.getRessources().get(Ressource.ORE));
      trade.spend(player, Ressource.ORE);
      assertSame(3, player.getRessources().get(Ressource.ORE));
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
        player.addRessource(Ressource.ORE);
        player.addRessource(Ressource.ORE);
        assertEquals(0, trade.availableRessources(player).size());
        player.addRessource(Ressource.ORE);
        player.addRessource(Ressource.SHEEP);
        player.addRessource(Ressource.SHEEP);
        assertEquals(1, trade.availableRessources(player).size());
        assertEquals(Ressource.ORE, trade.availableRessources(player).get(0));
    }

    @Test
    void testIsPossible() {
        player.addRessource(Ressource.ORE);
        player.addRessource(Ressource.ORE);
        player.addRessource(Ressource.SHEEP);
        player.addRessource(Ressource.SHEEP);
        assertFalse(trade.isPossible(player));
        player.addRessource(Ressource.SHEEP);
        assertTrue(trade.isPossible(player));
    }
}
