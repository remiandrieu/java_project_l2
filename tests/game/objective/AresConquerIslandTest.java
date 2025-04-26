package game.objective;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.*;

import game.board.*;
import game.building.*;
import game.player.*;

public class AresConquerIslandTest {
    Board board;
    AresPlayer player;
    AresConquerIsland objective;

    @BeforeEach
    public void init(){
        board = new Board(10,10);
        try{
            board.createGrid();
        } catch(Exception e){

        }
        player = new AresPlayer("Timoleon");
        objective = new AresConquerIsland(player, board);
    }

    @Test
    public void testConstructeur(){
        assertEquals(objective.getDescription(), "Conquer an entire island (Have a building on every tile of any island)");
    }

    @Test
    public void testIsAchieved() throws InvalidPositionException {
        ArrayList<Coordinates> island = board.detectIslands().get(0);

        for (int i = 0; i < island.size() - 1; i++) {
            Coordinates coord = island.get(i);
            Land land = (Land) board.getTile(coord.getX(), coord.getY());
            AresBuilding b = new AresBuilding(player, land, 1);
            player.getBuildings().add(b);
        }

        assertFalse(objective.isAchieved());

        Coordinates coord = island.get(island.size() - 1);
        Land land = (Land) board.getTile(coord.getX(), coord.getY());
        AresBuilding b = new AresBuilding(player, land, 1);
        player.getBuildings().add(b);

        assertTrue(objective.isAchieved());
    }
}