package Game.Board;

import Game.Direction;

import java.util.ArrayList;

/**
 * Class representing a node in the NavGraph for implementation of A* Algorithm
 */
public class NavGraphNode {
    private final Tile tile;
    private ArrayList<NavGraphNode> accessibleNodes = new ArrayList<NavGraphNode>();

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
