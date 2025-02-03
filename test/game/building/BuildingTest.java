package game.building;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import game.board.*;
import game.player.*;

public class BuildingTest {
    Player player = new Player();
    Land forest = new Forest();

    @Test
    void testAresBuildingGetters(){
        AresBuilding ares1 = new AresBuilding(player, forest, 0);
        assertEquals(player, ares1.getPlayer());
        assertEquals(forest, ares1.getLand());
        assertFalse(ares1.isEvolved());
    }
}
