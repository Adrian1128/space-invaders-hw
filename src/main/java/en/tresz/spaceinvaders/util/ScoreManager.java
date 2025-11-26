package en.tresz.spaceinvaders.util;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

import java.lang.reflect.Type;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/**
 * Manages saving and loading of high scores using JSON serialization.
 */
public class ScoreManager {

    private int maxPlacements = 5;

    private static final String FILE_PATH = "src/main/java/en/tresz/spaceinvaders/json/scores.json";
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Constructs a ScoreManager with a custom maximum number of placements.
     * 
     * @param maxPlacements the maximum number of scores to keep
     */
    public ScoreManager(int maxPlacements) {
        this.maxPlacements = maxPlacements;
    }

    /**
     * Constructs a ScoreManager with default maximum placements (5).
     */
    public ScoreManager() {
    }
    
    /**
     * Adds a score to the leaderboard in order.
     * Updates existing player scores if the new time is better.
     * 
     * @param score the score to add
     */
    public void addScore(Score score) {
        List<Score> scores = loadScores();
        List<String> playerNames = new ArrayList<>();
        List<Time> times = new ArrayList<>();

        for (Score s : scores) {
            playerNames.add(s.getPlayerName());
            times.add(s.getTime());
        }

        if (playerNames.contains(score.getPlayerName())
                && times.get(playerNames.indexOf(score.getPlayerName())).getTimeInSeconds() < score.getTime()
                        .getTimeInSeconds()) {
            return;
        } else if (playerNames.contains(score.getPlayerName())) {
            scores.removeIf(s -> s.getPlayerName().equals(score.getPlayerName()));

        }

        scores.add(score);

        if (scores.size() > maxPlacements) {
            orderScores(scores);
            scores = scores.subList(0, maxPlacements);
        }

        saveScores(scores);
    }

    /**
     * Loads scores from the JSON file.
     * 
     * @return a list of scores, or an empty list if the file doesn't exist
     */
    public List<Score> loadScores() {
        File file = new File(FILE_PATH);

        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (Reader reader = new FileReader(file)) {
            Type listType = new TypeToken<List<Score>>() {
            }.getType();
            List<Score> scores = gson.fromJson(reader, listType);
            return scores != null ? scores : new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Error reading scores from file: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    /**
     * Saves scores to the JSON file.
     * 
     * @param scores the list of scores to save
     */
    private void saveScores(List<Score> scores) {
        try (Writer writer = new FileWriter(FILE_PATH)) {
            gson.toJson(scores, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Orders scores by time in ascending order (fastest first).
     * 
     * @param scores the list of scores to order
     */
    public void orderScores(List<Score> scores) {
        scores.sort((s1, s2) -> Integer.compare(s1.getTime().getTimeInSeconds(), s2.getTime().getTimeInSeconds()));
    }
}
