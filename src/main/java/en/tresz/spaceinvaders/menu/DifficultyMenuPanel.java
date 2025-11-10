package en.tresz.spaceinvaders.menu;

import en.tresz.spaceinvaders.MainWindow;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * The difficulty selection screen.
 * Shows options for Easy, Medium, Hard, and a Back button.
 */
public class DifficultyMenuPanel extends JPanel {

    private MainWindow gameWindow;

    public DifficultyMenuPanel(MainWindow gw) {
        this.gameWindow = gw;
        initUI();
    }

    /**
     * Initializes the user interface.
     */
    private void initUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Title Label
        add(new JLabel("Select Difficulty"), gbc);

        // creating the buttons
        JButton easyButton = new JButton("Easy");
        easyButton.addActionListener(new EasyButtonActionListener());
        add(easyButton, gbc);

        JButton mediumButton = new JButton("Medium");
        mediumButton.addActionListener(new MediumButtonActionListener());
        add(mediumButton, gbc);

        JButton hardButton = new JButton("Hard");
        hardButton.addActionListener(new HardButtonActionListener());
        add(hardButton, gbc);

        gbc.insets = new Insets(30, 10, 10, 10);
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new BackButtonActionListener());
        add(backButton, gbc);

    }

    /**
     * The actionlistener class of the easy button.
     */
    private class EasyButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            gameWindow.startGame("Easy");
        }
    }

    /**
     * The actionlistener class of the medium button.
     */
    private class MediumButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            gameWindow.startGame("Medium");
        }
    }

    /**
     * The actionlistener class of the hard button.
     */
    private class HardButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            gameWindow.startGame("Hard");
        }
    }

    /**
     * The actionlistener class of the back button.
     */
    private class BackButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            gameWindow.showPanel(MainWindow.MENU_PANEL);
        }
    }
}