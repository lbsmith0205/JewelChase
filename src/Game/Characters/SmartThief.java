package Game.Characters;

import Game.Board.Board;
import Game.Board.NavGraph;
import Game.Board.Tile;
import Game.Direction;

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

    }
}
