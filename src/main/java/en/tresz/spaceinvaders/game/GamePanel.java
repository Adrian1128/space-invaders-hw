package en.tresz.spaceinvaders.game;

import en.tresz.spaceinvaders.MainWindow;
import en.tresz.spaceinvaders.game.aliens.*;
import en.tresz.spaceinvaders.util.*;

import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Color;

/**
 * The panel where the game runs.
 */
public class GamePanel extends JPanel {

    private MainWindow gameWindow;

    private transient BufferedImage backgroundImage = ImageLoader.loadBufferedImage("/images/background1.png");

    private transient ArrayList<GameObject> gameObjects = new ArrayList<>();
    private transient ArrayList<Alien> aliens = new ArrayList<>();

    private transient AlienController alienController = new AlienController(aliens);

    public GamePanel(MainWindow gw) {
        this.gameWindow = gw;
        initUI();
    }
    // 
    /**
     * Initializes the user interface.
     */
    private void initUI() {
        setBackground(Color.BLACK);

    }

    /**
     * Starts the game with the given difficulty.
     * 
     * @param difficulty the difficulty of the game
     */
    public void startGame(String difficulty) {

        NormalAlien alien1 = new NormalAlien(new Vector2D(100, 100), new Vector2D(2, 0), 5);
        RapidfireAlien alien = new RapidfireAlien(new Vector2D(100, 100), new Vector2D(2, 0), 5);
        
        gameObjects.add(alien);
        gameObjects.add(alien1);
        aliens.add(alien);
        aliens.add(alien1);

        // createObjects(difficulty);
        Timer timer;

        timer = new Timer(3, e -> {
            updateGame();
            repaint();
        });
        timer.start();

    }

    /**
     * Updates the game state.
     */
    private void updateGame() {
        for (Alien alien : aliens) {

            if (alien.isRequestGlobalMoveDown()) {
                alienController.moveDownAllAliens();
            } else {
                alien.update(this.getWidth(), this.getHeight());
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