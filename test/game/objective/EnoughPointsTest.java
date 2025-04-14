package game.objective;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.*;
import game.board.*;

import game.building.DemeterBuilding;
import game.player.*;

public class EnoughPointsTest {
    Board board;
    DemeterPlayer player;
    EnoughPoints objective;

    @BeforeEach
    public void init() {
        player = new DemeterPlayer("Timoleon");
    }

    @Test
    public void testConstructeur() {
        assertEquals(objective.getDescription(), "You need to get at least 12 points.");
    }

    @Test
    public void testIsAchievedInOneIslandwithNoEvolution(){
        Board board = new Board(10, 10);
        Objective objective = new EnoughPoints(player, board);
        try {
            board.fillWithSea();
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        int x = 0;
        int y = 0;
        while (x*10+y<13){
            Forest forest = new Forest(x, y);
            try {
                board.setTile(x, y, forest);
            } catch (InvalidPositionException e) {
                e.printStackTrace();
            }
            y++;
            if(y==10){
                y=0;
                x++;
            }
        }
        ArrayList<Coordinates> island = new ArrayList<>();
        try {
            island = board.detectIslands().get(0);
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < island.size() - 1; i++) {
            assertFalse(objective.isAchieved());
            Coordinates coord = island.get(i);
            try {
                Land land = (Land) board.getTile(coord.getX(), coord.getY());
                DemeterBuilding b = new DemeterBuilding(player, land);
                player.getBuildings().add(b);
            } catch (InvalidPositionException e) {
                e.printStackTrace();
            }
        }
        assertTrue(objective.isAchieved());
    }

    @Test
    public void testIsAchievedInOneIslandwithEvolution(){
        Board board = new Board(10, 10);
        Objective objective = new EnoughPoints(player, board);
        try {
            board.fillWithSea();
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        int x = 0;
        int y = 0;
        while (x*10+y<13){
            Forest forest = new Forest(x, y);
            try {
                board.setTile(x, y, forest);
            } catch (InvalidPositionException e) {
                e.printStackTrace();
            }
            y++;
            if(y==10){
                y=0;
                x++;
            }
        }
        ArrayList<Coordinates> island = new ArrayList<>();
        try {
            island = board.detectIslands().get(0);
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < island.size() - 6; i++) {
            assertFalse(objective.isAchieved());
            Coordinates coord = island.get(i);
            try {
                Land land = (Land) board.getTile(coord.getX(), coord.getY());
                DemeterBuilding b = new DemeterBuilding(player, land);
                player.getBuildings().add(b);
                if (i>1) b.evolve();
            } catch (InvalidPositionException e) {
                e.printStackTrace();
            }
        }
        assertTrue(objective.isAchieved());
    }

    @Test
    public void testIsAchievedInTwoIsland(){
        Board board = new Board(10, 10);
        Objective objective = new EnoughPoints(player, board);
        try {
            board.fillWithSea();
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        int x = 0;
        int y = 0;
        while (x*10+y<7){
            Forest forest = new Forest(x, y);
            try {
                board.setTile(x, y, forest);
            } catch (InvalidPositionException e) {
                e.printStackTrace();
            }
            y++;
            if(y==10){
                y=0;
                x++;
            }
        }
        x = 8;
        y = 0;
        while ((x-8)*10+y<7){
            Forest forest = new Forest(x, y);
            try {
                board.setTile(x, y, forest);
            } catch (InvalidPositionException e) {
                e.printStackTrace();
            }
            y++;
            if(y==10){
                y=0;
                x++;
            }
        }
        ArrayList<ArrayList<Coordinates>> islands = new ArrayList<ArrayList<Coordinates>>();
        try {
            islands = board.detectIslands();
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < islands.get(0).size(); i++) {
            assertFalse(objective.isAchieved());
            Coordinates coord = islands.get(0).get(i);
            try {
                Land land = (Land) board.getTile(coord.getX(), coord.getY());
                DemeterBuilding b = new DemeterBuilding(player, land);
                player.getBuildings().add(b);
                if (i==0) b.evolve();
            } catch (InvalidPositionException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 3; i++) {
            assertFalse(objective.isAchieved());
            Coordinates coord = islands.get(1).get(i);
            try {
                Land land = (Land) board.getTile(coord.getX(), coord.getY());
                DemeterBuilding b = new DemeterBuilding(player, land);
                player.getBuildings().add(b);
            } catch (InvalidPositionException e) {
                e.printStackTrace();
            }
        }
        assertTrue(objective.isAchieved());
    }

    @Test
    public void testIsAchievedInThreeIsland(){
        Board board = new Board(10, 10);
        Objective objective = new EnoughPoints(player, board);
        try {
            board.fillWithSea();
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        int x = 0;
        int y = 0;
        while (x*10+y<5){
            Forest forest = new Forest(x, y);
            try {
                board.setTile(x, y, forest);
            } catch (InvalidPositionException e) {
                e.printStackTrace();
            }
            y++;
            if(y==10){
                y=0;
                x++;
            }
        }
        x = 4;
        y = 0;
        while ((x-4)*10+y<5){
            Forest forest = new Forest(x, y);
            try {
                board.setTile(x, y, forest);
            } catch (InvalidPositionException e) {
                e.printStackTrace();
            }
            y++;
            if(y==10){
                y=0;
                x++;
            }
        }
        x = 8;
        y = 0;
        while ((x-8)*10+y<5){
            Forest forest = new Forest(x, y);
            try {
                board.setTile(x, y, forest);
            } catch (InvalidPositionException e) {
                e.printStackTrace();
            }
            y++;
            if(y==10){
                y=0;
                x++;
            }
        }
        ArrayList<ArrayList<Coordinates>> islands = new ArrayList<ArrayList<Coordinates>>();
        try {
            islands = board.detectIslands();
        } catch (InvalidPositionException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < 3; i++) {
            assertFalse(objective.isAchieved());
            Coordinates coord = islands.get(0).get(i);
            try {
                Land land = (Land) board.getTile(coord.getX(), coord.getY());
                DemeterBuilding b = new DemeterBuilding(player, land);
                player.getBuildings().add(b);
                if (i==0) b.evolve();
            } catch (InvalidPositionException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 3; i++) {
            assertFalse(objective.isAchieved());
            Coordinates coord = islands.get(1).get(i);
            try {
                Land land = (Land) board.getTile(coord.getX(), coord.getY());
                DemeterBuilding b = new DemeterBuilding(player, land);
                player.getBuildings().add(b);
            } catch (InvalidPositionException e) {
                e.printStackTrace();
            }
        }
        for (int i = 0; i < 3; i++) {
            assertFalse(objective.isAchieved());
            Coordinates coord = islands.get(2).get(i);
            try {
                Land land = (Land) board.getTile(coord.getX(), coord.getY());
                DemeterBuilding b = new DemeterBuilding(player, land);
                player.getBuildings().add(b);
            } catch (InvalidPositionException e) {
                e.printStackTrace();
            }
        }
        assertTrue(objective.isAchieved());
    }


}