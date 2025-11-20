package en.tresz.spaceinvaders;

import en.tresz.spaceinvaders.game.GamePanel;
import en.tresz.spaceinvaders.menu.DifficultyMenuPanel;
import en.tresz.spaceinvaders.menu.MainMenuPanel;
import en.tresz.spaceinvaders.menu.ScoreboardPanel;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

import java.awt.CardLayout;

/**
 * The main frame.
 */
public class MainWindow extends JFrame {
    // constants for the panels
    public static final String MENU_PANEL = "MENU";
    public static final String DIFFICULTY_PANEL = "DIFFICULTY";
    public static final String SCOREBOARD_PANEL = "SCOREBOARD";
    public static final String GAME_PANEL = "GAME";

    private CardLayout cardLayout;
    private JPanel mainPanel;

    // the used panels
    private MainMenuPanel mainMenuPanel;
    private DifficultyMenuPanel difficultyMenuPanel;
    private ScoreboardPanel scoreboardPanel;
    private GamePanel gamePanel;

    public MainWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // sonarlint suggested WindowConstants
        setTitle("Space Invaders");
        setSize(400, 800);
        setResizable(true);
        setLocationRelativeTo(null);

        // setting layout
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        // creating the panels
        mainMenuPanel = new MainMenuPanel(this);
        difficultyMenuPanel = new DifficultyMenuPanel(this);
        scoreboardPanel = new ScoreboardPanel(this);
        gamePanel = new GamePanel(this);

        // adding panels to main frame
        mainPanel.add(mainMenuPanel, MENU_PANEL);
        mainPanel.add(difficultyMenuPanel, DIFFICULTY_PANEL);
        mainPanel.add(scoreboardPanel, SCOREBOARD_PANEL);
        mainPanel.add(gamePanel, GAME_PANEL);

        this.add(mainPanel);

        // the default panel is the main menu
        showPanel(MENU_PANEL);
    }

    /**
     * Switches the current panel.
     * 
     * @param panelName the name of the panel to show
     */
    public void showPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }

    /**
     * Sets up the game and swaps to the game panel.
     * 
     * @param difficulty the difficulty of the game
     */
    public void startGame(String difficulty) {
        gamePanel.startGame(difficulty);
        showPanel(GAME_PANEL);
    }
}
