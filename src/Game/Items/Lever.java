package Game.Items;

import Game.Tile;
import javafx.scene.paint.Color;

public class Lever extends Item {
    private final Color colorCode;

    public Lever (Tile position, Color code) {
        super(position);
        this.colorCode = code;
    }

    public void unlockGate(Gate gate) {
        if(gate.getColorCode() == this.colorCode) {
            gate.remove();
        }
    }

    @Override
    public void interact() {

    }

    public Color getColorCode() {
        return this.colorCode;
    }
}
