package Game.Board;

import java.util.ArrayList;

/**
 * Class representing a node in the NavGraph for implementation of A* Algorithm
 */
public class NavGraphNode {
    private final Tile tile;
    private ArrayList<NavGraphNode> accessibleNodes = new ArrayList<NavGraphNode>();

    public NavGraphNode(Tile tile) {
        this.tile = tile;
        populateAccessibleNodes();
    }

    /**
     * populates accessibleNodes with all
     */
    private void populateAccessibleNodes() {

    }
}
