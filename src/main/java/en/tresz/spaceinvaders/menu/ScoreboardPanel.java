package en.tresz.spaceinvaders.menu;

import en.tresz.spaceinvaders.MainWindow;
import en.tresz.spaceinvaders.util.ButtonMaker;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.BorderFactory;

import java.awt.BorderLayout;
import java.awt.ComponentOrientation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScoreboardPanel extends JPanel {

    private MainWindow gameWindow;
    private JTextArea scoreArea;

    public ScoreboardPanel(MainWindow gw) {
        this.gameWindow = gw;
        initUI();
        loadScores();
    }

    /**
     * Initializes the user interface.
     */
    private void initUI() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(new JLabel("Top 5 Scores"), BorderLayout.NORTH);

        // creating text area
        scoreArea = new JTextArea();
        scoreArea.setEditable(false);
        scoreArea.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        add(new JScrollPane(scoreArea), BorderLayout.CENTER);

        // creating buttons
        JButton backButton = ButtonMaker.buttonSetup("Back", 180, 68);
        backButton.addActionListener(new BackButtonActionListener());
        add(backButton, BorderLayout.SOUTH);
    }

    /**
     * Loads the scores from a JSON file.
     */
    private void loadScores() {
        // TODO: Implement JSON file reading
        scoreArea.setText("1\n");
        scoreArea.append("2.\n");
        scoreArea.append("3.\n");
        scoreArea.append("4.\n");
        scoreArea.append("5.\n");
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