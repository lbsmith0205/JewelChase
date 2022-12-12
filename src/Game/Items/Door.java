package Game.Items;

import Game.Board.Tile;

import Game.Characters.Player;
import Game.Characters.SmartThief;
import Game.Characters.Character;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * A Door can be used to exit the Level and end it.
 *
 * @author Khoi Nguyen Cao
 */
public class Door extends Item {
    private static final String DOOR_PATH = "Sprites/Items/Door.png";

    private boolean isOpen = false;

    /**
     * Create an instance of the Door.
     *
     * @param position Tile the Door will be put on.
     */
    public Door(Tile position) {
        super(position);
        refreshImage();
    }

    public void openDoor() {
        this.isOpen = true;
    }

    @Override
    protected void refreshImage() {
        this.image = new Image(DOOR_PATH);


    }
}
