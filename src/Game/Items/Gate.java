package Game.Items;

import Game.Tile;
import javafx.scene.paint.Color;
import Game.Characters.Character;

public class Gate extends Item{
    private final Color colorCode;
    private final Lever linkedLever;

    public Gate (Tile position, Lever lever) {
        super(position);
        this.colorCode = lever.getColorCode();

        this.linkedLever = lever;
        linkedLever.addGateToLever(this);
    }

    public Color getColorCode() { return this.colorCode;}

    @Override
    public void interact(Character c) {
    }
}
