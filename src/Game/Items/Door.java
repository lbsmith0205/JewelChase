package Game.Items;

import Game.Board.Tile;

import Game.Characters.Player;
import Game.Characters.SmartThief;
import Game.Characters.Character;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Door extends Item {
    private static final String DOOR_PATH = "Sprites/Items/Door.png";

    private final Image doorImage;
    private boolean isOpen = false;

    public Door(Tile position) {
        super(position);
        this.doorImage = new Image(DOOR_PATH);
    }

    public void openDoor() {
        this.isOpen = true;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(this.doorImage, this.position.getXPosition() * IMAGE_SIZE, this.position.getYPosition() * IMAGE_SIZE);
    }
}
