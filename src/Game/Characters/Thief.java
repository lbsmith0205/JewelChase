package Game.Characters;

import Game.Board.Tile;
import Game.Items.Lever;
import Game.Items.Loot;


public abstract class Thief extends Character {
    /**
     * Creates an instance of Thief.
     *
     * @param position The Tile on which the Character is located.
     */
    public Thief(Tile position) {
        super(position);
    }

    public void interact() {
        for (Object o : position.getObjectsOnTile()) {
            if (o instanceof Loot) {
                position.removeObjectFromTile(o);
            } else-if (o instanceof Lever) {

                position.removeObjectFromTile(o);
            }

        }
    }
}

