package Game.Characters;

import Game.Board.Tile;

public abstract class Thief extends Character {
    /**
     * Creates an instance of Thief.
     *
     * @param position The Tile on which the Character is located.
     */
    public Thief(Tile position) {
        super(position);
    }
}

