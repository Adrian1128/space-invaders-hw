package en.tresz.spaceinvaders.menu;

import en.tresz.spaceinvaders.MainWindow;
import en.tresz.spaceinvaders.util.ButtonMaker;
import en.tresz.spaceinvaders.util.Score;
import en.tresz.spaceinvaders.util.ScoreManager;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.JTextPane;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.List;

/**
 * The scoreboard panel displays the top 5 high scores.
 */
public class ScoreboardPanel extends JPanel {

    private MainWindow gameWindow;
    private JTextPane scoreArea;

    /**
     * Constructs a ScoreboardPanel.
     * 
     * @param gw the main game window
     */
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
        setBorder(BorderFactory.createEmptyBorder(10, 10, 80, 10));
        setBackground(new java.awt.Color(0x222034));

        JLabel titleLabel = new JLabel("Top 5 Scores");
        titleLabel.setForeground(java.awt.Color.LIGHT_GRAY);
        add(titleLabel, BorderLayout.NORTH);

        // creating text area
        scoreArea = new JTextPane();
        scoreArea.setEditable(false);
        scoreArea.setBackground(new java.awt.Color(0x222034)); // Set text area background
        scoreArea.setForeground(java.awt.Color.LIGHT_GRAY);
        scoreArea.setFont(new java.awt.Font("Monospaced", java.awt.Font.BOLD, 20));

        // Center align text
        StyledDocument doc = scoreArea.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);

        JScrollPane scrollPane = new JScrollPane(scoreArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Remove white border
        scrollPane.getViewport().setBackground(new java.awt.Color(0x222034));
        add(scrollPane, BorderLayout.CENTER);
        
        // creating buttons
        JButton backButton = ButtonMaker.buttonSetup("Back", 180, 68);
        backButton.addActionListener(new BackButtonActionListener());
        add(backButton, BorderLayout.SOUTH);
    }

    /**
     * Loads the scores from a JSON file.
     */
    private void loadScores() {
        ScoreManager scoreManager = new ScoreManager();
        List<Score> scores = scoreManager.loadScores();

        if (scores.isEmpty()) {
            scoreArea.setText("No scores available.");
            return;
        }

        StringBuilder sb = new StringBuilder();
        int rank = 1;
        for (Score score : scores) {
            sb.append(rank).append(". ").append(score.getPlayerName()).append(" - ")
                    .append(score.getTime().getMinutes()).append(":").append(score.getTime().getSeconds())
                    .append("\n");
            rank++;
        }
        scoreArea.setText(sb.toString());
    }

    /**
     * Refreshes the displayed scores by reloading from the JSON file.
     */
    public void refreshScores() {
        loadScores();
    }

    /**
     * The actionlistener class of the back button.
     */
    private class BackButtonActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            gameWindow.showPanel(MainWindow.PanelType.MENU);
        }
    }
}