package game.action.util;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;

import game.board.*;
import listchooser.util.Input;

public class ActionsUtilsTest {
    private static InputStream systemIn;
    private static PrintStream systemOut;

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

    @Test
    public void testAskCoordinates(){
        simulateInput("1\ntimo\n1\n2");
        Coordinates coord = ActionUtils.askCoordinates();
        assertTrue(coord.equals(new Coordinates(1, 2)));
    }

    @Test
    public void testAskCoordinatesWithList(){
        simulateInput("1\ntimo\n1\n2\n0\n0");
        ArrayList<Coordinates> list = new ArrayList<>();
        list.add(new Coordinates(0, 0));
        Coordinates coord = ActionUtils.askCoordinates(list);
        assertTrue(coord.equals(new Coordinates(0, 0)));
    }
}
