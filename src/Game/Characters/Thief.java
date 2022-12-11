package Game.Characters;

import Game.Board.Board;
import Game.Board.Tile;
import Game.Direction;
import Game.Items.Bomb;
import Game.Items.Loot;

public abstract class Thief extends Character {
    /**
     * Creates an instance of Thief.
     *
     * @param position The Tile on which the Character is located.
     */
    public Thief(Tile position, Direction direction) {
        super(position, direction);
    }

    public void steal(Loot loot) {
        if (this instanceof Player) {
            ((Player) this).addScore(loot.getValue());
        }
        this.position.removeObjectFromTile(loot);
    }

    public void activateBomb(Board board, int timer) {
        int XPosition = this.getPosition().getXPosition();
        int YPosition = this.getPosition().getYPosition();
        for (Bomb bomb : board.getAllBombs()) {
            int xDistance = Math.abs(XPosition - bomb.getPosition().getXPosition());
            int yDistance = Math.abs(YPosition - bomb.getPosition().getYPosition());
            if (xDistance <= 1 || yDistance <= 1) {
                bomb.activate(timer);
            }
        }
    }


}

