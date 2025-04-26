package game.action;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;


import org.junit.jupiter.api.*;

import game.board.Board;
import game.board.Forest;
import game.board.InvalidPositionException;
import game.board.Land;
import game.board.Sea;
import game.board.util.Ressource;
import game.building.AresBuilding;
import game.building.DemeterBuilding;
import game.building.Port;
import game.player.AresPlayer;
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
        player.addRessource(Ressource.WOOD, 1);
        player.addRessource(Ressource.SHEEP, 2);
        
        Board seaBoard = new Board(2, 2);
        try {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    seaBoard.setTile(i, j, new Sea(i, j));
                }
            }
            BuildPort seaBuildPort = new BuildPort(seaBoard);
            assertFalse(seaBuildPort.isPossible(player));
        } catch (InvalidPositionException e) {
        }
    }
    
    @Test
    void testIsPossibleWithAllLandTilesOccupied() {
        player.addRessource(Ressource.WOOD, 1);
        player.addRessource(Ressource.SHEEP, 2);
        
        Board smallBoard = new Board(3, 3);
        try {
            smallBoard.createGrid();
        } catch (InvalidPositionException e) {
        }
        
        try {
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
        }
    }
    
    @Test
    void testIsPossibleWithAvailableLand() {
        player.addRessource(Ressource.WOOD, 1);
        player.addRessource(Ressource.SHEEP, 2);
        
        try {
            board.createGrid();
        } catch (InvalidPositionException e) {
        }
        assertTrue(buildPort.isPossible(player));
    }
    
    @Test
    void testIsPossibleWithInsufficientResources() {
        player.addRessource(Ressource.WOOD, 0);
        player.addRessource(Ressource.SHEEP, 1);
        
        try {
            board.createGrid();
        } catch (InvalidPositionException e) {
        }
        assertFalse(buildPort.isPossible(player));
    }

    @Test
    public void testBuild() throws IOException {        
        try {
            board.fillWithSea();
            board.setTile(1, 1, new Forest(1, 1));
            board.setTile(2, 1, new Forest(2, 1));
            board.setTile(3, 1, new Forest(3, 3));
        } catch (InvalidPositionException e) {
        }
        player.addRessource(Ressource.WOOD, 1);
        player.addRessource(Ressource.SHEEP, 2);

        buildPort.build(player,2, 1);

        assertEquals(0, player.getRessources().get(Ressource.WOOD));
        assertEquals(0, player.getRessources().get(Ressource.SHEEP));
        try {
            assertTrue(((Land) board.getTile(2,1)).hasBuilding());
        } catch (InvalidPositionException e) {
        }
        assertTrue(player.getBuildings().get(0) instanceof Port);
    }

     @Test
    void testBuildPortOnlyOnLandNextToSea() throws InvalidPositionException {
        Board landBoard = new Board(2, 2);
        try {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    landBoard.setTile(i, j, new Forest(i, j));
                }
            }
            BuildPort buildPort = new BuildPort(landBoard);
            player.addRessource(Ressource.WOOD, 1);
            player.addRessource(Ressource.SHEEP, 2);
            assertFalse(buildPort.isPossible(player));
        } catch (InvalidPositionException e) {
        }
    }

    @Test
    void testBuildPortAresPlayerIslandConditions() throws InvalidPositionException {
        board.fillWithSea();
        
        // Première île
        AresPlayer aresPlayer = new AresPlayer("aresPlayer");
        Land land1 = new Forest(1, 1);
        Land land2 = new Forest(2, 1);
        board.setTile(1, 1, land1);
        board.setTile(2, 1, land2);
        new AresBuilding(aresPlayer, land1, 2);
        Land newLand = new Forest(5, 5);
        board.setTile(5, 5, newLand);
        
        player.addRessource(Ressource.WOOD, 1);
        player.addRessource(Ressource.SHEEP, 2);
        assertFalse(buildPort.isPossible(aresPlayer));
        assertTrue(buildPort.isPossible(player));
    }

}
