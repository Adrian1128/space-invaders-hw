package en.tresz.spaceinvaders.game;

import en.tresz.spaceinvaders.GameOverWindow;
import en.tresz.spaceinvaders.HardGameOverWindow;
import en.tresz.spaceinvaders.MainWindow;
import en.tresz.spaceinvaders.game.objects.GameObject;
import en.tresz.spaceinvaders.game.objects.Player;
import en.tresz.spaceinvaders.game.objects.aliens.*;
import en.tresz.spaceinvaders.game.projectiles.PlayerProjectile;
import en.tresz.spaceinvaders.util.*;

import java.util.ArrayList;

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

    private transient Player player;
    private transient int maxHealth = 3;

    private transient AlienController alienController = new AlienController();
    private transient PlayerController playerController = new PlayerController();

    private transient GameTimer gameTimer = new GameTimer();

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
     * Starts the game with the given difficulty.
     * 
     * @param difficulty the difficulty of the game
     */
    public void startGame(String difficulty) {

        gameObjects.clear();
        aliens.clear();
        gameTimer.reset();
        gameTimer.start();
        healthBar.reset();

        FastAlien alien1 = new FastAlien(new Vector2D(100, 100), new Vector2D(2, 0), 5);
        FastAlien alien = new FastAlien(new Vector2D(300, 100), new Vector2D(2, 0), 5);

        player = new Player(new Vector2D(0, 0), new Vector2D(5, 0), 20, maxHealth);
        player.setPosition(new Vector2D(mainWindow.getWidth() / 2, mainWindow.getHeight() - player.getHeight()));
        gameObjects.add(player);
        player.playerMovement(this);

        gameObjects.add(alien);
        gameObjects.add(alien1);

        createObjects(difficulty);
        alienController.addAllAliens(gameObjects, aliens);

        Timer timer;

        timer = new Timer(10, e -> {
            if (!updateGame()) {
                ((Timer) e.getSource()).stop();
                gameTimer.stop();
                HardGameOverWindow hardGameOverWindow = new HardGameOverWindow(gameTimer.getTotalSeconds(), mainWindow);
                hardGameOverWindow.setVisible(difficulty.equals("Hard"));
                GameOverWindow gameOverWindow = new GameOverWindow(gameTimer.getTotalSeconds(), mainWindow);
                gameOverWindow.setVisible(!difficulty.equals("Hard"));

            }
            repaint();
        });
        timer.start();

    }

    private transient ArrayList<GameObject> objectsToAdd = new ArrayList<>();
    private transient ArrayList<GameObject> objectsToRemove = new ArrayList<>();

    /**
     * Updates the game state.
     */
    private boolean updateGame() {
        objectsToAdd.clear();
        objectsToRemove.clear();

        for (GameObject object : gameObjects) {
            object.update(this);
            if (object instanceof Alien alienObject) {
                if (alienObject.isRequestGlobalMoveDown()) {
                    alienController.moveDownAllAliens(this);
                }
                alienController.handleAlienColision(alienObject, this);
                alienObject.shoot(this);
            } else if (object instanceof PlayerProjectile projectile) {
                alienController.handleAlienHit(projectile, this);
            } else if (object instanceof Player playerObject) {
                playerController.handlePlayerHit(playerObject, this);
                // playerObject.shoot(this);
                if (playerObject.getHealth() <= 0 || alienController.areAllAliensDestroyed(this)
                        || alienController.hasAlienHitBottom(this, playerObject)) {
                    return false; // Game over
                }
            }
        }

        // Add new objects after iteration completes
        gameObjects.addAll(objectsToAdd);
        gameObjects.removeAll(objectsToRemove);
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

        player.draw(g);

        for (GameObject object : gameObjects) {
            object.draw(g);
        }
    }

    /**
     * Creates game objects based on difficulty.
     * 
     * @param difficulty
     */
    private void createObjects(String difficulty) {
        // TODO: Create game objects based on difficulty
        switch (difficulty) {
            case "Easy":
                // create easy objects
                break;
            case "Medium":
                // create medium objects
                break;
            case "Hard":
                // create hard objects
                break;
            default:
                throw new IllegalArgumentException("Unknown difficulty: " + difficulty);
        }
    }

    public ArrayList<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void addGameObject(GameObject object) {
        objectsToAdd.add(object);
    }

    public void removeGameObject(GameObject object) {
        objectsToRemove.add(object);
    }

    public HealthBar getHealthBar() {
        return healthBar;
    }
}
