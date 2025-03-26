package game.action;

import static org.junit.jupiter.api.Assertions.*;
import java.io.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;

import game.board.*;
import game.board.util.Ressource;
import game.building.AresBuilding;
import game.building.Port;
import game.player.*;

public class BuildArmyTest {
    private static InputStream systemIn;
    private static PrintStream systemOut;

    Board board;
    AresPlayer player;
    BuildArmy action;

    @BeforeClass // sauvegarde de System.in et System.out
    public static void changeSystemOut() {
        System.out.println("tests begin");
        systemIn = System.in;
        systemOut = System.out;
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
    }

    @AfterClass // restauration de System.in et System.out
    public static void restoreSystemInOut() throws IOException {
        System.setIn(systemIn);
        System.setOut(systemOut);
        System.out.println("tests end");
    }

    public static void simulateInput(String input) {   
        InputStream in = new ByteArrayInputStream(input.getBytes()); 
        System.setIn(in);
    }

    @BeforeEach
    public void init(){
        board = new Board(10,10);
        try{
            board.createGrid();
        } catch(Exception e){
            
        }
        player = new AresPlayer("Player");
        player.addRessoure(Ressource.WOOD);
        player.addRessoure(Ressource.SHEEP);
        action = new BuildArmy(board);
    }

    @Test
    public void testIsPossible(){
        assertFalse(action.isPossible(player));
        player.addRessoure(Ressource.WHEAT);
        assertFalse(action.isPossible(player));
        player.removeRessoure(Ressource.WHEAT);
        player.addWarrior(1);
        assertFalse(action.isPossible(player));
        player.addRessoure(Ressource.WHEAT);
        assertTrue(action.isPossible(player));
    }

    @Test
    public void testBuild() throws IOException {
        player.addRessoure(Ressource.WHEAT);
        player.addWarrior(10);
        Land land = firstAvailableLand(board, 10, 10);
        new Port(player, land);
        
        land = firstAvailableLand(board, 10, 10);

        assertEquals(1, player.getRessources().get(Ressource.WHEAT));
        assertEquals(1, player.getRessources().get(Ressource.WOOD));
        assertEquals(1, player.getRessources().get(Ressource.SHEEP));
        assertFalse(land.hasBuilding());

        action.build(player, land.getCoordinates().getX(), land.getCoordinates().getY(), 4);

        assertEquals(0, player.getRessources().get(Ressource.WHEAT));
        assertEquals(0, player.getRessources().get(Ressource.WOOD));
        assertEquals(0, player.getRessources().get(Ressource.SHEEP));
        assertTrue(land.hasBuilding());
    }

    @Test
    public void testFullBoard(){
        player.addWarrior(10);
        player.addRessoure(Ressource.WHEAT);
        Land land = firstAvailableLand(board, 10, 10);
        while(land != null){
            assertTrue(action.isPossible(player));
            new Port(player, land);
            land = firstAvailableLand(board, 10, 10);
               
        }

        assertFalse(action.isPossible(player));        
    }

    @Test
    public void testAct() throws InvalidPositionException {
        player.addRessoure(Ressource.ORE);
        player.addRessoure(Ressource.WOOD);
        player.addWarrior(10);

        int[] coord = firstAvailableCoord(board, 10, 10);
        String input = coord[0] + "\n" + coord[1] + "\n3";
        simulateInput(input);
        action.act(player);
        assertTrue(((Land) board.getTile(coord[0], coord[1])).hasBuilding());
        assertTrue(player.getBuildings().get(0) instanceof AresBuilding);
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
