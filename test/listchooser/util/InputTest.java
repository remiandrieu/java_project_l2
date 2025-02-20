package listchooser.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;


public class InputTest {

   
   private static InputStream systemIn;
   private static PrintStream systemOut;
   
   @BeforeAll // sauvegarde de System.in et System.out
   public static void changeSystemOut() {
      System.out.println("tests begin");
      systemIn = System.in;
      systemOut = System.out;
      System.setOut(new PrintStream(new ByteArrayOutputStream()));   // détourne System.out
      System.out.println("NE SERA PAS VISIBLE SUR LA SORTIE STANDARD");
   }
   
   @AfterAll // restauration de System.in et System.out
   public static void restoreSystemInOut() throws IOException {
      System.setIn(systemIn);
      System.setOut(systemOut);
      System.out.println("tests end");
   }
   
   @BeforeEach 
   public void init() {
      new Input();
   }

   
   /** permet de simuler une saisie au clavier en fournissant la chaine qui serait saisie
    * @param input la chaine saisie, qui sera donc récupérée par un scanner qui lirait sur Scanner.in  
    */
   private void simulateInput(String input) {   
      InputStream in = new ByteArrayInputStream(input.getBytes()); 
      System.setIn(in);  // détourne System.in vers in
   }
   
   @Test
   public void testMethodWithString() throws IOException {
      this.simulateInput("ok"); 
      String result = Input.readString();
      assertEquals("ok", result);
   }
   
   @Test
   public void testMethodWithInt() throws IOException {
      this.simulateInput("8");      
      int result = Input.readInt();
      assertEquals(8, result);
      
      this.simulateInput("ok");
      assertThrows(IOException.class, () -> Input.readInt());
   }
   
   
}

