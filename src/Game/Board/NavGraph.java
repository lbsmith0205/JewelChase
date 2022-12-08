package Game.Board;

import Game.Direction;

import java.util.Collections;
/*
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
            for (int y = 0; x < board.getWidth(); y++) {
                nodes[x][y] = new NavGraphNode(board.getTile(x, y));
            }
        }

        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; x < board.getHeight(); y++) {
                Tile sourceTile = board.getTile(x, y);
                nodes[x][y].addAccessibleNode(findAccessibleNode(board, Direction.UP, sourceTile));
                nodes[x][y].addAccessibleNode(findAccessibleNode(board, Direction.LEFT, sourceTile));
                nodes[x][y].addAccessibleNode(findAccessibleNode(board, Direction.DOWN, sourceTile));
                nodes[x][y].addAccessibleNode(findAccessibleNode(board, Direction.RIGHT, sourceTile));
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
    private NavGraphNode findAccessibleNode(Board board, Direction d, Tile source) {
        try {
            int x = source.getXPosition();
            int y = source.getYPosition();
            switch (d) {
                case UP -> y--;
                case LEFT -> x--;
                case DOWN -> y++;
                case RIGHT -> x++;
            }

            Tile target = board.getTile(x,y);
            if (board.isLegalMove(source, target)) {
                return nodes[x][y];
            } else {
                return findAccessibleNode(board, d, target);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

}
