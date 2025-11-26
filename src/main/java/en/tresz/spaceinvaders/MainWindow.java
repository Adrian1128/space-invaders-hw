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
    // enum for the panels
    public enum PanelType {
        MENU,
        DIFFICULTY,
        SCOREBOARD,
        GAME

    }

    // window size constants
    public static final int WINDOW_WIDTH = 400;
    public static final int WINDOW_HEIGHT = 800;

    private CardLayout cardLayout;
    private JPanel mainPanel;

    // the used panels
    private MainMenuPanel mainMenuPanel;
    private DifficultyMenuPanel difficultyMenuPanel;
    private ScoreboardPanel scoreboardPanel;
    private GamePanel gamePanel;

    /**
     * Constructs the main window and initializes all panels.
     */
    public MainWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // sonarlint suggested WindowConstants
        setTitle("Space Invaders");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
        setLocationRelativeTo(null);
        
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        mainMenuPanel = new MainMenuPanel(this);
        difficultyMenuPanel = new DifficultyMenuPanel(this);
        scoreboardPanel = new ScoreboardPanel(this);
        gamePanel = new GamePanel(this);
        
        mainPanel.add(mainMenuPanel, PanelType.MENU.name());
        mainPanel.add(difficultyMenuPanel, PanelType.DIFFICULTY.name());
        mainPanel.add(scoreboardPanel, PanelType.SCOREBOARD.name());
        mainPanel.add(gamePanel, PanelType.GAME.name());

        this.add(mainPanel);
        
        showPanel(PanelType.MENU);
    }

    /**
     * Gets the game panel.
     * 
     * @return the game panel
     */
    public GamePanel getGamePanel() {
        return gamePanel;
    }

    /**
     * Gets the scoreboard panel.
     * 
     * @return the scoreboard panel
     */
    public ScoreboardPanel getScoreboardPanel() {
        return scoreboardPanel;
    }

    /**
     * Gets the main menu panel.
     * 
     * @return the main menu panel
     */
    public MainMenuPanel getMainMenuPanel() {
        return mainMenuPanel;
    }

    /**
     * Gets the difficulty menu panel.
     * 
     * @return the difficulty menu panel
     */
    public DifficultyMenuPanel getDifficultyMenuPanel() {
        return difficultyMenuPanel;
    }

    /**
     * Switches the current panel.
     * 
     * @param panelName the name of the panel to show
     */
    public void showPanel(PanelType panelType) {
        cardLayout.show(mainPanel, panelType.name());
    }

    /**
     * Sets up the game and swaps to the game panel.
     * 
     * @param difficulty the difficulty of the game
     */
    public void startGame(String difficulty) {
        gamePanel.startGame(difficulty);
        showPanel(PanelType.GAME);
    }

}
