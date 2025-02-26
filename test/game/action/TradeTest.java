package game.action;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;


import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import game.board.util.Ressource;
import game.player.Player;
import listchooser.util.Input;

public class TradeTest {

    private Player player = new Player("Timoleon");
    private Trade trade = new Trade();

   
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
    void testAct() {
		  player.addRessoure(Ressource.ORE);
      player.addRessoure(Ressource.ORE);
      player.addRessoure(Ressource.ORE);
      player.addRessoure(Ressource.SHEEP);
      player.addRessoure(Ressource.SHEEP);
      player.addRessoure(Ressource.SHEEP);
      this.simulateInput("0");
      this.simulateInput("0");
      trade.act(player);
      assertSame(0, player.getRessources().get(Ressource.ORE));

    }

    @Test
    void testAvailableRessources() {
        player.addRessoure(Ressource.ORE);
        player.addRessoure(Ressource.ORE);
        assertEquals(0, trade.availableRessources(player).size());
        player.addRessoure(Ressource.ORE);
        player.addRessoure(Ressource.SHEEP);
        player.addRessoure(Ressource.SHEEP);
        assertEquals(1, trade.availableRessources(player).size());
        assertEquals(Ressource.ORE, trade.availableRessources(player).get(0));
    }

    @Test
    void testIsPossible() {
        player.addRessoure(Ressource.ORE);
        player.addRessoure(Ressource.ORE);
        player.addRessoure(Ressource.SHEEP);
        player.addRessoure(Ressource.SHEEP);
        assertFalse(trade.isPossible(player));
        player.addRessoure(Ressource.SHEEP);
        assertTrue(trade.isPossible(player));
    }
}
