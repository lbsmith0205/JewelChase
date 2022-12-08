package Game.Characters;

import Game.Board.Board;
import Game.Board.NavGraph;
import Game.Board.Tile;

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
    public SmartThief(Tile position) {
        super(position);
    }

    @Override
    public void move(Board currentBoard) {

    }
}
