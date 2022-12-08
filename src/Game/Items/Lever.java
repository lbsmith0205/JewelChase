package Game.Items;

import javafx.scene.paint.Color;

public class Lever extends Item {
    private final Color colorCode;

    public Lever (int x, int y, Color code) {
        super(x,y);
        this.colorCode = code;
    }

    public void unlockGate(Gate gate) {

    }

    @Override
    public void interact() {

    }

    public Color getColorCode() {
        return this.colorCode;
    }
}
