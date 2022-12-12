package Game.Items;

import Game.Board.Tile;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;

/**
 * A Lever can be used to unlock the Gates on the Board that have the same color.
 *
 * @author Khoi Nguyen Cao
 */
public class Lever extends Item {
    private static final String BLUE_LEVER_PATH = "Sprites/Items/Levers/LeverBlue.png";
    private static final String RED_LEVER_PATH = "Sprites/Items/Levers/LeverRed.png";
    private static final String GREEN_LEVER_PATH = "Sprites/Items/Levers/LeverGreen.png";

    private final Color colour;
    private final String leverSprite;

    /**
     * Create an instance of Lever.
     *
     * @param position Tile the Lever will be put on.
     * @param type the Color the Lever is assigned to.
     */
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
