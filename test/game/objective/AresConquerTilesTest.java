package game.objective;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.*;

import game.board.*;
import game.building.*;
import game.player.*;

public class AresConquerTilesTest {
    Board board;
    AresPlayer player;
    AresConquerTiles objective;

    @BeforeEach
    public void init(){
        board = new Board(10,10);
        try{
            board.createGrid();
        } catch(Exception e){

        }
        player = new AresPlayer("Timoleon");
        objective = new AresConquerTiles(player, board);
    }

    @Test
    public void testConstructeur(){
        assertEquals(objective.getDescription(), "Conquer "+(board.getLength()+board.getWidth())/2+" tiles");
    }

    @Test
    public void testIsAchieved() throws InvalidPositionException {
        ArrayList<ArrayList<Coordinates>> islands = board.detectIslands();

        for (int i = 0; i < islands.get(0).size() && i<9; i++) {
            Coordinates coord = islands.get(0).get(i);
            Land land = (Land) board.getTile(coord.getX(), coord.getY());
            AresBuilding b = new AresBuilding(player, land, 1);
            player.getBuildings().add(b);
        }


        assertFalse(objective.isAchieved());

        Coordinates coord = islands.get(1).get(0);
        Land land = (Land) board.getTile(coord.getX(), coord.getY());
        AresBuilding b = new AresBuilding(player, land, 1);
        player.getBuildings().add(b);

        assertTrue(objective.isAchieved());
    }
}
