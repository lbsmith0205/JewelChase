package Game.Characters;

import Game.Board.Board;
import Game.Board.Tile;
import Game.Direction;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Collections;

/**
 * FloorFollowingThieves.java
 * Sub class of Character.
 * @author Daniel Baxter, Jack Lewis.
 */

public class FloorFollowingThief extends Thief {

    private String pathToImage = "Sprites/Characters/FFT/Floor_Following_Thief_" + direction.name() +".png";
    private Color followingColour;

    /**
     * Creates an instance of FloorFollowingThief.
     *
     * @param position The Tile on which the Character is located.
     */
    public FloorFollowingThief(Tile position, Direction direction) {
        super(position, direction);
        refreshImage();
    }

    public void setFollowingColour(Color colour) {
        this.followingColour = colour;
    }
    @Override
    public void move(Board board) {
        ArrayList<Tile> validTiles = new ArrayList<>();
        validTiles.add(board.getAdjacentTileOfRequiredColour(position, direction.turnLeft(), followingColour));
        validTiles.add(board.getAdjacentTileOfRequiredColour(position, direction, followingColour));
        validTiles.add(board.getAdjacentTileOfRequiredColour(position, direction.turnRight(), followingColour));
        validTiles.add(board.getAdjacentTileOfRequiredColour(position, direction.turnBack(), followingColour));
        validTiles.removeAll(Collections.singleton(null));
        if (!validTiles.isEmpty()) {
            Tile target = validTiles.get(0);
            direction = getNewDirection(position, target);
            refreshImage();
            position.removeObjectFromTile(this);
            this.position = target;
            position.addObjectToTile(this);
        }
    }

    @Override
    protected void refreshImage() {
        pathToImage = "Sprites/Characters/FFT/Floor_Following_Thief_" + direction.name() +".png";
        this.image = new Image(pathToImage);
    }

}
