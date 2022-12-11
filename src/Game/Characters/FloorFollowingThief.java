package Game.Characters;

import Game.Board.Board;
import Game.Board.Tile;
import Game.Direction;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

/**
 * FloorFollowingThieves.java
 * Sub class of Character.
 * @author Daniel Baxter, Jack Lewis.
 */

public class FloorFollowingThief extends Thief {
    private String FLOOR_THIEF_PATH = "Sprites/Characters/FFT/Floor_Following_Thief_" + direction.name() +".png";
    private Color followingColour;

    /**
     * Creates an instance of FloorFollowingThief.
     *
     * @param position The Tile on which the Character is located.
     */
    public FloorFollowingThief(Tile position, Direction direction) {
        super(position, direction);
        this.image = new Image(FLOOR_THIEF_PATH);
    }

    public void setFollowingColour(Color colour) {
        this.followingColour = colour;
    }
    @Override
    public void move(Board currentBoard) {

    }
}
