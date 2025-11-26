package en.tresz.spaceinvaders.util;

/**
 * Represents a player's score with their name and completion time.
 */
public class Score {

    private String playerName;
    private Time time;

    /**
     * Constructs a Score with player name and time.
     * 
     * @param playerName the name of the player
     * @param time       the time achieved
     */
    public Score(String playerName, Time time) {
        this.playerName = playerName;
        this.time = time;
    }

    /**
     * Gets the player's name.
     * 
     * @return the player name
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Gets the time achieved.
     * 
     * @return the time
     */
    public Time getTime() {
        return time;
    }

}
