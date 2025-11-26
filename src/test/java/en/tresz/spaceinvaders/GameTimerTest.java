package en.tresz.spaceinvaders;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import en.tresz.spaceinvaders.util.GameTimer;

class GameTimerTest {

    private GameTimer gameTimer;

    /**
     * Sets up the test environment before each test case.
     */
    @BeforeEach
    void setUp() {
        gameTimer = new GameTimer();
        gameTimer.stop();
    }

    /**
     * Tests the initialization of the GameTimer.
     */
    @Test
    void testGameTimerInitialization() {
        assertEquals(0, gameTimer.getMinutes());
        assertEquals(0, gameTimer.getSeconds());
        assertEquals("00:00", gameTimer.getText());
        assertEquals(0, gameTimer.getTotalSeconds());
    }

    /**
     * Tests the reset functionality of the GameTimer.
     * tested function: reset
     */
    @Test
    void testGameTimerReset() {
        gameTimer.getTime().setMinutes(2);
        gameTimer.getTime().setSeconds(30);

        gameTimer.reset();

        assertEquals(0, gameTimer.getMinutes());
        assertEquals(0, gameTimer.getSeconds());
        assertEquals("00:00", gameTimer.getText());
        assertEquals(0, gameTimer.getTotalSeconds());
    }

    /**
     * Tests the getTotalSeconds method with various time values.
     * tested function: getTotalSeconds
     */
    @Test
    void testGetTotalSeconds() {

        gameTimer.getTime().setMinutes(2);
        gameTimer.getTime().setSeconds(30);

        assertEquals(150, gameTimer.getTotalSeconds());

        gameTimer.reset();
        assertEquals(0, gameTimer.getTotalSeconds());
    }
}
