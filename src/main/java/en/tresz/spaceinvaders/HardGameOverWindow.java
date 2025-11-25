package en.tresz.spaceinvaders;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

//TODO: customize this window
public class HardGameOverWindow extends JFrame implements WindowListener, ActionListener {

    private MainWindow mainWindow;

    private String playerName = "";

    JPanel panel = new JPanel(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    JTextField nameArea = new JTextField(20);

    JButton enter = new JButton("Enter");

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

        int minutes = time / 60;
        int seconds = time % 60;
        JLabel text = new JLabel("Game Over! Your time: " + String.format("%02d:%02d", minutes, seconds));
        gbc.gridy = 0;
        panel.add(text, gbc);

        JLabel name = new JLabel("Enter your name:");
        gbc.gridy = 1;
        panel.add(name, gbc);

        gbc.gridy = 2;
        panel.add(nameArea, gbc);

        gbc.gridy = 3;
        panel.add(enter, gbc);

        add(panel);
    }

    public String getPlayerName() {
        return playerName;

        // TODO: JSON save score logic
    }

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