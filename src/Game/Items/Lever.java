package Game.Items;

import Game.Characters.Player;
import Game.Characters.SmartThief;
import Game.Board.Tile;
import Game.Characters.Character;
import javafx.scene.paint.Color;
import java.util.ArrayList;

public class Lever extends Item {
    private final Color colorCode;
    private ArrayList<Gate> linkedGates;

    public Lever (Tile position, Color code) {
        super(position);
        this.colorCode = code;
    }

    public void unlockGate(Gate gate) {
        if(gate.getColorCode() == this.colorCode) {
            gate.remove();
        }
    }

    public void addGateToLever(Gate g) {
        this.linkedGates.add(g);
    }

    @Override
    public void interact(Character c) {
        if(c instanceof SmartThief || c instanceof Player) {
            for (Gate gate : linkedGates) {
                unlockGate(gate);
            }
        }
    }

    public Color getColorCode() {
        return this.colorCode;
    }
}
