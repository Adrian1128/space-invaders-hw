package en.tresz.spaceinvaders.game;

import en.tresz.spaceinvaders.MainWindow;
import en.tresz.spaceinvaders.game.aliens.*;
import en.tresz.spaceinvaders.game.projectiles.AlienProjectile;
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

    private MainWindow gameWindow;

    private transient BufferedImage backgroundImage = ImageLoader.loadBufferedImage("/images/background1.png");

    private transient ArrayList<GameObject> gameObjects = new ArrayList<>();
    private transient ArrayList<Alien> aliens = new ArrayList<>();

    private transient Player player;

    private transient AlienController alienController = new AlienController();

    public GamePanel(MainWindow gw) {
        this.gameWindow = gw;
        setFocusable(true);

        initUI();
    }

    //
    /**
     * Initializes the user interface.
     */
    private void initUI() {
        player = new Player(new Vector2D(0, 0), new Vector2D(5, 0), 20, 3);
        player.setPosition(new Vector2D(gameWindow.getWidth() / 2, gameWindow.getHeight() - player.getHeight()));
        gameObjects.add(player);
        player.playerMovement(this);
    }

    /**
     * Starts the game with the given difficulty.
     * 
     * @param difficulty the difficulty of the game
     */
    public void startGame(String difficulty) {

        FastAlien alien1 = new FastAlien(new Vector2D(100, 100), new Vector2D(2, 0), 5, 5);
        FastAlien alien = new FastAlien(new Vector2D(300, 100), new Vector2D(2, 0), 5, 5);

        GameObject proj = new AlienProjectile(alien.getPosition());
        GameObject playerProj = new PlayerProjectile(player.getPosition());

        gameObjects.add(alien);
        gameObjects.add(alien1);
        gameObjects.add(proj);
        gameObjects.add(playerProj);

        createObjects(difficulty);
        alienController.addAllAliens(gameObjects, aliens);

        Timer timer;

        timer = new Timer(10, e -> {
            updateGame();
            repaint();
        });
        timer.start();

    }

    private transient ArrayList<GameObject> objectsToAdd = new ArrayList<>();
    private transient ArrayList<GameObject> objectsToRemove = new ArrayList<>();

    /**
     * Updates the game state.
     */
    private void updateGame() {
        objectsToAdd.clear(); // Clear before each update
        objectsToRemove.clear(); // Clear before each update

        for (GameObject object : gameObjects) {
            object.update(this);
            if (object instanceof Alien alienObject) {
                if (alienObject.isRequestGlobalMoveDown()) {
                    alienController.moveDownAllAliens(this);
                }
                alienController.handleAlienColision(alienObject, this);
                alienObject.shoot(this);
                if (alienObject.reachedBottom(this)) {
                    // TODO: Game over logic
                }
            } else if (object instanceof PlayerProjectile projectile) {
                alienController.handleAlienHit(projectile, this);
            } else if (object instanceof Player playerObject) {
                playerObject.shoot(this);
            }
        }

        // Add new objects after iteration completes
        gameObjects.addAll(objectsToAdd);
        gameObjects.removeAll(objectsToRemove);
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
}
