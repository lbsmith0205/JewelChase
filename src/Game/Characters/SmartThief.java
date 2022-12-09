package Game.Characters;

import Game.Board.Board;
import Game.Board.NavGraph;
import Game.Board.Tile;
import Game.Direction;

import java.util.ArrayList;

/**
 * SmartThief.java
 * Sub class of Character.
 * @author Daniel Baxter
 */

public class SmartThief extends Thief {
    /**
     * Creates an instance of Character.
     *
     * @param position The Tile on which the Character is located.
     */
    public SmartThief(Tile position, Direction direction) {
        super(position, direction);
    }

    public void move(Board currentBoard) {
        NavGraph navigableRoutes = currentBoard.getNavGraph();


    }

    private Tile optimalMove(ArrayList<ArrayList<Tile>> availableRoutes) {
        for (ArrayList<Tile> route : availableRoutes) {
            if (terminus(route).hasLoot()) {
                return route.get(0);
            }
        }
        return null;
    }

    private Tile terminus(ArrayList<Tile> route) {
        return route.get(route.size() - 1);
    }




}
