package Game.Items;

import Game.Board.Tile;

import javafx.scene.paint.Color;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * An Item class can be used to hold information about it.
 *
 * @author Khoi Nguyen Cao
 */
public abstract class Item {
    protected static final int IMAGE_SIZE = 64;

    protected final Tile position;
    protected Image image;
    protected boolean exist;
    protected boolean contact = false;

    /**
     * Create an instance of an Item.
     *
     * @param pos Tile the Item will be put on.
     */
    public Item(Tile pos) {
        this.position = pos;
        this.exist = true;

    }

    /**
     * Remove an Item from the Tile and delete itself.
     */
    protected void remove() {
        this.exist = false;
        this.position.removeObjectFromTile(this);
    }

    /**
     * Convert the type of Item into a String.
     *
     * @return type of Item in String.
     */
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
            return ((Gate) this).getType();
        } else if (this instanceof Loot) {
            String type = ((Loot) this).getLootType();
            return type;
        }
        return null;
    }

    /**
     * Get the position of the Item on the board.
     *
     * @return Tile the Item is on.
     */
    public Tile getPosition() {
        return position;
    }

    /**
     * Draw the item.
     *
     * @param gc Graphic Context buffer to draw on Canvas.
     */
    public void draw(GraphicsContext gc) {
        gc.drawImage(this.image, this.position.getXPosition() * IMAGE_SIZE,
                this.position.getYPosition() * IMAGE_SIZE);
    }

    /**
     * Change the Image of the Item according to its state
     */
    protected abstract void refreshImage();
}
