package game.board.util;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import game.board.*;
import game.player.*;
import game.building.*;

public class BoardUtilsTest {
   private Board board;
   
  @BeforeEach
  public void init(){
    this.board = new Board(10, 10);
    try{
        this.board.fillWithSea();
    }catch(Exception e){

    } 
  }

 @Test
 public void testFirstAvailableCoords(){
    //Empty board: only sea (returns null)

    Coordinates coordinates = BoardUtils.firstAvailableCoords(this.board);
    assertNull(coordinates);

    // Three empty lands on the board
    Land land = null;
    Land land2 = null;
    Land land3 = null;

    try{
        land = this.board.getRandomTypeOfLand(0, 0);
        this.board.setTile(0, 0, land);

        land2 = this.board.getRandomTypeOfLand(4, 5);
        this.board.setTile(4, 5, land2);

        land3 = this.board.getRandomTypeOfLand(9, 9);
        this.board.setTile(9, 9, land3);
    }catch(Exception e){

    }

    Coordinates coor1 = BoardUtils.firstAvailableCoords(this.board);
    assertEquals(0, coor1.getX());
    assertEquals(0, coor1.getY());

    new AresBuilding(new Player(null), land, 5);
    
    Coordinates coor2 = BoardUtils.firstAvailableCoords(this.board);
    assertEquals(4, coor2.getX());
    assertEquals(5, coor2.getY());

    new AresBuilding(new Player(null), land2, 5);

    Coordinates coor3 = BoardUtils.firstAvailableCoords(this.board);
    assertEquals(9, coor3.getX());
    assertEquals(9, coor3.getY());

    new AresBuilding(new Player(null), land3, 5);

    assertNull(BoardUtils.firstAvailableCoords(this.board));

 }

 @Test
 public void testFirstAvailableCoord(){
    //Empty board: only sea (returns a tile outside of grid)

    Coordinates coordinates = BoardUtils.firstAvailableCoord(this.board, false, null);
    assertEquals(10, coordinates.getX());
    assertEquals(0, coordinates.getY());

    // Land in the corner

    Land land = null;
    Land land2 = null;
    Land land3 = null;

    try{
        land = this.board.getRandomTypeOfLand(0, 0);
        this.board.setTile(0, 0, land);

        land2 = this.board.getRandomTypeOfLand(0, 1);
        this.board.setTile(0, 1, land2);

        land3 = this.board.getRandomTypeOfLand(1, 0);
        this.board.setTile(1, 0, land3);
    }catch(Exception e){

    }
    Coordinates coor1 = BoardUtils.firstAvailableCoord(this.board, true, new Player(null));
    assertEquals(0, coor1.getX());
    assertEquals(1, coor1.getY());

    new AresBuilding(new Player(null), land2, 5);
    
    Coordinates coor2 = BoardUtils.firstAvailableCoord(this.board, true, new Player(null));
    assertEquals(1, coor2.getX());
    assertEquals(0, coor2.getY());

    new AresBuilding(new Player(null), land3, 5);

    Coordinates coor3 = BoardUtils.firstAvailableCoord(this.board, true, new Player(null));
    assertEquals(10, coor3.getX());
    assertEquals(0, coor3.getY());

 }

 @Test
 public void testNFirstAvailableCoords(){
    //Empty board: only sea (returns empty List)

    List<Coordinates> coordinates = BoardUtils.nFirstAvailableCoords(this.board, 5);
    assertTrue(coordinates.isEmpty());

    // Three empty lands on the board and one with a building
    Land land = null;
    Land land2 = null;
    Land land3 = null;
    Land land4 = null;

    try{
        land = this.board.getRandomTypeOfLand(0, 0);
        this.board.setTile(0, 0, land);

        land2 = this.board.getRandomTypeOfLand(4, 5);
        this.board.setTile(4, 5, land2);

        land3 = this.board.getRandomTypeOfLand(8, 1);
        this.board.setTile(8, 1, land3);
        new AresBuilding(new Player(null), land3, 5);

        land4 = this.board.getRandomTypeOfLand(9, 9);
        this.board.setTile(9, 9, land4);
    }catch(Exception e){

    }

    List<Coordinates> coords = BoardUtils.nFirstAvailableCoords(this.board, 3);

    assertEquals(3, coords.size());

    assertTrue(coords.contains(land.getCoordinates()));
    assertTrue(coords.contains(land2.getCoordinates()));
    assertFalse(coords.contains(land3.getCoordinates()));
    assertTrue(coords.contains(land4.getCoordinates()));
 }

 @Test
 public void testFirstAvailableLand(){
    //Empty board: only sea (returns null)

    Land land = BoardUtils.firstAvailableLand(this.board);
    assertNull(land);

    // Three empty lands on the board
    Land land1 = null;
    Land land2 = null;
    Land land3 = null;

    try{
        land1 = this.board.getRandomTypeOfLand(0, 0);
        this.board.setTile(0, 0, land1);

        land2 = this.board.getRandomTypeOfLand(4, 5);
        this.board.setTile(4, 5, land2);

        land3 = this.board.getRandomTypeOfLand(9, 9);
        this.board.setTile(9, 9, land3);
    }catch(Exception e){

    }

    assertEquals(land1, BoardUtils.firstAvailableLand(this.board));

    new AresBuilding(new Player(null), land1, 5);
    
    assertEquals(land2, BoardUtils.firstAvailableLand(this.board));

    new AresBuilding(new Player(null), land2, 5);

    assertEquals(land3, BoardUtils.firstAvailableLand(this.board));

    new AresBuilding(new Player(null), land3, 5);

    assertNull(BoardUtils.firstAvailableLand(this.board));

 }
}
