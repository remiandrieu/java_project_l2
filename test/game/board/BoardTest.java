package game.board;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

public class BoardTest {
    
    private Board board = new Board(5, 6);
    
    @Test
    void testBoardToString() {

    }

    @Test
    void testCreateGrid() {

    }

    @Test
    void testGetNeighbourTiles() {
        Tile forest = new Forest();
        Tile sea = new Sea();
        Tile sea2 = new Sea();
        Tile sea3 = new Sea();
        try {
            board.setTile(0, 1, forest);
            board.setTile(1, 0, sea);
            board.setTile(2, 1, sea2);
            board.setTile(1, 2, sea3);
            Tile[] neighbours = board.getNeighbourTiles(0, 0);
            assertNull(neighbours[0]);
            assertSame(forest, neighbours[1]);
            assertSame(sea, neighbours[2]);
            assertNull(neighbours[3]);
            Tile[] neighbours2 = board.getNeighbourTiles(1, 1);
            assertSame(forest, neighbours2[0]);
            assertSame(sea3, neighbours2[1]);
            assertSame(sea2, neighbours2[2]);
            assertSame(sea, neighbours2[3]);

        } catch (InvalidPositionException e) {
            fail();
        };
    }

    @Test
    void testGetNeighbourError() {
        assertThrows(InvalidPositionException.class , ()->{
            board.getNeighbourTiles(-1, 0);
        });
        assertThrows(InvalidPositionException.class , ()->{
            board.getNeighbourTiles(0, 7);
        });
    }

    @Test
    void testSetTile() throws InvalidPositionException {
        Tile forest = new Forest();
        try {
            board.setTile(2, 3, forest);
        } catch (InvalidPositionException e) {
            fail();
        };
        assertSame(forest, board.getTile(2, 3));
    }

    @Test
    void testSetTileError() {
        Tile forest = new Forest();
        assertThrows(InvalidPositionException.class , ()->{
            board.setTile(-1, 2, forest);
        });
        assertThrows(InvalidPositionException.class , ()->{
            board.setTile(6, 2, forest);
        });
    }
}
