package game.board;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

public class BoardTest {
    
    private final int LENGTH = 12;
    private final int WIDTH = 15;
    private Board board = new Board(LENGTH, WIDTH);

    
    @Test
    void testGetRandomTypeOfLand(){
        Land randomLand = this.board.getRandomTypeOfLand(0, 0);
        for(int i =0 ; i<100 ; i++){
            assertTrue(randomLand instanceof Forest || randomLand instanceof Pasture || randomLand instanceof Mountain || randomLand instanceof Fields);
        }
    }

    @Test
    void testGetRandomCoordinatesNeighbour(){
        for (int i = 0; i < 100; i++){
            int x = 4;
            int y = 5;
            
            int[] neighbour = this.board.getRandomCoordinatesNeighbour(x, y);
            int neighbourX = neighbour[0];
            int neighbourY = neighbour[1];
    
            assertTrue((neighbourX == 4 && neighbourY == 6) 
                        || (neighbourX == 4 && neighbourY == 4)
                        || (neighbourX == 5 && neighbourY == 5)
                        || (neighbourX == 3 && neighbourY == 5));
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
        Tile forest = new Forest(0, 1);
        Tile sea = new Sea(1, 0);
        Tile sea2 = new Sea(2, 1);
        Tile sea3 = new Sea(1, 2);
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
        Tile forest = new Forest(2, 3);
        try {
            this.board.setTile(2, 3, forest);
        } catch (InvalidPositionException e) {
            fail();
        };
        assertSame(forest, board.getTile(2, 3));
    }

    @Test
    void testSetTileError() {
        Tile forest = new Forest(0, 0);
        assertThrows(InvalidPositionException.class , ()->{
            this.board.setTile(-1, 2, forest);
        });
        assertThrows(InvalidPositionException.class , ()->{
            this.board.setTile(LENGTH, 2, forest);
        });
    }

    @Test
    void testFillWithSea() throws InvalidPositionException{
        this.board.fillWithSea();
        for(int x = 0 ; x < LENGTH ; x++){
            for(int y = 0 ; y < WIDTH ; y++){
                assertTrue(this.board.getTile(x, y) instanceof Sea);
            }
        }
    }

    @Test
    void testLandNeighbour() throws InvalidPositionException{
        this.board.fillWithSea();
        assertFalse(this.board.landNeighbour(0, 0)[0]);
        assertFalse(this.board.landNeighbour(0, 0)[1]);
        
        Tile forest = new Forest(0, 0);
        this.board.setTile(0, 1, forest);
        assertTrue(this.board.landNeighbour(0, 0)[0]);
        assertFalse(this.board.landNeighbour(0, 0)[1]);

        this.board.setTile(1, 0, forest);
        assertTrue(this.board.landNeighbour(0, 0)[0]);
        assertTrue(this.board.landNeighbour(0, 0)[1]);
    }

    @Test
    void testRandomSeaNeighbour() throws InvalidPositionException{
        this.board.fillWithSea();
        Tile forest = new Forest(0, 0);
        this.board.setTile(0, 1, forest);
        this.board.setTile(1, 0, forest);
        for (int i = 0; i < 100; i++){
            int[] coordinates = this.board.randomSeaNeighbour(1, 1);
            assertTrue(this.board.getTile(coordinates[0], coordinates[1]) instanceof Sea);
            boolean found = false;
            for(Tile tile : this.board.getNeighbourTiles(1, 1)){
                if(this.board.getTile(coordinates[0], coordinates[1]) == tile){
                    found = true;
                }
            }
            assertTrue(found);
        }
    }
}
