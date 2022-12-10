package Game.Items;

import Game.Board.Tile;
import Game.Characters.Character;

import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;

import java.awt.*;

/**
 * Item class, used to hold information about it
 *
 * @author Khoi Nguyen Cao
 */
public abstract class Item {
    protected static final int IMAGE_SIZE = 64;

    protected final Tile position;
    protected boolean exist;
    protected boolean contact = false;

    public Item(Tile pos) {
        this.position = pos;
        this.exist = true;

    }

    /**
     * Used to remove an Item from the Tile and delete itself
     */
    protected void remove() {
        this.exist = false;
        this.position.removeObjectFromTile(this);
    }

    public String getTypeInString() {
        if (this instanceof Door) {
            return "D";
        } else if (this instanceof Bomb) {
            return "Bo";
        } else if (this instanceof Clock) {
            return "Cl";
        } else if (this instanceof Lever) {
            Color colour = ((Lever) this).getColour();
            if (colour == Color.BLUE) {
                return "BL";
            } else if (colour == Color.RED) {
                return "RL";
            } else if (colour == Color.GREEN) {
                return "GL";
            }
        } else if (this instanceof Gate) {
            Color colour = ((Gate) this).getColour();
            if (colour == Color.BLUE) {
                return "BGt";
            } else if (colour == Color.RED) {
                return "RGt";
            } else if (colour == Color.GREEN) {
                return "GGt";
            }
        } else if (this instanceof Loot) {
            String type = ((Loot) this).getLootType();
            return type;
        }
        return null;
    }

    public Tile getPosition() {
        return position;
    }

    /**
     * Uses GraphicContext to draw itself
     *
     * @param gc GraphicContext buffer from the Canvas
     */
    public abstract void draw(GraphicsContext gc);
}
