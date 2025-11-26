package en.tresz.spaceinvaders;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import en.tresz.spaceinvaders.game.GamePanel;
import en.tresz.spaceinvaders.game.objects.Player;
import en.tresz.spaceinvaders.util.Vector2D;

class PlayerTest {

    private Player player;
    private GamePanel gamePanel;
    private MainWindow mainWindow;
    private int gameWidth;

    /**
     * Sets up the test environment before each test case.
     */
    @BeforeEach
    void setUp() {
        mainWindow = new MainWindow();
        mainWindow.setSize(800, 800);

        gamePanel = new GamePanel(mainWindow);
        gamePanel.setSize(800, 800);

        player = new Player(new Vector2D(200, 700), new Vector2D(5, 0), 10, 3);
        gameWidth = gamePanel.getWidth();
    }

    /**
     * Tests the initialization of the Player object.
     */
    @Test
    void testPlayerInitialization() {
        assertEquals(200, player.getPosition().getX());
        assertEquals(700, player.getPosition().getY());
        assertEquals(5, player.getVelocity().getX());
        assertEquals(3, player.getHealth());
    }

    /**
     * Tests the player movement to the left.
     * tested function: moveLeft, update
     */
    @Test
    void testPlayerMovementLeft() {
        player.moveLeft();
        player.update(gamePanel);

        assertTrue(player.getPosition().getX() < 200);
        assertEquals(195, player.getPosition().getX());
    }

    /**
     * Tests the player movement to the right.
     * tested function: moveRight, update
     */
    @Test
    void testPlayerMovementRight() {
        player.moveRight();
        player.update(gamePanel);

        assertTrue(player.getPosition().getX() > 200);
        assertEquals(205, player.getPosition().getX());
    }

    /**
     * Tests the player boundary constraints on the left side.
     * tested function: moveLeft, update
     */
    @Test
    void testPlayerLeftBoundary() {
        player.setPosition(new Vector2D(10, 700));
        player.moveLeft();
        player.update(gamePanel);

        assertEquals(player.getHalfWidth(), player.getPosition().getX());
    }

    /**
     * Tests the player boundary constraints on the right side.
     * tested function: moveRight, update
     */
    @Test
    void testPlayerRightBoundary() {
        player.setPosition(new Vector2D(gameWidth - 10, 700));
        player.moveRight();
        player.update(gamePanel);

        assertEquals(gameWidth - player.getHalfWidth(), player.getPosition().getX());
    }

    /**
     * Tests the player stop functionality.
     * tested function: stop
     */
    @Test
    void testPlayerStop() {
        player.moveRight();
        player.update(gamePanel);

        int positionAfterMove = player.getPosition().getX();

        player.stop();
        player.update(gamePanel);

        assertEquals(positionAfterMove, player.getPosition().getX());
    }
}
