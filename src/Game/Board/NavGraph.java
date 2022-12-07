package Game.Board;

import Game.Direction;

public class NavGraph {
    private NavGraphNode[][] nodes;

    public NavGraph(Board board) {
        nodes = new NavGraphNode[board.getWidth()][board.getHeight()];

        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; x < board.getWidth(); y++) {
                nodes[x][y] = new NavGraphNode(board.getTile(x, y));
            }
        }

        for (int x = 0; x < board.getWidth(); x++) {
            for (int y = 0; x < board.getWidth(); y++) {

            }
        }
    }

    private NavGraphNode findAccessibleNode(Direction d, Tile source) {
        try {
            switch (d) {
                case LEFT:
                    break;
                case RIGHT:
                    break;
                    case
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

}
