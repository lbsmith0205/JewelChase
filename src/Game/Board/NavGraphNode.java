package Game.Board;

import java.util.ArrayList;

/**
 * Class representing a node in the NavGraph for implementation of BFS Algorithm
 */
public class NavGraphNode {
    private final Tile tile;
    private final ArrayList<NavGraphNode> accessibleNodes = new ArrayList<>();
    private boolean hasBeenVisited = false;

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

    public void visit() {
        hasBeenVisited = true;
    }

    public void unVisit() {
        hasBeenVisited = false;
    }

    public boolean getHasBeenVisited() {
        return hasBeenVisited;
    }





}
