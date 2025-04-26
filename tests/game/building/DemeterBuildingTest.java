package game.building;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import game.board.*;
import game.board.util.Ressource;
import game.player.*;


public class DemeterBuildingTest {
    Player player = new Player("player");
    Land forest = new Forest(0, 0);
    DemeterBuilding demeter1;

    @BeforeEach
    public void init(){
        demeter1 = new DemeterBuilding(player, forest);
    }

    @Test
    void testDemeterBuildingGetters(){
        assertEquals(player, demeter1.getPlayer());
        assertEquals(forest, demeter1.getLand());
        assertFalse(demeter1.isEvolved());
    }

    @Test
    void testDemeterBuildingEvolve(){
        assertFalse(demeter1.isEvolved());
        demeter1.evolve();
        assertTrue(demeter1.isEvolved());
        demeter1.evolve();
        assertTrue(demeter1.isEvolved());
    }

    @Test
    void testDemeterPlayerCollectResources(){
        // C'est normal si ça ne marche pas pour l'instant!
        // Le test devrait marcher quand la classe Player sera complétée.
        
        assertEquals(0, (player.getRessources()).get(Ressource.WOOD));
        demeter1.playerCollectRessources();
        assertEquals(1, (player.getRessources()).get(Ressource.WOOD));
        demeter1.evolve();
        demeter1.playerCollectRessources();
        assertEquals(3, (player.getRessources()).get(Ressource.WOOD));
    }
}
