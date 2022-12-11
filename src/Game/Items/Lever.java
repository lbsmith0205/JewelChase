package Game.Items;

import Game.Board.Tile;
import Game.Characters.Character;
import Game.Characters.Thief;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Lever extends Item {
    private static final String BLUE_LEVER_PATH = "Sprites/Items/Levers/LeverBlue.png";
    private static final String RED_LEVER_PATH = "Sprites/Items/Levers/LeverRed.png";
    private static final String GREEN_LEVER_PATH = "Sprites/Items/Levers/LeverGreen.png";

    private final Color colour;
    private final String leverSprite;


    public Lever(Tile position, String type) {
        super(position);

        switch (type) {
            case "RL" -> {
                this.colour = Color.RED;
                this.leverSprite = RED_LEVER_PATH;
            }
            case "GL" -> {
                this.colour = Color.GREEN;
                this.leverSprite = GREEN_LEVER_PATH;
            }
            case "BL" -> {
                this.colour = Color.BLUE;
                this.leverSprite = BLUE_LEVER_PATH;
            }
            default -> {
                this.colour = null;
                this.leverSprite = null;
            }
        }
        refreshImage();

    }

    public Color getColour() {
        return this.colour;
    }


    @Override
    protected void refreshImage() {
        this.image = new Image(leverSprite);

    }

}
