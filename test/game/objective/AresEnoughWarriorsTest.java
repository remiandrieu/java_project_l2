package game.objective;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.*;

import game.board.*;
import game.building.*;
import game.player.*;

public class AresEnoughWarriorsTest {
    Board board;
    AresPlayer player;
    AresEnoughWarriors objective;

    @BeforeEach
    public void init(){
        board = new Board(10,10);
        try{
            board.createGrid();
        } catch(Exception e){

        }
        player = new AresPlayer("Timoleon");
        objective = new AresEnoughWarriors(player, board);
    }

    @Test
    public void testConstructeur(){
        assertEquals(objective.getDescription(), "Have "+ (board.getLength() + board.getWidth()+20)+" warriors in buildings");
    }

    @Test
    public void testIsAchieved() throws InvalidPositionException {
        ArrayList<Coordinates> island = board.detectIslands().get(0);
        Coordinates coord = island.get(0);
        Land land = (Land) board.getTile(coord.getX(), coord.getY());
        AresBuilding b = new AresBuilding(player, land, 39);
        player.getBuildings().add(b);

        assertFalse(objective.isAchieved());

        Coordinates coord2 = island.get(1);
        Land land2 = (Land) board.getTile(coord2.getX(), coord2.getY());
        AresBuilding b2 = new AresBuilding(player, land2, 1);
        player.getBuildings().add(b2);
        assertTrue(objective.isAchieved());
    }
}