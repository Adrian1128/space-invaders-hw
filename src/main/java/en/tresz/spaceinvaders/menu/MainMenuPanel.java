package en.tresz.spaceinvaders.menu;

import en.tresz.spaceinvaders.MainWindow;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainMenuPanel extends JPanel {
    private MainWindow gameWindow;

    public MainMenuPanel(MainWindow gw) {
        gameWindow = gw;

        initUI();
    }

    /**
     * Initializes the user interface.
     */
    private void initUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(40, 10, 10, 10);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // creating the buttons
        JButton playButton = new JButton("Play");
        playButton.addActionListener(new PlayButtonActionListener());
        add(playButton, gbc);

        JButton scoreboardButton = new JButton("ScoreBoard");
        scoreboardButton.addActionListener(new ScoreboardButtonActionListener());
        add(scoreboardButton, gbc);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ExitButtonActionListener());
        add(exitButton, gbc);
    }

    /**
     * The actionlistener class of the play button.
     */
    private class PlayButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            gameWindow.showPanel(MainWindow.DIFFICULTY_PANEL);
        }
    }

    /**
     * The actionlistener class of the scoreboard button.
     */
    private class ScoreboardButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            gameWindow.showPanel(MainWindow.SCOREBOARD_PANEL);
        }
    }

    /**
     * The actionlistener class of the exit button.
     */
    private class ExitButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int choice = JOptionPane.showConfirmDialog(
                    gameWindow,
                    "Are you sure you want to quit?",
                    "Confirm Exit",
                    JOptionPane.YES_NO_OPTION);

            if (choice == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        }
    }
}
