package en.tresz.spaceinvaders.util;

/**
 * Represents a time duration in minutes and seconds.
 */
public class Time {
    private int minutes;
    private int seconds;

    /**
     * Constructs a Time object with the specified minutes and seconds.
     * 
     * @param minutes the minutes component
     * @param seconds the seconds component
     */
    public Time(int minutes, int seconds) {
        this.minutes = minutes;
        this.seconds = seconds;
    }

    /**
     * Converts the time to total seconds.
     * 
     * @return the total time in seconds
     */
    public int getTimeInSeconds() {
        return minutes * 60 + seconds;
    }

    /**
     * Gets the minutes component.
     * 
     * @return the minutes
     */
    public int getMinutes() {
        return minutes;
    }

    /**
     * Gets the seconds component.
     * 
     * @return the seconds
     */
    public int getSeconds() {
        return seconds;
    }

    /**
     * Sets the minutes component.
     * 
     * @param minutes the new minutes value
     */
    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    /**
     * Sets the seconds component.
     * 
     * @param seconds the new seconds value
     */
    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }
}
