package game.objective;

import static org.junit.jupiter.api.Assertions.*;
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
    public void testIsIslandConquered(){
        //There is no buildings on the board.
        assertFalse(objective.isAchieved());

        Land land = firstAvailableLand(board, 10, 10);
        AresBuilding farm = new AresBuilding(player, land, 2);
        player.getBuildings().add(farm);

        assertFalse(objective.isAchieved());


        while(land != null){
            land = firstAvailableLand(board, 10, 10);
            farm = new AresBuilding(player, land,2);
            player.getBuildings().add(farm);
        }

        assertTrue(objective.isAchieved());
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