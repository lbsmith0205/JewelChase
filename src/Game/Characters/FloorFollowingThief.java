package Game.Characters;

import Game.Board.Board;
import Game.Board.Tile;
import Game.Direction;

/**
 * FloorFollowingThieves.java
 * Sub class of Character.
 * @author Daniel Baxter, Jack Lewis.
 */

public class FloorFollowingThief extends Thief {

    /**
     * Creates an instance of FloorFollowingThief.
     *
     * @param position The Tile on which the Character is located.
     */
    public FloorFollowingThief(Tile position, Direction direction) {
        super(position, direction);
    }

    public void move(Board currentBoard) {

    }
}
