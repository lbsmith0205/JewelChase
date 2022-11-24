package Game;
import java.util.ArrayList;
import javafx.scene.paint.Color;

/**
 * Character.java
 * Super class for all player and non player character classes.
 * @author Dan.
 */
public abstract class Character {
    protected int x;
    protected int y;
    protected boolean doesExist;
    protected ArrayList<Color> currentColours;

    /**
     * Creates an instance of Character.
     * @param x The character's x coordinate.
     * @param y The character's y coordinate.
     */
    public Character(int x, int y) {
        this.x = x;
        this.y = y;
        this.doesExist = true;
    }

    /**
     * Removes an instance of Character from the game.
     */
    protected void kill() {
        this.doesExist = false;
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

    public abstract void move();
}

