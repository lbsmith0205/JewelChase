package Game.Items;

import Game.Board.Tile;

import Game.Characters.Player;
import Game.Characters.SmartThief;
import Game.Characters.Character;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Door extends Item {
    private static final String DOOR_PATH = "Sprites/Items/Door.png";

    private boolean isOpen = false;

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
