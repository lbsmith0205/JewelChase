package Game.Characters;
import Game.Board.Tile;
import Game.Direction;
import javafx.application.Platform;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
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
}

