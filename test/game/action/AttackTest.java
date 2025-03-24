package game.action;

import static org.junit.jupiter.api.Assertions.*;
import java.io.*;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;

import game.board.Board;
import game.board.Coordinates;
import game.board.InvalidPositionException;
import game.board.Land;
import game.board.Tile;
import game.board.util.Ressource;
import game.building.AresBuilding;
import game.building.Port;
import game.player.AresPlayer;

public class AttackTest {
    private static InputStream systemIn;
    private static PrintStream systemOut;
    private ByteArrayOutputStream testOut;

    private Board board;
    private AresPlayer player1;
    private AresPlayer player2;
    private AresPlayer player3;
    private Attack action;

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

    public void simulateInput(String input) {   
        InputStream in = new ByteArrayInputStream(input.getBytes()); 
        System.setIn(in);
    }

    @BeforeEach
    public void init(){
        this.board = new Board(10,10);
        try{
            this.board.createGrid();
        } catch(Exception e){
        }
        this.player1 = new AresPlayer("P1");
        this.player2 = new AresPlayer("P2");
        this.player3 = new AresPlayer("P3");
        this.player1.addRessoure(Ressource.WOOD);
        this.player1.addRessoure(Ressource.SHEEP);
        this.action = new Attack(this.board);
    }


    @Test
    public void testBuildingsThatCanAttackCoordinates(){
        //No Buildings
        assertTrue(this.action.buildingsThatCanAttackCoordinates(this.player1).isEmpty());
        
        
        //Only player 1's buildings
        for(int i = 0; i < 15; i++){
            Land land = firstAvailableLand(this.board, 10, 10);
            AresBuilding army = new AresBuilding(this.player1, land, 5);
            this.player1.getBuildings().add(army);
        }
        assertTrue(this.action.buildingsThatCanAttackCoordinates(this.player1).isEmpty());


        //Only player 1's buildings and ports from other players
        init();

        for(int i = 0; i < 15; i++){
            Land land = firstAvailableLand(this.board, 10, 10);
            if(i%3 == 0){
                AresBuilding army = new AresBuilding(this.player1, land, 5);
                this.player1.getBuildings().add(army);
            } else if(i%3 == 1){
                Port port = new Port(this.player2, land);
                this.player2.getBuildings().add(port);
            } else {
                Port port = new Port(this.player3, land);
                this.player3.getBuildings().add(port);
            } 
        }

        assertTrue(this.action.buildingsThatCanAttackCoordinates(this.player1).isEmpty());


        //Player 1 only ports and other players buildings
        init();

        for(int i = 0; i < 15; i++){
            Land land = firstAvailableLand(this.board, 10, 10);
            if(i%3 == 0){
                Port port = new Port(this.player1, land);
                this.player1.getBuildings().add(port);
            } else if(i%3 == 1){
                AresBuilding army = new AresBuilding(this.player2, land, 5);
                this.player2.getBuildings().add(army);
            } else {
                AresBuilding army = new AresBuilding(this.player3, land, 5);
                this.player3.getBuildings().add(army);
            } 
        }

        assertTrue(this.action.buildingsThatCanAttackCoordinates(this.player1).isEmpty());


        //Classic situation
        init();

        for(int i = 0; i < 15; i++){
            Land land = firstAvailableLand(this.board, 10, 10);
            if(i%3 == 0){
                AresBuilding army = new AresBuilding(this.player1, land, 5);
                this.player1.getBuildings().add(army);
            } else if(i%3 == 1){
                AresBuilding army = new AresBuilding(this.player2, land, 5);
                this.player2.getBuildings().add(army);
            } else {
                AresBuilding army = new AresBuilding(this.player3, land, 5);
                this.player3.getBuildings().add(army);
            } 
        }

        assertFalse(this.action.buildingsThatCanAttackCoordinates(this.player1).isEmpty());
        for(Coordinates coor : this.action.buildingsThatCanAttackCoordinates(this.player1)) {
            try {
                AresBuilding building = (AresBuilding) ((Land) (this.board.getTile(coor.getX(), coor.getY()))).getBuilding();
                assertTrue(this.player1.getBuildings().contains(building));
            } catch (InvalidPositionException e) {
            }
        }
    }

    @Test
    public void testCheckAndAskForSecretWeaponNo(){
        assertFalse(action.checkAndAskForSecretWeapon(player1));

        this.player1.addSecretWeapon();
        this.simulateInput("n");
        boolean res = this.action.checkAndAskForSecretWeapon(this.player1);
        assertFalse(res);
    }

    @Test
    public void testCheckAndAskForSecretWeaponYes(){
        this.player1.addSecretWeapon();
        this.simulateInput("y");
        boolean res = this.action.checkAndAskForSecretWeapon(this.player1);
        assertTrue(res);

        assertEquals(0, this.player1.getNbSecretWeapons());
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