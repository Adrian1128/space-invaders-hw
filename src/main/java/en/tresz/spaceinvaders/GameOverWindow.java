package en.tresz.spaceinvaders;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//TODO: customize this window
public class GameOverWindow extends JFrame {

    private String playerName = "";

    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();
    
    JTextField nameArea = new JTextField(20);

    JButton enter = new JButton("Enter");

    public GameOverWindow(int score) {
        setTitle("Game Over" + score);
        setSize(300, 200);
        setResizable(false);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        nameArea.addActionListener(new EnterActionListener());

        enter.addActionListener(new EnterActionListener());

        gbc.gridy = 0;
        panel.add(nameArea, gbc);

        gbc.gridy = 1;
        panel.add(enter, gbc);

        add(panel);
    }

    public String getPlayerName() {
        return playerName;
    }

    protected class EnterActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            playerName = nameArea.getText();
            System.out.println("Beírt név: " + playerName);
        }
    }

}
