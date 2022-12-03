package Game.Items;

import Game.Tile;
import javafx.scene.paint.Color;

public class Gate extends Item{
    private final Color colorCode;
    private final Lever linkedLever;

    public Gate (Tile position, Lever lever) {
        super(position);
        this.linkedLever = lever;
        this.colorCode = lever.getColorCode();
    }

    public Color getColorCode() { return this.colorCode;}

    @Override
    public void interact() {

    }
}
