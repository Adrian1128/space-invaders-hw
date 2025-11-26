package en.tresz.spaceinvaders.game;

import en.tresz.spaceinvaders.GameOverWindow;
import en.tresz.spaceinvaders.HardGameOverWindow;
import en.tresz.spaceinvaders.MainWindow;
import en.tresz.spaceinvaders.game.objects.GameObject;
import en.tresz.spaceinvaders.game.objects.Player;
import en.tresz.spaceinvaders.game.objects.aliens.*;
import en.tresz.spaceinvaders.game.objects.projectiles.PlayerProjectile;
import en.tresz.spaceinvaders.util.*;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * The panel where the game runs.
 */
public class GamePanel extends JPanel {

    private MainWindow mainWindow;

    private transient BufferedImage backgroundImage = ImageLoader.loadBufferedImage("/images/background1.png");

    private transient ArrayList<GameObject> gameObjects = new ArrayList<>();
    private transient ArrayList<Alien> aliens = new ArrayList<>();

    private transient HealthBar healthBar;

    private transient int maxHealth = 3;

    private transient AlienController alienController = new AlienController();
    private transient PlayerController playerController = new PlayerController();

    private transient GameTimer gameTimer = new GameTimer();

    private transient boolean won = false;

    private transient boolean playerHit = false;
    private transient int playerHitTimer = 30;

    /**
     * Constructs a GamePanel.
     * 
     * @param mw the main window reference
     */
    public GamePanel(MainWindow mw) {
        this.mainWindow = mw;
        setFocusable(true);

        initUI();
    }

    //
    /**
     * Initializes the user interface.
     */
    private void initUI() {

        setLayout(null);

        gameTimer.setBounds(10, 10, 100, 30);
        this.add(gameTimer);

        healthBar = new HealthBar(maxHealth);
        healthBar.setBounds(0, 0, mainWindow.getWidth() - 20, mainWindow.getHeight());
        this.add(healthBar);
    }

    /**
     * Initializes the game state based on the selected difficulty.
     * 
     * @param difficulty the difficulty level
     */
    private void initGame(String difficulty) {
        gameObjects.clear();

        aliens.clear();

        gameTimer.reset();
        gameTimer.start();

        healthBar.reset();

        playerHitTimer = 0;

        Player player = new Player(new Vector2D(0, 0), new Vector2D(5, 0), 10, maxHealth);
        player.setPosition(new Vector2D(mainWindow.getWidth() / 2, mainWindow.getHeight() - player.getHeight()));
        gameObjects.add(player);
        player.playerMovement(this);

        createObjects(difficulty);
        alienController.addAllAliens(gameObjects, aliens);

    }

    /**
     * Starts the game with the given difficulty.
     * 
     * @param difficulty the difficulty of the game
     */
    public void startGame(String difficulty) {

        initGame(difficulty);

        Timer timer;
        timer = new Timer(10, e -> {
            if (!updateGame()) {
                ((Timer) e.getSource()).stop();
                gameTimer.stop();

                if (won && difficulty.equals("Hard")) {
                    HardGameOverWindow hardGameOverWindow = new HardGameOverWindow(gameTimer.getTotalSeconds(),
                            mainWindow);
                    hardGameOverWindow.setVisible(true);
                    mainWindow.getScoreboardPanel().refreshScores();
                } else {
                    GameOverWindow gameOverWindow = new GameOverWindow(gameTimer.getTotalSeconds(), mainWindow);
                    gameOverWindow.setVisible(true);
                }

            }
            repaint();
        });
        timer.start();

    }

    private transient ArrayList<GameObject> objectsToAdd = new ArrayList<>();
    private transient ArrayList<GameObject> objectsToRemove = new ArrayList<>();

    /**
     * Updates the game state including object movements, collisions, and shooting.
     * 
     * @return false if the game is over, true otherwise
     */
    public boolean updateGame() {
        objectsToAdd.clear();
        objectsToRemove.clear();

        alienController.handleAllAlienCollisions(this);

        for (GameObject object : gameObjects) {
            object.update(this);
            if (object instanceof Alien alienObject) {
                if (alienObject.isRequestGlobalMoveDown()) {
                    alienController.moveDownAllAliens(this);
                }
                if (alienObject.chance(50)) {
                    alienObject.shoot(this);
                }
            } else if (object instanceof PlayerProjectile projectile) {
                alienController.handleAlienHit(projectile, this);
            } else if (object instanceof Player playerObject && !playerLogicHandler(playerObject)) {
                return false;
            }

        }

        gameObjects.addAll(objectsToAdd);
        gameObjects.removeAll(objectsToRemove);
        return true;
    }

    /**
     * Handles player game logic.
     * 
     * @param playerObject the player object
     * @return false if the game is over, true otherwise
     */
    private boolean playerLogicHandler(Player playerObject) {
        playerController.handlePlayerHit(playerObject, this);
        playerObject.shoot(this);
        if (playerObject.getHealth() <= 0 || alienController.hasAlienHitBottom(this, playerObject)) {
            won = false;
            return false; // Game over
        } else if (alienController.areAllAliensDestroyed(this)) {
            won = true;
            return false; // Player wins
        }
        return true;
    }

    /**
     * Paints the game objects.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }

        for (GameObject object : gameObjects) {
            if (object instanceof Player playerObject) {
                if (playerHit) {
                    playerHitTimer--;
                    if (playerHitTimer <= 0) {
                        playerHit = false;
                    }
                }
                playerObject.draw(g, playerHit);

            } else {
                object.draw(g);
            }
        }
    }

    /**
     * Creates game objects based on difficulty.
     * 
     * @param difficulty
     */
    private void createObjects(String difficulty) {
        switch (difficulty) {
            case "Easy":
                createEasyObjects();
                break;
            case "Medium":
                createMediumObjects();
                break;
            case "Hard":
                createHardObjects();
                break;
            default:
                throw new IllegalArgumentException("Unknown difficulty: " + difficulty);
        }
    }

    /**
     * Creates game objects for easy difficulty.
     */
    public void createEasyObjects() {
        int xSpacing = 20;
        int ySpacing = 10;
        int firstRowY = 100;
        for (int i = 0; i < 5; i++) {
            NormalAlien alien1 = new NormalAlien(
                    new Vector2D(100 + i * NormalAlien.NORMAL_ALIEN_WIDTH + xSpacing, firstRowY),
                    new Vector2D(1, 0), 5);

            FastAlien alien2 = new FastAlien(
                    new Vector2D(100 + i * FastAlien.FAST_ALIEN_WIDTH + xSpacing,
                            firstRowY + 2 * Alien.ALIEN_HEIGHT + 2 * ySpacing),
                    new Vector2D(1, 0), 5);
            gameObjects.add(alien1);
            gameObjects.add(alien2);
        }
    }

    /**
     * Creates game objects for medium difficulty.
     */
    public void createMediumObjects() {
        int xSpacing = 20;
        int ySpacing = 10;
        int firstRowY = 100;
        for (int i = 0; i < 3; i++) {
            FastAlien alien1 = new FastAlien(
                    new Vector2D(100 + i * FastAlien.FAST_ALIEN_WIDTH + xSpacing, firstRowY),
                    new Vector2D(1, 0), 5);
            NormalAlien alien2 = new NormalAlien(
                    new Vector2D(100 + i * NormalAlien.NORMAL_ALIEN_WIDTH + xSpacing,
                            firstRowY + 2 * Alien.ALIEN_HEIGHT + 2 * ySpacing),
                    new Vector2D(1, 0), 5);
            RapidfireAlien alien3 = new RapidfireAlien(
                    new Vector2D(100 + i * RapidfireAlien.RAPIDFIRE_ALIEN_WIDTH + xSpacing,
                            firstRowY + 3 * Alien.ALIEN_HEIGHT + 3 * ySpacing),
                    new Vector2D(1, 0), 5);
            gameObjects.add(alien1);
            gameObjects.add(alien2);
            gameObjects.add(alien3);
        }
    }

    /**
     * Creates game objects for hard difficulty.
     */
    public void createHardObjects() {
        int xSpacing = 20;
        int ySpacing = 10;
        int firstRowY = 100;
        for (int i = 0; i < 3; i++) {
            FastRapidfireAlien alien1 = new FastRapidfireAlien(
                    new Vector2D(100 + i * FastRapidfireAlien.FAST_RAPIDFIRE_ALIEN_WIDTH + xSpacing, firstRowY),
                    new Vector2D(1, 0), 5);
            WideAlien alien2 = new WideAlien(
                    new Vector2D(100 + i * WideAlien.WIDE_ALIEN_WIDTH + xSpacing,
                            firstRowY + Alien.ALIEN_HEIGHT + ySpacing),
                    new Vector2D(1, 0), 5);
            NormalAlien alien3 = new NormalAlien(
                    new Vector2D(100 + i * NormalAlien.NORMAL_ALIEN_WIDTH + xSpacing,
                            firstRowY + 2 * Alien.ALIEN_HEIGHT + 2 * ySpacing),
                    new Vector2D(1, 0), 5);
            RapidfireAlien alien4 = new RapidfireAlien(
                    new Vector2D(100 + i * RapidfireAlien.RAPIDFIRE_ALIEN_WIDTH + xSpacing,
                            firstRowY + 3 * Alien.ALIEN_HEIGHT + 3 * ySpacing),
                    new Vector2D(1, 0), 5);

            gameObjects.add(alien1);
            gameObjects.add(alien2);
            gameObjects.add(alien3);
            gameObjects.add(alien4);
        }
    }

    /**
     * Gets the list of game objects.
     * 
     * @return the list of game objects
     */
    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    /**
     * Gets the list of aliens.
     * 
     * @return the list of aliens
     */
    public List<Alien> getAliens() {
        return aliens;
    }

    /**
     * Adds a game object to the panel.
     * 
     * @param object the game object to add
     */
    public void addGameObject(GameObject object) {
        objectsToAdd.add(object);
    }

    /**
     * Removes a game object from the panel.
     * 
     * @param object the game object to remove
     */
    public void removeGameObject(GameObject object) {
        objectsToRemove.add(object);
    }

    /**
     * Gets the health bar.
     * 
     * @return the health bar
     */
    public HealthBar getHealthBar() {
        return healthBar;
    }

    /**
     * Gets the game timer.
     * 
     * @return the game timer
     */
    public GameTimer getGameTimer() {
        return gameTimer;
    }

    /**
     * Sets the player hit status.
     * 
     * @param playerHit true if the player is hit, false otherwise
     */
    public void setPlayerHit(boolean playerHit) {
        this.playerHit = playerHit;
        if (playerHit) {
            this.playerHitTimer = 50;
        }
    }

    /**
     * Test method to add a game object directly (for testing purposes). !!!
     * 
     * @param object
     */
    public void testAddObject(GameObject object) {
        gameObjects.add(object);
    }

    /**
     * Test method to remove a game object directly (for testing purposes). !!!
     * 
     * @param object
     */
    public void testRemoveObject(GameObject object) {
        gameObjects.remove(object);
    }
}
