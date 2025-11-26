package en.tresz.spaceinvaders;

import static en.tresz.spaceinvaders.util.ButtonMaker.buttonSetup;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import en.tresz.spaceinvaders.util.GameTimer;
import en.tresz.spaceinvaders.util.Score;
import en.tresz.spaceinvaders.util.ScoreManager;
import en.tresz.spaceinvaders.util.Time;

/**
 * Game over window for hard difficulty that allows entering player name for
 * scoreboard.
 */
public class HardGameOverWindow extends JFrame implements WindowListener, ActionListener {

    private MainWindow mainWindow;

    private String playerName = "";

    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    JTextField nameArea = new JTextField(20);

    JButton enter = buttonSetup("enter", 112, 42);

    /**
     * Constructs a HardGameOverWindow with time and score entry.
     * 
     * @param time       the game completion time in seconds
     * @param mainWindow the main window reference
     */
    public HardGameOverWindow(int time, MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        setTitle("Game Over");
        setSize(300, 200);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        nameArea.addActionListener(this);

        enter.addActionListener(this);

        addWindowListener(this);

        panel.setBackground(new java.awt.Color(0x222034));

        int minutes = time / 60;
        int seconds = time % 60;
        JLabel text = new JLabel("Game Over! Your time: " + String.format("%02d:%02d", minutes, seconds));
        text.setForeground(Color.LIGHT_GRAY);
        gbc.gridy = 0;
        gbc.insets = new Insets(0, 0, 10, 0);
        panel.add(text, gbc);

        JLabel name = new JLabel("Enter your name:");
        name.setForeground(Color.LIGHT_GRAY);
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 5, 0);
        panel.add(name, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 10, 0);
        panel.add(nameArea, gbc);

        gbc.gridy = 3;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(enter, gbc);

        add(panel);
    }

    /**
     * Gets the player name entered.
     * 
     * @return the player name
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Handles the enter button action to save the score.
     * 
     * @param e the action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        GameTimer gameTimer = mainWindow.getGamePanel().getGameTimer();
        playerName = nameArea.getText().trim();
        ScoreManager scoreManager = new ScoreManager();
        scoreManager.addScore(new Score(playerName, new Time(gameTimer.getMinutes(), gameTimer.getSeconds())));
        mainWindow.getScoreboardPanel().refreshScores();
        this.dispose();
    }

    @Override
    public void windowOpened(WindowEvent e) {
        // don't need to do anything
    }

    @Override
    public void windowClosing(WindowEvent e) {
        mainWindow.showPanel(MainWindow.PanelType.MENU);
    }

    @Override
    public void windowClosed(WindowEvent e) {
        mainWindow.showPanel(MainWindow.PanelType.MENU);
    }

    @Override
    public void windowIconified(WindowEvent e) {
        // don't need to do anything
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
        // don't need to do anything
    }

    @Override
    public void windowActivated(WindowEvent e) {
        // don't need to do anything
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
        // don't need to do anything
    }

}