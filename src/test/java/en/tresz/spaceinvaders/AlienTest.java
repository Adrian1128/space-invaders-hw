package en.tresz.spaceinvaders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import en.tresz.spaceinvaders.game.GamePanel;
import en.tresz.spaceinvaders.game.objects.Player;
import en.tresz.spaceinvaders.game.objects.aliens.Alien;
import en.tresz.spaceinvaders.game.objects.aliens.NormalAlien;
import en.tresz.spaceinvaders.util.Vector2D;

class AlienTest {

    private Alien alien;
    private GamePanel gamePanel;
    private MainWindow mainWindow;
    private int gameWidth;
    private int gameHeight;

    /**
     * Sets up the test environment before each test case.
     */
    @BeforeEach
    void setUp() {
        mainWindow = new MainWindow();
        mainWindow.setSize(800, 800);

        gamePanel = new GamePanel(mainWindow);
        gamePanel.setSize(800, 800);
        alien = new NormalAlien(new Vector2D(200, 100), new Vector2D(2, 0), 5);
        gameWidth = gamePanel.getWidth();
        gameHeight = gamePanel.getHeight();
    }

    /**
     * Tests the request for a global move down by the Alien object.
     * tested function: isRequestGlobalMoveDown
     */
    @Test
    void testAlienRequestGlobalMoveDown() {
        alien.setCollisionCount(0);

        for (int i = 0; i < alien.getClosingInTreshold(); i++) {
            alien.setPosition(new Vector2D(gameWidth - 10, 100));
            alien.setVelocity(new Vector2D(5, 0));
            alien.update(gamePanel);
        }

        assertTrue(alien.isRequestGlobalMoveDown());
        assertFalse(alien.isRequestGlobalMoveDown());
    }

    /**
     * Tests if the Alien correctly identifies reaching the bottom of the game
     * panel.
     * 
     * tested function: hasAlienHitBottom
     */
    @Test
    void testAlienReachedBottom() {
        Player player = new Player(new Vector2D(200, 700), new Vector2D(5, 0), 10, 3);

        alien.setPosition(new Vector2D(200, gameHeight - Alien.ALIEN_HEIGHT));
        assertTrue(alien.reachedBottom(player));
    }

    /**
     * Tests the update of the Alien's position.
     * 
     * tested function: update
     */
    @Test
    void testAlienUpdatePosition() {
        alien.update(gamePanel);
        assertEquals(202, alien.getPosition().getX());

        alien.setPosition(new Vector2D(gameWidth - 1, 100));
        alien.setVelocity(new Vector2D(5, 0));
        alien.update(gamePanel);
        assertTrue(alien.getPosition().getX() <= gameWidth);
    }
}
