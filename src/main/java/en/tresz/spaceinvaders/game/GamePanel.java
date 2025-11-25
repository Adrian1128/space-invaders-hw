package en.tresz.spaceinvaders.game;

import en.tresz.spaceinvaders.MainWindow;
import en.tresz.spaceinvaders.game.aliens.*;
import en.tresz.spaceinvaders.game.projectiles.AlienProjectile;
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

    private BufferedImage backgroundImage = ImageLoader.loadBufferedImage("/images/background1.png");

    private ArrayList<GameObject> gameObjects = new ArrayList<>();

    private Player player;

    public boolean leftPressed = false;
    public boolean rightPressed = false;

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
        player = new Player(new Vector2D(gameWindow.getWidth() / 2, gameWindow.getHeight() - Player.HEIGHT),
                new Vector2D(5, 0));
        gameObjects.add(player);
        player.playerMovement(this);
    }

    /**
     * Starts the game with the given difficulty.
     * 
     * @param difficulty the difficulty of the game
     */
    public void startGame(String difficulty) {

        FastAlien alien1 = new FastAlien(new Vector2D(100, 100), new Vector2D(2, 0), 5);
        FastAlien alien = new FastAlien(new Vector2D(300, 100), new Vector2D(2, 0), 5);

        GameObject proj = new AlienProjectile(new Vector2D(100, 100), new Vector2D(0, 6));

        gameObjects.add(alien);
        gameObjects.add(alien1);
        gameObjects.add(proj);
        
        // createObjects(difficulty);
        AlienController.addAllAliens(gameObjects);

        Timer timer;
        
        timer = new Timer(10, e -> {
            proj.update(this);
            updateGame();
            repaint();
        });
        timer.start();

    }

    /**
     * Updates the game state.
     */
    private void updateGame() {

        for (GameObject object : gameObjects) {
            if (object instanceof Alien alien) {
                if (alien.isRequestGlobalMoveDown()) {
                    AlienController.moveDownAllAliens();
                } else {
                    alien.update(this);
                }
                AlienController.handleAlienColision(alien);

                if (alien.reachedBottom(this)) {
                    // TODO: Game over logic
                }
            }
            if (object instanceof Player) {
                player.update(this);
            }

        }

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
}
