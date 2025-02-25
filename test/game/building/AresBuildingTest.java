package game.building;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import game.board.*;
import game.board.util.Ressource;
import game.player.*;

public class AresBuildingTest {
    Player player = new Player("player");
    Land forest = new Forest();
    AresBuilding ares1;

    @BeforeEach
    public void init(){
        ares1 = new AresBuilding(player, forest, 0);
    }

    @Test
    void testAresBuildingGetters(){
        assertEquals(player, ares1.getPlayer());
        assertEquals(forest, ares1.getLand());
        assertFalse(ares1.isEvolved());
    }

    @Test
    void testAresBuildingAddWarriors(){
        ares1.addWarriors(5);
        assertEquals(5, ares1.getDimension());
    }

    @Test
    void testAresBuildingEvolve(){
        assertFalse(ares1.isEvolved());
        ares1.evolve();
        assertTrue(ares1.isEvolved());
        ares1.evolve();
        assertTrue(ares1.isEvolved());
    }

    @Test
    void testAresPlayerCollectResources(){
        // C'est normal si ça ne marche pas pour l'instant!
        // Le test devrait marcher quand la classe Player sera complétée.
        
        assertEquals(0, (player.getRessources()).get(Ressource.WOOD));
        ares1.playerCollectRessources();
        assertEquals(1, (player.getRessources()).get(Ressource.WOOD));
        ares1.evolve();
        ares1.playerCollectRessources();
        assertEquals(3, (player.getRessources()).get(Ressource.WOOD));
    }
}
