package en.tresz.spaceinvaders.util;

import javax.swing.*;
import java.awt.*;

/**
 * A label that displays and tracks game time in MM:SS format.
 */
public class GameTimer extends JLabel {

    private transient Time time;
    private Timer timer;

    /**
     * Constructs a GameTimer and starts counting from 00:00.
     */
    public GameTimer() {
        time = new Time(0, 0);
        setFont(new Font("Arial", Font.BOLD, 20));
        setForeground(Color.gray);
        setText("00:00");

        timer = new Timer(1000, e -> updateTime());
        timer.start();
    }

    /**
     * Updates the timer by one second and refreshes the display.
     */
    private void updateTime() {
        time.setSeconds(time.getSeconds() + 1);

        if (time.getSeconds() == 60) {
            time.setSeconds(0);
            time.setMinutes(time.getMinutes() + 1);
        }

        setText(String.format("%02d:%02d", time.getMinutes(), time.getSeconds()));
    }

    /**
     * Starts the timer.
     */
    public void start() {
        timer.start();
    }

    /**
     * Stops the timer.
     */
    public void stop() {
        timer.stop();
    }

    /**
     * Resets the timer to 00:00.
     */
    public void reset() {
        time.setMinutes(0);
        time.setSeconds(0);
        setText("00:00");
    }

    /**
     * Gets the minutes component of the timer.
     * 
     * @return the minutes
     */
    public int getMinutes() {
        return time.getMinutes();
    }

    /**
     * Gets the seconds component of the timer.
     * 
     * @return the seconds
     */
    public int getSeconds() {
        return time.getSeconds();
    }

    /**
     * Gets the Time object representing the current time.
     * 
     * @return the Time object
     */
    public Time getTime() {
        return time;
    }

    /**
     * Gets the total time in seconds.
     * 
     * @return the total seconds elapsed
     */
    public int getTotalSeconds() {
        return time.getMinutes() * 60 + time.getSeconds();
    }
}
