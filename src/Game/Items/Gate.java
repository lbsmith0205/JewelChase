package Game.Items;

import Game.Characters.Character;
import Game.Board.Tile;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;

public class Gate extends Item {
    private static final String BLUE_GATE_PATH = "Sprites/Items/Gates/GateBlue.png";
    private static final String RED_GATE_PATH = "Sprites/Items/Gates/GateRed.png";
    private static final String GREEN_GATE_PATH = "Sprites/Items/Gates/GateGreen.png";

    private final Color colour;


    private final String gateSprite;

    public Gate(Tile position, String type) {
        super(position);

        switch (type) {
            case "RGt" -> {
                this.colour = Color.RED;
                this.gateSprite = RED_GATE_PATH;
            }
            case "GGt" -> {
                this.colour = Color.GREEN;
                this.gateSprite = GREEN_GATE_PATH;
            }
            case "BGt" -> {
                this.colour = Color.BLUE;
                this.gateSprite = BLUE_GATE_PATH;
            }
            default -> {
                this.colour = null;
                this.gateSprite = null;
            }
        }
        refreshImage();

    }

    public Color getColour() {
        return colour;
    }

    @Override
    protected void refreshImage() {
        this.image = new Image(gateSprite);


    }
}
