package game.action;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;

import game.board.*;
import game.board.util.*;
import game.building.*;
import game.player.*;
import listchooser.util.Input;

public class EvolveFarmTest {
    private static InputStream systemIn;
    private static PrintStream systemOut;

    private Board board;
    private DemeterPlayer player;
    private EvolveFarm action;

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
        board = new Board(10, 10);
        try{
            board.createGrid();
        }catch(Exception e){

        }
        player = new DemeterPlayer("Timoleon");
        player.addRessource(Ressource.WHEAT);
        player.addRessource(Ressource.SHEEP);
        player.addRessource(Ressource.WOOD, 2);
        action = new EvolveFarm(board);
    }

    @Test
    public void testIsPossible(){
        //Vérification que l'action est impossible avec 0 batiments sur le plateau
        assertFalse(action.isPossible(player));

        //Vérification que l'action est possible avec 1 batiment non évolué sur le plateau 
        Land land = BoardUtils.firstAvailableLand(board);
        DemeterBuilding farm = new DemeterBuilding(player, land);
        player.getBuildings().add(farm);
        assertTrue(action.isPossible(player));

        //Vérification que l'action est impossible avec seulement 1 batiment évolué sur le plateau
        farm.evolve();
        assertFalse(action.isPossible(player));

        //Vérification que l'action est impossible si le batiment appartient à un autre joueur
        DemeterPlayer player2 = new DemeterPlayer("Timoleon2");
        land = BoardUtils.firstAvailableLand(board);
        farm = new DemeterBuilding(player2, land);
        player2.getBuildings().add(farm);
        assertFalse(action.isPossible(player));
        farm.evolve();
        assertFalse(action.isPossible(player));

        //Vérification que les ports ne comptent pas
        land = BoardUtils.firstAvailableLand(board);
        Port port = new Port(player, land);
        player.getBuildings().add(port);
        assertFalse(action.isPossible(player));
    }

    @Test
    public void testAct(){
        Land land = BoardUtils.firstAvailableLand(board);
        Coordinates coord = BoardUtils.firstAvailableCoords(board);
        String inputString = coord.getX() + "\n" + coord.getY();

    
        DemeterBuilding farm = new DemeterBuilding(player, land);
        player.getBuildings().add(farm);
        assertFalse(farm.isEvolved());

        simulateInput(inputString);
        action.act(player);

        assertTrue(farm.isEvolved());
    }

}
