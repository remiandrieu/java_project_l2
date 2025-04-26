package game.player;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DemeterPlayerTest {
    private DemeterPlayer player;

    @BeforeEach
    void setUp() {
        player = new DemeterPlayer("timoleon");
    }

    @Test
    void testConstructor() {
        assertEquals("timoleon", player.getName());
        assertFalse(player.hasThief());
    }

    @Test
    void testAddThief() {
        assertFalse(player.hasThief());
        player.addThief();
        assertTrue(player.hasThief());
    }

    @Test
    void testRemoveThief() {
        player.addThief();
        assertTrue(player.hasThief());
        player.removeThief();
        assertFalse(player.hasThief());
    }

}