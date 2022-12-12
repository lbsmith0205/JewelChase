package Game.Board;

import Game.Direction;

import java.util.Collections;

/**
 * NavGraph.java
 *
 * @author Daniel Baxter
 */

public class NavGraph {
    private final NavGraphNode[][] nodes;

    /**
     * Creates a new instance of NavGraph.
     *
     * @param board The board of Tiles on which the NavGraph is based.
     */
    public NavGraph(Board board) {
        nodes = new NavGraphNode[board.getWidth()][board.getHeight()];

        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                nodes[x][y] = new NavGraphNode(board.getTile(x, y));
            }
        }

        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; y < board.getHeight(); y++) {
                Tile sourceTile = board.getTile(x, y);
                nodes[x][y].addAccessibleNode(findAccessibleNode(board, Direction.UP, sourceTile, 1));
                nodes[x][y].addAccessibleNode(findAccessibleNode(board, Direction.LEFT, sourceTile, 1));
                nodes[x][y].addAccessibleNode(findAccessibleNode(board, Direction.DOWN, sourceTile, 1));
                nodes[x][y].addAccessibleNode(findAccessibleNode(board, Direction.RIGHT, sourceTile, 1));
                nodes[x][y].getAccessibleNodes().removeAll(Collections.singleton(null));
            }
        }
    }

    /**
     * Recursive method returns the next accessible node in a specified direction from the source.
     *
     * @param board The board of Tiles on which the NavGraph is based.
     * @param d The direction in which to search.
     * @param source The Tile from which to begin the search.
     * @return NavGraphNode, the nearest accessible node in specified direction, null if no accessible node found.
     */
    private NavGraphNode findAccessibleNode(Board board, Direction d, Tile source, int targetDistance) {
        Tile accessibleTile = board.findAccessibleTile(d, source, 1);
        if (accessibleTile != null) {
            return getNode(accessibleTile);
        }
        return null;
    }

    public NavGraphNode getNode(int x, int y) {
        return nodes[x][y];
    }

    public NavGraphNode getNode(Tile tile) {
        int x = tile.getXPosition();
        int y = tile.getYPosition();
        return nodes[x][y];
    }

    public void unvisitAll() {
        for (NavGraphNode[] rowOfNodes :  nodes) {
            for (NavGraphNode node : rowOfNodes) {
                node.unVisit();
            }
        }
    }

    public int getSize() {
        int size = 0;
        for (NavGraphNode[] rowOfNodes : nodes) {
            size += rowOfNodes.length;
        }
        return size;
    }
}
