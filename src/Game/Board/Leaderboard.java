package Game.Board;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javafx.util.Pair;

public class Leaderboard {
    // level number
    private int level;
    // name and score
    private final ArrayList<Pair<String, Integer>> Placement;

    public Leaderboard(int level) {
        this.level = level;
        Placement = new ArrayList<>();
    }

    public ArrayList<Pair<String, Integer>> getPlacement() {
        return Placement;
    }

    private void addToLeaderboard(String name, int score) {
        for (int i = 0; i < Placement.size(); i++) {
            int scoreOnLeaderboard = Placement.get(i).getValue();
            if (scoreOnLeaderboard < score) {
                Placement.add(i, new Pair<>(name, score));
                return;
            }
        }
    }

    public void updateLeaderboard(String name, int score) throws IOException {
        addToLeaderboard(name, score);

        while (Placement.size() > 10) {
            Placement.remove(10);
        }
        saveLeaderboard();
    }

    private void saveLeaderboard() throws IOException {
        File Leaderboards = new File("src/Leaderboards/Level" + level + ".txt");

        FileWriter fileWriter = new FileWriter(Leaderboards);
        for (Pair<String, Integer> pairs : Placement) {
            fileWriter.write(pairs.getKey() + " " + pairs.getValue() + "\n\r");
        }
        fileWriter.close();
    }

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

