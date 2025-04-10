package listchooser.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import java.io.*;


public class InputTest {

   
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
   public void testMethodWithString() throws IOException {
      simulateInput("ok"); 
      String result = Input.readString();
      assertEquals("ok", result);
   }
   
   @Test
   public void testMethodWithInt() throws IOException {
      simulateInput("8");      
      int result = Input.readInt();
      assertEquals(8, result);
      
      simulateInput("ok");
      assertThrows(IOException.class, () -> Input.readInt());
   }
   
   
}

