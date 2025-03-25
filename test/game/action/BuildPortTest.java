package game.action;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;


import org.junit.jupiter.api.*;

import game.board.Board;
import game.board.InvalidPositionException;
import game.board.Land;
import game.board.Sea;
import game.board.Tile;
import game.board.util.Ressource;
import game.building.DemeterBuilding;
import game.building.Port;
import game.player.Player;

public class BuildPortTest {

    private Player player;
    private Board board;
    private BuildPort buildPort;
    
    @BeforeEach
    void setUp() {
        player = new Player("Timoleon");
        board = new Board(10, 10);
        buildPort = new BuildPort(board);
    }
    
    @Test
    void testIsPossibleWithNoLandTiles() {
        player.addRessoure(Ressource.WOOD, 1);
        player.addRessoure(Ressource.SHEEP, 2);
        
        Board seaBoard = new Board(2, 2);
        try {
            // Make all tiles Sea
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    seaBoard.setTile(i, j, new Sea(i, j));
                }
            }
            BuildPort seaBuildPort = new BuildPort(seaBoard);
            assertFalse(seaBuildPort.isPossible(player));
        } catch (InvalidPositionException e) {
            // Ignore exceptions for test
        }
    }
    
    @Test
    void testIsPossibleWithAllLandTilesOccupied() {
        player.addRessoure(Ressource.WOOD, 1);
        player.addRessoure(Ressource.SHEEP, 2);
        
        Board smallBoard = new Board(3, 3);
        try {
            smallBoard.createGrid();
        } catch (InvalidPositionException e) {
        }
        
        try {
            // Occupy all land tiles with buildings
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (smallBoard.getTile(i, j) instanceof Land) {
                        player.getBuildings().add(new DemeterBuilding(player, (Land) smallBoard.getTile(i, j)));
                    }
                }
            }
            BuildPort occupiedBuildPort = new BuildPort(smallBoard);
            assertFalse(occupiedBuildPort.isPossible(player));
        } catch (InvalidPositionException e) {
            // Ignore exceptions for test
        }
    }
    
    @Test
    void testIsPossibleWithAvailableLand() {
        player.addRessoure(Ressource.WOOD, 1);
        player.addRessoure(Ressource.SHEEP, 2);
        
        try {
            board.createGrid();
        } catch (InvalidPositionException e) {
        }
        assertTrue(buildPort.isPossible(player));
    }
    
    @Test
    void testIsPossibleWithInsufficientResources() {
        player.addRessoure(Ressource.WOOD, 0);
        player.addRessoure(Ressource.SHEEP, 1);
        
        try {
            board.createGrid();
        } catch (InvalidPositionException e) {
        }
        assertFalse(buildPort.isPossible(player));
    }

    @Test
    public void testBuild() throws IOException {        
        try {
            board.createGrid();
        } catch (InvalidPositionException e) {
        }
        int[] coord = firstAvailableCoord(board, 10, 10);
        Land land = firstAvailableLand(board, 10, 10);

        player.addRessoure(Ressource.WOOD, 1);
        player.addRessoure(Ressource.SHEEP, 2);

        buildPort.build(player, coord[0], coord[1]);

        assertEquals(0, player.getRessources().get(Ressource.WOOD));
        assertEquals(0, player.getRessources().get(Ressource.SHEEP));
        assertTrue(land.hasBuilding());
        assertTrue(player.getBuildings().get(0) instanceof Port);
    }

    /**
     * Returns the first available land tile on the board i.e. the first tile that isn't a sea and doesn't have a building
     * @param board the board we want the land on
     * @param length the length of the board
     * @param width the width of the board
     * @return the first available land tile on the board
    */
    public static int[] firstAvailableCoord(Board board, int length, int width){
        int x = 0;
        int y = 0;
        try{
            Tile tile = board.getTile(0, 0);
            while(x < length && ((tile instanceof Sea) || !(tile instanceof Sea) && ((Land) tile).hasBuilding())){
                while(y < width && ((tile instanceof Sea) || !(tile instanceof Sea) && ((Land) tile).hasBuilding())){
                    tile = board.getTile(x, y);
                    if(!(y < width && ((tile instanceof Sea) || !(tile instanceof Sea) && ((Land) tile).hasBuilding()))){
                        int[] coordonnees = {x, y};
                        return coordonnees;
                    }
                    y += 1;
                }
                x += 1;
                y = 0;
            }
        } catch(InvalidPositionException e){
        }
        int[] coordonnees = {x, y};
        return coordonnees;
    }

    /**
     * Returns the first available land tile on the board i.e. the first tile that isn't a sea and doesn't have a building
     * @param board the board we want the land on
     * @param length the length of the board
     * @param width the width of the board
     * @return the first available land tile on the board
    */
    public static Land firstAvailableLand(Board board, int length, int width) {
        for (int x = 0; x < length; x++) {
            for (int y = 0; y < width; y++) {
                try {
                    Tile tile = board.getTile(x, y);
                    if (tile instanceof Land && !((Land) tile).hasBuilding()) {
                        return (Land) tile;
                    }
                } catch (InvalidPositionException e) {
                }
            }
        }
        return null;
    }
}
