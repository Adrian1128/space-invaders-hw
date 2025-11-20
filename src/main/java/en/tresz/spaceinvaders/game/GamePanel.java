package en.tresz.spaceinvaders.game;

import en.tresz.spaceinvaders.MainWindow;

import javax.swing.JPanel;

import java.awt.Color;

/**
 * The panel where the game runs.
 */
public class GamePanel extends JPanel {

    private MainWindow gameWindow;
    private String currentDifficulty;
    
    public GamePanel(MainWindow gw) {
        this.gameWindow = gw;
        initUI();
    }

    /**
     * Initializes the user interface.
     */
    private void initUI() {
        setBackground(Color.BLACK);

    }

    /**
     * Sets up the game.
     * 
     * @param difficulty the difficulty of the game
     */
    public void startGame(String difficulty) {
        this.currentDifficulty = difficulty;
        System.out.println("Setting up game for difficulty: " + difficulty);

        // TODO: Start game here
    }
}