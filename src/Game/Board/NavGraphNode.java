package Game.Board;

import java.util.ArrayList;

/**
 * Class representing a node in the NavGraph for implementation of BFS Algorithm
 */
public class NavGraphNode {
    private final Tile tile;
    private final ArrayList<NavGraphNode> accessibleNodes = new ArrayList<>();

    public NavGraphNode(Tile tile) {
        this.tile = tile;
    }

    public void addAccessibleNode(NavGraphNode node) {
        accessibleNodes.add(node);
    }

    public Tile getTile() {
        return this.tile;
    }

    public ArrayList<NavGraphNode> getAccessibleNodes() {
        return this.accessibleNodes;
    }





}
