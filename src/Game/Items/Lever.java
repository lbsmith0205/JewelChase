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
    private final Image leverImage;

    public Lever(Tile position, String type) {
        super(position);

        switch (type) {
            case "RL":
                this.colour = Color.RED;
                this.leverSprite = RED_LEVER_PATH;
                break;
            case "GL":
                this.colour = Color.GREEN;
                this.leverSprite = GREEN_LEVER_PATH;
                break;
            case "BL":
                this.colour = Color.BLUE;
                this.leverSprite = BLUE_LEVER_PATH;
                break;
            default:
                this.colour = null;
                this.leverSprite = null;
        }

        this.leverImage = new Image(leverSprite);
    }

    public Color getColour() {
        return this.colour;
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(this.leverImage, this.position.getXPosition(), this.position.getYPosition());
    }

}
