package Game.Items;

import Game.Board.Tile;
import javafx.scene.paint.Color;
import Game.Characters.Character;

public class Gate extends Item{
    private static final String BLUE_GATE_PATH = "Sprites/Items/Gates/GateBlue.png";
    private static final String RED_GATE_PATH = "Sprites/Items/Gates/GateRed.png";
    private static final String GREEN_GATE_PATH = "Sprites/Items/Gates/GateGreen.png";

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
