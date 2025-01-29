package game.board;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

public class BoardTest {
    
    private final int LENGTH = 12;
    private final int WIDTH = 15;
    private Board board = new Board(LENGTH, WIDTH);

    
    @Test
    void testGetRandomTypeOfLand(){
        Land randomLand = this.board.getRandomTypeOfLand();
        for(int i =0 ; i<100 ; i++){
            assertTrue(randomLand instanceof Forest || randomLand instanceof Pasture || randomLand instanceof Mountain || randomLand instanceof Fields);
        }

    }
    
    @Test
    void testCreateGrid() throws InvalidPositionException {
        for(int i = 0 ; i < 100; i++){
            Board board2 = new Board(LENGTH, WIDTH);
            board2.createGrid();
            int nbSea = 0;
            for(int x = 0 ; x < LENGTH ; x++){
                for(int y = 0 ; y < WIDTH ; y++){
                    if(board2.getTile(x, y) instanceof Sea){
                        nbSea++;
                    } 
                    else {
                        boolean hasLandNeighbour = false;
                        for(Tile tile : board2.getNeighbourTiles(x, y)){
                            if(tile instanceof Land){
                                hasLandNeighbour = true;
                            }
                        }
                        assertTrue(hasLandNeighbour);
                    }
                }
            }
            assertTrue(nbSea >= LENGTH*WIDTH * 2/3);
            }
    }

    @Test
    void testGetNeighbourTiles() {
        Tile forest = new Forest();
        Tile sea = new Sea();
        Tile sea2 = new Sea();
        Tile sea3 = new Sea();
        try {
            this.board.setTile(0, 1, forest);
            this.board.setTile(1, 0, sea);
            this.board.setTile(2, 1, sea2);
            this.board.setTile(1, 2, sea3);
            Tile[] neighbours = this.board.getNeighbourTiles(0, 0);
            assertNull(neighbours[0]);
            assertSame(forest, neighbours[1]);
            assertSame(sea, neighbours[2]);
            assertNull(neighbours[3]);
            Tile[] neighbours2 = this.board.getNeighbourTiles(1, 1);
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
            this.board.getNeighbourTiles(-1, 0);
        });
        assertThrows(InvalidPositionException.class , ()->{
            this.board.getNeighbourTiles(0, WIDTH);
        });
    }

    @Test
    void testSetTile() throws InvalidPositionException {
        Tile forest = new Forest();
        try {
            this.board.setTile(2, 3, forest);
        } catch (InvalidPositionException e) {
            fail();
        };
        assertSame(forest, board.getTile(2, 3));
    }

    @Test
    void testSetTileError() {
        Tile forest = new Forest();
        assertThrows(InvalidPositionException.class , ()->{
            this.board.setTile(-1, 2, forest);
        });
        assertThrows(InvalidPositionException.class , ()->{
            this.board.setTile(LENGTH, 2, forest);
        });
    }
}
