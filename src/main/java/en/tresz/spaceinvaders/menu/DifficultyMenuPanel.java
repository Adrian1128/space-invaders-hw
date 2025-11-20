package en.tresz.spaceinvaders.menu;

import en.tresz.spaceinvaders.MainWindow;
import en.tresz.spaceinvaders.util.ButtonMaker;
import en.tresz.spaceinvaders.util.ImageLoader;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Image;
import java.awt.Graphics;

/**
 * The difficulty selection screen.
 * Shows options for Easy, Medium, Hard, and a Back button.
 */
public class DifficultyMenuPanel extends JPanel {

    private MainWindow gameWindow;

    private static final int BUTTON_WIDTH = 135;
    private static final int BUTTON_HEIGHT = 51;

    private transient Image backgroundImage; // sonarlint suggestion (transient)

    public DifficultyMenuPanel(MainWindow gw) {
        this.gameWindow = gw;
        backgroundImage = ImageLoader.loadIcon("/images/menu-background.png").getImage();

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
        gbc.fill = GridBagConstraints.NONE;

        // Title Label
        add(new JLabel("Select Difficulty"), gbc);

        // creating the buttons
        JButton easyButton = ButtonMaker.buttonSetup("easy", BUTTON_WIDTH, BUTTON_HEIGHT);
        easyButton.addActionListener(new EasyButtonActionListener());
        add(easyButton, gbc);

        JButton mediumButton = ButtonMaker.buttonSetup("medium", BUTTON_WIDTH, BUTTON_HEIGHT);
        mediumButton.addActionListener(new MediumButtonActionListener());
        add(mediumButton, gbc);

        JButton hardButton = ButtonMaker.buttonSetup("hard", BUTTON_WIDTH, BUTTON_HEIGHT);
        hardButton.addActionListener(new HardButtonActionListener());
        add(hardButton, gbc);

        gbc.insets = new Insets(100, 10, 10, 10);
        JButton backButton = ButtonMaker.buttonSetup("back", BUTTON_WIDTH, BUTTON_HEIGHT);
        backButton.addActionListener(new BackButtonActionListener());
        add(backButton, gbc);

    }
    
    /**
     * Paints the background image scaled to fit the panel.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
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