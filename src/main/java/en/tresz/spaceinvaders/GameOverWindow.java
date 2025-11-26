package en.tresz.spaceinvaders;

import static en.tresz.spaceinvaders.util.ButtonMaker.buttonSetup;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * Game over window for easy/medium difficulty with play again option.
 */
public class GameOverWindow extends JFrame implements WindowListener, ActionListener {

    private MainWindow mainWindow;

    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    JButton playAgainButton = buttonSetup("play-again", 112, 42);

    /**
     * Constructs a GameOverWindow displaying the completion time.
     * 
     * @param time       the game completion time in seconds
     * @param mainWindow the main window reference
     */
    public GameOverWindow(int time, MainWindow mainWindow) {
        this.mainWindow = mainWindow;
        setTitle("Game Over");
        setSize(300, 200);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        playAgainButton.addActionListener(this);
        addWindowListener(this);

        panel.setBackground(new java.awt.Color(0x222034));

        int minutes = time / 60;
        int seconds = time % 60;
        JLabel text = new JLabel("Game Over! Your time: " + String.format("%02d:%02d", minutes, seconds));
        text.setForeground(Color.LIGHT_GRAY);
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 0, 15, 0);
        panel.add(text, gbc);

        gbc.gridy = 2;
        gbc.insets = new Insets(0, 0, 0, 0);
        panel.add(playAgainButton, gbc);

        add(panel);
    }

    /**
     * Handles the play again button action.
     * 
     * @param e the action event
     */
    @Override
    public void actionPerformed(ActionEvent e) {
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
