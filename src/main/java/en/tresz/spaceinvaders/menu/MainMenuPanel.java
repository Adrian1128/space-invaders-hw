package en.tresz.spaceinvaders.menu;

import en.tresz.spaceinvaders.MainWindow;
import en.tresz.spaceinvaders.util.ButtonMaker;
import en.tresz.spaceinvaders.util.ImageLoader;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.BorderLayout;
import java.awt.Window;
import java.awt.image.BufferedImage;

public class MainMenuPanel extends JPanel {
    private MainWindow gameWindow;

    private static final int BUTTON_WIDTH = 135;
    private static final int BUTTON_HEIGHT = 51;

    private transient BufferedImage backgroundImage; // sonarlint suggestion (transient)

    public MainMenuPanel(MainWindow gw) {
        gameWindow = gw;
        backgroundImage = ImageLoader.loadBufferedImage("/images/menu-background.png");

        initUI();
    }

    /**
     * Initializes the user interface.
     */
    private void initUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 20, 10);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.NONE;

        gbc.weighty = 3;
        add(Box.createVerticalGlue(), gbc);
        gbc.weighty = 0;

        // creating the buttons

        JButton playButton = ButtonMaker.buttonSetup("play", BUTTON_WIDTH, BUTTON_HEIGHT);

        playButton.addActionListener(new PlayButtonActionListener());
        add(playButton, gbc);

        JButton scoreboardButton = ButtonMaker.buttonSetup("scoreboard", BUTTON_WIDTH, BUTTON_HEIGHT);
        scoreboardButton.addActionListener(new ScoreboardButtonActionListener());
        add(scoreboardButton, gbc);

        JButton exitButton = ButtonMaker.buttonSetup("exit", BUTTON_WIDTH, BUTTON_HEIGHT);
        exitButton.addActionListener(new ExitButtonActionListener());
        add(exitButton, gbc);

        gbc.weighty = 0.4;
        add(Box.createVerticalGlue(), gbc);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    /**
     * The actionlistener class of the play button.
     */
    private class PlayButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            gameWindow.showPanel(MainWindow.PanelType.DIFFICULTY);
        }
    }

    /**
     * The actionlistener class of the scoreboard button.
     */
    private class ScoreboardButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            gameWindow.showPanel(MainWindow.PanelType.SCOREBOARD);
        }
    }

    /**
     * The actionlistener class of the exit button.
     */
    private class ExitButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {

            JPanel panel = new JPanel(new BorderLayout(10, 10));
            panel.add(new JLabel(ImageLoader.scaledIcon("/images/exiticon.png", 32, 32)), BorderLayout.WEST);

            JLabel text = new JLabel("Are you sure you want to exit?");
            panel.add(text, BorderLayout.CENTER);

            JButton yes = ButtonMaker.buttonSetup("yes", 58, 34);
            JButton no = ButtonMaker.buttonSetup("no", 58, 34);

            /**
             * Action listener for the "Yes" button
             */
            yes.addActionListener(event -> {
                Window w = SwingUtilities.getWindowAncestor(yes);
                if (w != null)
                    w.dispose();
                System.exit(0);
            });

            /**
             * Action listener for the "No" button
             */
            no.addActionListener(event -> {
                Window w = SwingUtilities.getWindowAncestor(no);
                if (w != null)
                    w.dispose();
            });

            JButton[] options = { yes, no };
            JOptionPane.showOptionDialog(
                    gameWindow,
                    panel,
                    "Confirm Exit",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    options,
                    no);
        }
    }
}