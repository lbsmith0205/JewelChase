package Game.Items;

import Game.Characters.Character;
import Game.Board.Tile;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;

/**
 * A Gate can be used to lock a Tile out of movement.
 *
 * @author Khoi Nguyen Cao
 */
public class Gate extends Item {
    private static final String BLUE_GATE_PATH = "Sprites/Items/Gates/GateBlue.png";
    private static final String RED_GATE_PATH = "Sprites/Items/Gates/GateRed.png";
    private static final String GREEN_GATE_PATH = "Sprites/Items/Gates/GateGreen.png";

    private final String type;
    private final String gateSprite;

    /**
     * Create an instance of Gate.
     *
     * @param position Tile the Gate will be put on.
     * @param type the colour the Gate is assigned to.
     */
    public Gate(Tile position, String type) {
        super(position);
        this.type = type;
        switch (type) {
            case "RGt" -> {
                this.gateSprite = RED_GATE_PATH;
            }
            case "GGt" -> {
                this.gateSprite = GREEN_GATE_PATH;
            }
            case "BGt" -> {
                this.gateSprite = BLUE_GATE_PATH;
            }
            default -> {
                this.gateSprite = null;
            }
        }
        refreshImage();

    }

    @Override
    protected void refreshImage() {
        this.image = new Image(gateSprite);


    }

    public String getType() {
        return this.type;
    }
}
