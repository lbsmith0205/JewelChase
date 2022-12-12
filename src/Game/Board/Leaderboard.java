package Game.Board;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.util.Pair;

/**
 * A Leaderboard can be used to hold the high score of each Level.
 *
 * @author Luke Smith.
 */
public class Leaderboard {
    // level number
    private int level;
    // name and score
    private final ArrayList<Pair<String, Integer>> Placement;

    /**
     * Create an instance of the Leaderboard with a tied in Level.
     *
     * @param level Level that will have the Leaderboard.
     */
    public Leaderboard(int level) {
        this.level = level;
        Placement = new ArrayList<>();
    }

    /**
     * Get the list of the top 10 score of the Level.
     *
     * @return ArrayList of the scores.
     */
    public ArrayList<Pair<String, Integer>> getPlacement() {
        return Placement;
    }

    /**
     * Add a high score to the Leaderboard.
     *
     * @param name name of Player.
     * @param score score Player achieved.
     */
    private void addToLeaderboard(String name, int score) {
        for (int i = 0; i < Placement.size(); i++) {
            int scoreOnLeaderboard = Placement.get(i).getValue();
            if (scoreOnLeaderboard < score) {
                Placement.add(i, new Pair<>(name, score));
                return;
            }
        }
    }

    /**
     * Update the Leaderboard if the list have a new high score.
     *
     * @param name name of Player.
     * @param score high score achieved.
     * @throws IOException if the input is wrong.
     */
    public void updateLeaderboard(String name, int score) throws IOException {
        addToLeaderboard(name, score);

        while (Placement.size() > 10) {
            Placement.remove(10);
        }
        saveLeaderboard();
    }

    /**
     * Save the score to the Level file.
     *
     * @throws IOException if the input is wrong.
     */
    private void saveLeaderboard() throws IOException {
        File Leaderboards = new File("src/Leaderboards/Level" + level + ".txt");

        FileWriter fileWriter = new FileWriter(Leaderboards);
        for (Pair<String, Integer> pairs : Placement) {
            fileWriter.write(pairs.getKey() + " " + pairs.getValue() + "\n\r");
        }
        fileWriter.close();
    }

    /**
     * Get the high scores of the Levels.
     *
     * @throws FileNotFoundException if the File can't be found.
     */
    private void loadLeaderboard() throws FileNotFoundException {
        File Leaderboards = new File("src/Leaderboards/Level" + level + ".txt");
        if (!Leaderboards.exists()) {
            System.out.println("Created Leaderboard File.");
        }

        Scanner fileRead = new Scanner(Leaderboards);
        while(fileRead.hasNextLine()) {
            String[] pairs = fileRead.nextLine().split(" ");
            Placement.add(new Pair<>(pairs[0],Integer.parseInt(pairs[1])));
        }
    }
}

