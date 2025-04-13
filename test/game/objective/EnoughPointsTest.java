package game.objective;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import game.board.*;
import game.player.*;

public class EnoughPointsTest {
    Board board;
    DemeterPlayer player;
    EnoughPoints objective;

    @BeforeEach
    public void init() {
        board = new Board(10, 10);
        try {
            board.createGrid();
        } catch (Exception e) {
        }
        player = new DemeterPlayer("Timoleon");
        objective = new EnoughPoints(player, board);
    }

    @Test
    public void testConstructeur() {
        assertEquals(objective.getDescription(), "You need to get at least 12 points.");
    }
}