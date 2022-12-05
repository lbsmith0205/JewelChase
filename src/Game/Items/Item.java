package Game.Items;

import Game.Tile;
import Game.Characters.Character;

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
        exist = false;
    }


    public abstract void interact(Character c);
}
