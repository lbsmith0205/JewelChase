package Game.Characters;
import Game.Board.Tile;
import Game.Direction;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Character.java
 * Super class for all player and non player character classes.
 * @author Daniel Baxter, Jack Lewis.
 */
public abstract class Character {
    protected static final int IMAGE_SIZE = 64;

    protected Tile position;
    protected Direction direction;
    protected ArrayList<Color> currentColours;

    /**
     * Creates an instance of Character.
     * @param position The Tile on which the Character is located.
     */
    public Character(Tile position, Direction direction) {
        this.position = position;
        this.direction = direction;
    }

    /**
     * Removes an instance of Character from the game.
     */
    protected void kill() {

    }

    /**
     * Determines whether a move to a proposed tile is legal.
     * @param target The Tile object to which a move is proposed.
     * @return boolean, true if move is legal, false otherwise.
     */
    protected boolean isLegalMove(Tile target) {
        for (Color currentColour : currentColours) {
            for (Color targetColour : target.getTileColours()) {
                if (targetColour == currentColour) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setPosition(Tile newPosition) {
        this.position = newPosition;
    }

    public Tile getPosition() {
        return this.position;
    }

    public abstract void draw(GraphicsContext gc);
}

