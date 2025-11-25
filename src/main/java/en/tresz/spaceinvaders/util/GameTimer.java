package en.tresz.spaceinvaders.util;

import javax.swing.*;
import java.awt.*;

public class GameTimer extends JLabel {

    private int seconds = 0;
    private int minutes = 0;
    private Timer timer;

    public GameTimer() {
        setFont(new Font("Arial", Font.BOLD, 20));
        setForeground(Color.gray);
        setText("00:00");

        timer = new Timer(1000, e -> updateTime());
        timer.start();
    }

    private void updateTime() {
        seconds++;
        
        if (seconds == 60) {
            seconds = 0;
            minutes++;
        }

        setText(String.format("%02d:%02d", minutes, seconds));
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    public void reset() {
        seconds = 0;
        minutes = 0;
        setText("00:00");
    }

    public int getTotalSeconds() {
        return minutes * 60 + seconds;
    }
}
