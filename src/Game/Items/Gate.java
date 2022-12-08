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

    private final Color colorCode;
    private final Lever linkedLever;

    private String gateSprite;
    private Image gateLook;

    public Gate(Tile position, Lever lever) {
        super(position);
        this.colorCode = lever.getColorCode();

        this.linkedLever = lever;
        linkedLever.addGateToLever(this);

        if (this.colorCode == Color.BLUE) {
            this.gateSprite = BLUE_GATE_PATH;
        } else if (this.colorCode == Color.RED) {
            this.gateSprite = RED_GATE_PATH;
        } else if (this.colorCode == Color.GREEN) {
            this.gateSprite = GREEN_GATE_PATH;
        } else {
            this.gateSprite = null;
        }

        this.gateLook = new Image(gateSprite);
    }

    public Color getColorCode() {
        return this.colorCode;
    }


    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(this.gateLook, this.position.getXPosition(), this.position.getYPosition());
    }
}
