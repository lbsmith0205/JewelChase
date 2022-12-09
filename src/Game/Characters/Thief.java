package Game.Characters;

import Game.Board.Tile;
import Game.Direction;
import javafx.scene.canvas.GraphicsContext;

public abstract class Thief extends Character {
    /**
     * Creates an instance of Thief.
     *
     * @param position The Tile on which the Character is located.
     */
    public Thief(Tile position, Direction direction) {
        super(position, direction);
    }

    @Override
    public abstract void draw(GraphicsContext gc);
}

