package game.action;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import game.board.*;
import game.board.util.Ressource;
import game.player.*;

public class BuyWarriorsTest {
    Board board;
    AresPlayer player;
    BuyWarriors action;

    @BeforeEach
    public void init(){
        board = new Board(10,10);
        player = new AresPlayer("Player");
        player.addRessoure(Ressource.WHEAT, 2);
        player.addRessoure(Ressource.SHEEP, 2);
        player.addRessoure(Ressource.ORE);
        action = new BuyWarriors(board);
    }

    @Test
    public void testIsPossible(){
        player.removeRessoure(Ressource.WHEAT);
        assertFalse(action.isPossible(player));
        player.addRessoure(Ressource.WHEAT);
        assertTrue(action.isPossible(player));
    }

    @Test
    public void testAct(){
        assertEquals(0, player.getNbWarrior());
        action.act(player);
        assertEquals(5, player.getNbWarrior());
    }
}
