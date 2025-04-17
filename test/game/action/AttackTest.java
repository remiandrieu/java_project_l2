package game.action;

import static org.junit.jupiter.api.Assertions.*;
import java.io.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;

import game.board.Board;
import game.board.Coordinates;
import game.board.InvalidPositionException;
import game.board.Land;
import game.board.util.*;
import game.building.AresBuilding;
import game.building.Port;
import game.player.AresPlayer;
import listchooser.util.Input;

public class AttackTest {
    private static InputStream systemIn;
    private static PrintStream systemOut;

    private Board board;
    private AresPlayer player1;
    private AresPlayer player2;
    private AresPlayer player3;
    private Attack action;

    @BeforeClass // sauvegarde de System.in et System.out
    public static void storeSystemIn() {
        systemIn = System.in;
        systemOut = System.out;
        System.setOut(new PrintStream(new ByteArrayOutputStream()));
    }

    @AfterClass
    public static void restoreSystemIn() throws IOException {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    public static void simulateInput(String input) {   
        InputStream in = new ByteArrayInputStream((input + "\n").getBytes()); 
        Input.setInputStream(in);
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
            Land land = BoardUtils.firstAvailableLand(this.board);
            AresBuilding army = new AresBuilding(this.player1, land, 5);
            this.player1.getBuildings().add(army);
        }
        assertTrue(this.action.buildingsThatCanAttackCoordinates(this.player1).isEmpty());


        //Only player 1's buildings and ports from other players
        init();

        for(int i = 0; i < 15; i++){
            Land land = BoardUtils.firstAvailableLand(this.board);
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
            Land land = BoardUtils.firstAvailableLand(this.board);
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
            Land land = BoardUtils.firstAvailableLand(this.board);
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
    public void testCheckAndAskForSecretWeapon(){
        assertFalse(action.checkAndAskForSecretWeapon(player1));

        this.player1.addSecretWeapon();
        simulateInput("n");
        boolean res = this.action.checkAndAskForSecretWeapon(this.player1);
        assertFalse(res);

        simulateInput("y");
        res = this.action.checkAndAskForSecretWeapon(this.player1);
        assertTrue(res);

        assertEquals(0, this.player1.getNbSecretWeapons());
    }

    @Test
    public void testThrowDice(){
        Land land = BoardUtils.firstAvailableLand(this.board);
        AresBuilding building = new AresBuilding(this.player1, land, 1);
        Coordinates coor = land.getCoordinates();

        //1 Soldat et pas d'armes secrètes = 1 dé
        for(int i = 0; i < 10; i ++){
            int score = action.scoreThrowingDice(coor, false);
            assertTrue(1 <= score && score <= 6);
        }

        //1 Soldat et 1 arme secrète = 2 dés
        for(int i = 0; i < 10; i ++){
            int score = action.scoreThrowingDice(coor, true);
            assertTrue(2 <= score && score <= 12);
        }

        building.addWarriors(3);

        //4 Soldat et pas d'armes secrètes = 2 dés
        for(int i = 0; i < 10; i ++){
            int score = action.scoreThrowingDice(coor, false);
            assertTrue(2 <= score && score <= 12);
        }

        building.addWarriors(4);

        //8 Soldats et pas d'armes secrètes = 3 dés
        for(int i = 0; i < 10; i ++){
            int score = action.scoreThrowingDice(coor, false);
            assertTrue(3 <= score && score <= 18);
        }

        //8 Soldats et une arme secrète = 4 dés
        for(int i = 0; i < 10; i ++){
            int score = action.scoreThrowingDice(coor, true);
            assertTrue(5 <= score && score <= 24);
        }
    }

    @Test
    public void testRemovesWarriors(){
        Land land = BoardUtils.firstAvailableLand(this.board);
        AresBuilding building = new AresBuilding(this.player1, land, 2);
        this.player1.getBuildings().add(building);
        Coordinates coor = land.getCoordinates();

        this.action.removesWarriorsBuilding(this.player1, coor);

        assertEquals(1, building.getDimension());

        this.action.removesWarriorsBuilding(this.player1, coor);

        assertFalse(land.hasBuilding());
        assertTrue(this.player1.getBuildings().isEmpty());

        AresBuilding newBuilding = new AresBuilding(this.player1, land, 2);
        this.player1.getBuildings().add(newBuilding);

        assertTrue(land.hasBuilding());
        assertFalse(this.player1.getBuildings().isEmpty());
    }

}