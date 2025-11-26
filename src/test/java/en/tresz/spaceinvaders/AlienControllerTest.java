package en.tresz.spaceinvaders;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import en.tresz.spaceinvaders.game.AlienController;
import en.tresz.spaceinvaders.game.GamePanel;
import en.tresz.spaceinvaders.game.objects.aliens.Alien;
import en.tresz.spaceinvaders.game.objects.aliens.NormalAlien;
import en.tresz.spaceinvaders.game.objects.projectiles.PlayerProjectile;
import en.tresz.spaceinvaders.util.Vector2D;

class AlienControllerTest {

    private Alien alien;
    private Alien otherAlien;
    private AlienController alienController;
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
        alienController = new AlienController();
        alien = new NormalAlien(new Vector2D(200, 100), new Vector2D(2, 0), 5);
        otherAlien = new NormalAlien(new Vector2D(300, 100), new Vector2D(2, 0), 5);
        gameWidth = gamePanel.getWidth();
        gameHeight = gamePanel.getHeight();
    }

    /**
     * Tests the initialization of the Alien object.
     */
    @Test
    void testAlienInitialization() {
        assertEquals(200, alien.getPosition().getX());
        assertEquals(100, alien.getPosition().getY());
        assertEquals(2, alien.getVelocity().getX());
        assertEquals(Alien.ALIEN_HEIGHT, alien.getHeight());
    }

    /**
     * Tests adding all aliens to the GamePanel.
     * tested function: addAllAliens
     */
    @Test
    void testAddAllAliens() {

        gamePanel.testAddObject(alien);
        gamePanel.testAddObject(otherAlien);

        alienController.addAllAliens(gamePanel.getGameObjects(), gamePanel.getAliens());

        assertEquals(2, gamePanel.getAliens().size());
    }

    /**
     * Tests handling collisions between aliens.
     * tested function: handleAllAlienCollisions
     */
    @Test
    void testHandleAllAlienCollisions() {
        gamePanel.testAddObject(alien);
        gamePanel.testAddObject(otherAlien);

        alienController.addAllAliens(gamePanel.getGameObjects(), gamePanel.getAliens());

        alien.setPosition(new Vector2D(250, 100));
        otherAlien.setPosition(new Vector2D(255, 100));

        alienController.handleAllAlienCollisions(gamePanel);

        assertEquals(-2, alien.getVelocity().getX());
        assertEquals(-2, otherAlien.getVelocity().getX());
        assertTrue(Math.abs(alien.getPosition().getX() - otherAlien.getPosition().getX()) > (alien.getHalfWidth()
                + otherAlien.getHalfWidth()));
    }

    /**
     * Tests if all aliens are destroyed.
     * tested function: areAllAliensDestroyed
     */
    @Test
    void testAllAliensAreDestroyed() {
        gamePanel.testAddObject(alien);
        gamePanel.testAddObject(otherAlien);

        alienController.addAllAliens(gamePanel.getGameObjects(), gamePanel.getAliens());

        int initialAlienCount = gamePanel.getAliens().size();

        assertEquals(2, initialAlienCount);

        gamePanel.testRemoveObject(alien);
        gamePanel.testRemoveObject(otherAlien);

        assertTrue(alienController.areAllAliensDestroyed(gamePanel));
    }
}
