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
 * @author Daniel Baxter.
 */
public abstract class Character {
    protected static final int IMAGE_SIZE = 64;

    protected Tile position;
    protected Direction direction;
    protected ArrayList<Color> currentColours;
    protected Image image;

    /**
     * Creates an instance of Character.
     * @param position The Tile on which the Character is located.
     */
    public Character(Tile position, Direction direction) {
        this.position = position;
        this.direction = direction;
    }

    /**
     *
     *
     * @return
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

    public void setPosition(Tile newPosition) {
        this.position = newPosition;
    }

    public Tile getPosition() {
        return this.position;
    }

    public void draw(GraphicsContext gc) {
        gc.drawImage(this.image, this.position.getXPosition() * IMAGE_SIZE,
                this.position.getYPosition() * IMAGE_SIZE);
    }

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

    public abstract void move(Board currentBoard);

    protected abstract void refreshImage();



}

