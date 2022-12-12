package Game.Characters;
import Game.Board.Board;
import Game.Board.Tile;
import Game.Direction;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * A Character can be used to hold its position and manage its movement.
 *
 * @author Daniel Baxter, Khoi Nguyen Cao.
 */
public abstract class Character {
    protected static final int IMAGE_SIZE = 64;

    protected Tile position;
    protected Direction direction;
    protected ArrayList<Color> currentColours;
    protected Image image;

    /**
     * Creates an instance of Character.
     *
     * @param position The Tile on which the Character is located.
     */
    public Character(Tile position, Direction direction) {
        this.position = position;
        this.direction = direction;
    }

    /**
     * Convert the type of Character to a String.
     *
     * @return type of Character in String.
     */
    public String getTypeInString() {
        if(this instanceof Player) {
            return "P";
        } else if(this instanceof FlyingAssassin) {
            return "FA";
        } else if(this instanceof FloorFollowingThief) {
            return "FFT";
        } else if(this instanceof SmartThief) {
            return "ST";
        }
        return null;
    }

    /**
     * Convert the current direction of Character to a String.
     *
     * @return direction as String.
     */
    public String getDirectionInString() {
        if(this.direction == Direction.UP) {
            return "U";
        } else if(this.direction == Direction.DOWN) {
            return "D";
        } else if(this.direction == Direction.LEFT) {
            return "L";
        } else if(this.direction == Direction.RIGHT) {
            return "R";
        }
        return null;
    }

    /**
     * Set the position of the Character.
     *
     * @param newPosition new position(Tile) for Character
     */
    public void setPosition(Tile newPosition) {
        this.position = newPosition;
    }

    /**
     * Get the current position Character is on.
     *
     * @return position of Character in Tile.
     */
    public Tile getPosition() {
        return this.position;
    }

    /**
     * Draw the Character.
     *
     * @param gc Graphic Context buffer to draw on the Canvas.
     */
    public void draw(GraphicsContext gc) {
        gc.drawImage(this.image, this.position.getXPosition() * IMAGE_SIZE,
                this.position.getYPosition() * IMAGE_SIZE);
    }

    /**
     * Calculate the direction that the Character should be facing based on the tiles it is traveling between.
     *
     * @param source Tile Character is on.
     * @param target Tile Character want to move to.
     * @return Direction to turn to.
     */
    protected Direction getNewDirection(Tile source, Tile target) {
        int proposedXTravel = Math.round(Math.signum(target.getXPosition() - source.getXPosition()));
        int proposedYTravel = Math.round(Math.signum(target.getYPosition() - source.getYPosition()));
        Direction d = null;
        switch (proposedXTravel) {
            case 1 -> d = Direction.RIGHT;
            case -1 -> d = Direction.LEFT;
        }
        switch (proposedYTravel) {
            case 1 -> d = Direction.DOWN;
            case -1 -> d = Direction.UP;
        }
        return d;
    }

    /**
     * Move the Character from one Tile to another.
     *
     * @param currentBoard Board Character is on.
     */
    public abstract void move(Board currentBoard);

    /**
     * Redraw the Character to fit the current Direction and Tile it is in.
     */
    protected abstract void refreshImage();



}

