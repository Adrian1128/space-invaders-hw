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

    public MainWindow() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // sonarlint suggested WindowConstants
        setTitle("Space Invaders");
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(false);
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
        mainPanel.add(mainMenuPanel, PanelType.MENU.name());
        mainPanel.add(difficultyMenuPanel, PanelType.DIFFICULTY.name());
        mainPanel.add(scoreboardPanel, PanelType.SCOREBOARD.name());
        mainPanel.add(gamePanel, PanelType.GAME.name());

        this.add(mainPanel);

        // the default panel is the main menu
        showPanel(PanelType.MENU.name());
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
        showPanel(PanelType.GAME.name());
    }

}
