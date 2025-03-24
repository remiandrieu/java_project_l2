package game.action;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import game.board.*;
import game.board.util.Ressource;
import game.player.*;

public class PlayThiefTest {
    
    Board board;
    DemeterPlayer thief;
    DemeterPlayer victim1;
    DemeterPlayer victim2;
    PlayThief action;

    @BeforeEach
    public void init(){
        board = new Board(10,10);
        thief = new DemeterPlayer("Thief");
        victim1 = new DemeterPlayer("Victim1");
        victim2 = new DemeterPlayer("Victim2");
        action = new PlayThief(board);
        victim1.addRessoure(Ressource.WOOD, 3);
        victim2.addRessoure(Ressource.WOOD, 2);
        thief.addThief();
    }

    @Test
    public void testIsPossible(){
        assertTrue(action.isPossible(thief));
        thief.removeThief();
        assertFalse(action.isPossible(thief));
    }

    @Test
    public void testSteal(){
        Player[] victims = {victim1, victim2};
        action.steal(victims, thief, Ressource.WOOD);
        assertEquals(0, victim1.getRessources().get(Ressource.WOOD));
        assertEquals(0, victim2.getRessources().get(Ressource.WOOD));
        assertEquals(5, thief.getRessources().get(Ressource.WOOD));
    }
}
