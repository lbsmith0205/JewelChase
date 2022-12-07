package Game.Items;

import Game.Board.Tile;
import Game.Board.Board;

import Game.Characters.Player;
import Game.Characters.SmartThief;
import Game.Characters.Character;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Door extends Item {
    private static final String DOOR_SPRITE_PATH = "Sprites/Items/Door.png";

    private final Board boardIn;
    private final Image doorLook;

    private boolean isOpen;

    public Door(Tile position, Board board) {
        super(position);
        this.boardIn = board;
        this.isOpen = false;
        this.doorLook = new Image(DOOR_SPRITE_PATH);
    }

    public void openDoor() {
        this.isOpen = true;
    }

    public void closeDoor() {
        this.isOpen = false;
    }

    @Override
    public void interact(Character c) {
        if(this.isOpen) {
            if (c instanceof SmartThief) {
                //Lose
            } else if (c instanceof Player) {
                //WIN
            }
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(this.doorLook, this.position.getXPosition(), this.position.getYPosition());
    }
}
