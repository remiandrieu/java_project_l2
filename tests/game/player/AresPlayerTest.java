package game.player;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import game.board.*;
import game.building.*;

class AresPlayerTest {
    private AresPlayer player;
    private Board board;

    @BeforeEach
    void setUp() {
        player = new AresPlayer("Timoleon");
        board = new Board(10, 10);
    }

    @Test
    void testAddAndRemoveSecretWeapon() {
        assertEquals(0, player.getNbSecretWeapons());
        player.addSecretWeapon();
        assertEquals(1, player.getNbSecretWeapons());
        player.removeSecretWeapon();
        assertEquals(0, player.getNbSecretWeapons());
    }

    @Test
    void testAddAndRemoveWarrior() {
        assertEquals(0, player.getNbWarrior());
        player.addWarrior(5);
        assertEquals(5, player.getNbWarrior());
        player.removeWarrior(2);
        assertEquals(3, player.getNbWarrior());
    }

    @Test
    void testIslandsConditionsNoIslands() throws InvalidPositionException {
        Coordinates co = new Coordinates(2, 2);
        board.fillWithSea();
        assertThrows(InvalidPositionException.class, () -> {
            player.islandsConditions(board, co);
        });
    }

    @Test
    void testIslandsConditionsIsFirstBuilding() throws InvalidPositionException {
        board.fillWithSea();
        Coordinates co = new Coordinates(2, 1);
        board.setTile(1, 1, new Forest(1, 1));
        board.setTile(2, 1, new Forest(2, 1));
        board.setTile(3, 1, new Forest(3, 3));

        assertTrue(player.islandsConditions(board, co));
    }

    @Test
    void testIslandsConditionsIsOccupyingIsland() throws InvalidPositionException {
        board.fillWithSea();
        Coordinates co = new Coordinates(2, 1);
        Land land = new Forest(1, 1);
        new AresBuilding(player, land, 2);
        board.setTile(1, 1, land);
        board.setTile(2, 1, new Forest(2, 1));
        board.setTile(3, 1, new Forest(3, 3));

        assertTrue(player.islandsConditions(board, co));
    }

    @Test
    void testIslandsConditionsPortRequirement() throws InvalidPositionException {
        board.fillWithSea();
        Coordinates co = new Coordinates(6, 6);
        Land land1 = new Fields(3, 3);
        Land land2 = new Fields(4, 3);
        Land land3 = new Fields(4, 4);
        new AresBuilding(player, land1, 2);
        new AresBuilding(player, land2, 3);
        board.setTile(3, 3, land1);
        board.setTile(4, 3, land2);
        board.setTile(4, 4, land3);
        board.setTile(6, 6, new Fields(6, 6));
        assertFalse(player.islandsConditions(board, co));
        new Port(player, land3);
        assertTrue(player.islandsConditions(board, co));
    }

    @Test
    void testIslandsConditions2BuildingsRequirement() throws InvalidPositionException {
        board.fillWithSea();
        Coordinates co = new Coordinates(6, 6);
        Land land1 = new Fields(3, 3);
        Land land2 = new Fields(4, 3);
        Land land3 = new Fields(4, 4);
        new Port(player, land3);
        board.setTile(3, 3, land1);
        board.setTile(4, 3, land2);
        board.setTile(4, 4, land3);
        board.setTile(6, 6, new Fields(6, 6));
        assertFalse(player.islandsConditions(board, co));
        new AresBuilding(player, land1, 2);
        assertTrue(player.islandsConditions(board, co));
    }
}