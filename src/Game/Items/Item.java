package Game.Items;

import Game.Board.Tile;
import Game.Characters.Character;
import javafx.scene.canvas.GraphicsContext;

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

    protected void remove() {
        this.exist = false;
        this.position.removeObjectFromTile(this);
    }


    public abstract void interact(Character c);

    public abstract void draw(GraphicsContext gc);
}
