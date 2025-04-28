package game.action.util;

import java.util.ArrayList;

import game.board.*;
import listchooser.util.Input;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.*;
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
}
