package Game.Board;

import java.util.ArrayList;
import javafx.util.Pair;

public class Leaderboard {
    private int level;
    // name and score
    private final ArrayList<Pair<String, Integer>> Placement;

    public Leaderboard(int level){
        this.level = level;
        Placement = new ArrayList<>();
    }

    public ArrayList<Pair<String, Integer>> getPlacement() {
        return Placement;
    }

}
