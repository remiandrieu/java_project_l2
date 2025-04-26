package game.action;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import game.board.*;
import game.board.util.Ressource;
import game.player.*;

public class BuyThiefTest {
    
    Board board;
    DemeterPlayer player;
    BuyThief action;

    @BeforeEach
    public void init(){
        board = new Board(10,10);
        player = new DemeterPlayer("Player");
        action = new BuyThief(board);
        player.addRessoure(Ressource.ORE, 1);
        player.addRessoure(Ressource.WOOD, 1);
        player.addRessoure(Ressource.WHEAT, 1);
    }

    @Test
    public void testIsPossible(){
        player.removeRessoure(Ressource.WHEAT);
        assertFalse(action.isPossible(player));
        player.addRessoure(Ressource.WHEAT);
        assertFalse(player.hasThief());
        assertTrue(action.isPossible(player));
    }

    @Test
    public void testAct(){
        assertFalse(player.hasThief());
        action.act(player);
        assertTrue(player.hasThief());
    }
}
