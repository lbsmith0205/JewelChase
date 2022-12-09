package Game.Items;

import Game.Board.Tile;
import Game.Characters.Character;
import javafx.scene.canvas.GraphicsContext;

/**
 * Item class, used to hold information about it
 * @author Khoi Nguyen Cao
 */
public abstract class Item {

    protected final Tile position;
    protected final int xCoord;
    protected final int yCoord;
    protected boolean exist;
    protected boolean contact = false;

    public Item (Tile pos) {
        this.position = pos;
        this.exist = true;
        this.xCoord = position.getXPosition();
        this.yCoord = position.getYPosition();
    }

    /**
     * Used to remove an Item from the Tile and delete itself
     */
    protected void remove() {
        this.exist = false;
        this.position.removeObjectFromTile(this);
    }

    public Tile getPosition() {
        return position;
    }

    /**
     * Uses GraphicContext to draw itself
     * @param gc GraphicContext buffer from the Canvas
     */
    public abstract void draw(GraphicsContext gc);
}
