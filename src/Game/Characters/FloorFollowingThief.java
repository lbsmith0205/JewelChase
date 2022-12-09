package Game.Characters;

import Game.Board.Board;
import Game.Board.Tile;
import Game.Direction;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * FloorFollowingThieves.java
 * Sub class of Character.
 * @author Daniel Baxter, Jack Lewis.
 */

public class FloorFollowingThief extends Thief {
    private static final String FLOOR_THIEF_PATH = "Sprites/Characters/FloorFollowingThief.png";

    private Image floorThiefImage;
    /**
     * Creates an instance of FloorFollowingThief.
     *
     * @param position The Tile on which the Character is located.
     */
    public FloorFollowingThief(Tile position, Direction direction) {
        super(position, direction);
        this.floorThiefImage = new Image(FLOOR_THIEF_PATH);
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(this.floorThiefImage, this.position.getXPosition() * IMAGE_SIZE,
                this.position.getYPosition() * IMAGE_SIZE);
    }

    public void move(Board currentBoard) {

    }
}
