package Game.Items;

import Game.Characters.Player;
import Game.Characters.SmartThief;
import Game.Board.Tile;
import Game.Characters.Character;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Lever extends Item {
    private static final String LEVER_BLUE_SPRITE_PATH = "Sprites/Items/Levers/LeverBlue.png";
    private static final String LEVER_RED_SPRITE_PATH = "Sprites/Items/Levers/LeverRed.png";
    private static final String LEVER_GREEN_SPRITE_PATH = "Sprites/Items/Levers/LeverGreen.png";

    private final Color colorCode;
    private final String leverSprite;
    private final Image leverLook;
    private ArrayList<Gate> linkedGates;

    public Lever (Tile position, Color code) {
        super(position);
        this.colorCode = code;

        if (this.colorCode == Color.BLUE) {
            this.leverSprite = LEVER_BLUE_SPRITE_PATH;
        } else if (this.colorCode == Color.RED) {
            this.leverSprite = LEVER_RED_SPRITE_PATH;
        } else if (this.colorCode == Color.GREEN) {
            this.leverSprite = LEVER_GREEN_SPRITE_PATH;
        } else {
            this.leverSprite = null;
        }

        this.leverLook = new Image(leverSprite);
    }

    public void unlockGate(Gate gate) {
        if(gate.getColorCode() == this.colorCode) {
            gate.remove();
        }
    }

    public void addGateToLever(Gate g) {
        if(g.getColorCode() == this.colorCode) {
            this.linkedGates.add(g);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public Color getColorCode() {
        return this.colorCode;
    }

    @Override
    public void interact(Character c) {
        if(c instanceof SmartThief || c instanceof Player) {
            for (Gate gate : linkedGates) {
                unlockGate(gate);
            }
        }
    }

    @Override
    public void draw(GraphicsContext gc) {
        gc.drawImage(this.leverLook, this.position.getXPosition(), this.position.getYPosition());
    }

}
