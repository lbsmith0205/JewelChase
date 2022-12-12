package Game.Characters;
import Game.Board.Board;
import Game.Board.Level;
import Game.Board.Tile;
import Game.Direction;
import Game.Items.Bomb;
import Game.Items.Loot;

/**
 * A Thief is a Character class that can pick up Loot.
 *
 * @author Daniel Baxter, Luke Smith.
 */
public abstract class Thief extends Character {
    /**
     * Creates an instance of Thief.
     *
     * @param position The Tile on which the Character is located.
     */
    public Thief(Tile position, Direction direction) {
        super(position, direction);
    }

    /**
     * Pick up the Loot.
     * Add to score if it's a Player, remove the Loot if it's a Smart Thief/Floor Following Thief.
     *
     * @param loot the Loot being picked up.
     */
    public void steal(Level level, Board board, Loot loot) {
        if (this instanceof Player) {
            ((Player) this).addScore(loot.getValue());
        }
        loot.pullLever(board);
        level.adjustTime(loot.adjustTime(this));
        this.position.removeObjectFromTile(loot);
    }

    /**
     * Activates the Bomb if a Thief is within the activation area.
     *
     * @param board Board the Thief/Bomb is in.
     * @param time The time remaining in the level.
     */
    public void activateBomb(Board board, int time) {
        int XPosition = this.getPosition().getXPosition();
        int YPosition = this.getPosition().getYPosition();
        for (Bomb bomb : board.getAllBombs()) {
            int xDistance = Math.abs(XPosition - bomb.getPosition().getXPosition());
            int yDistance = Math.abs(YPosition - bomb.getPosition().getYPosition());
            if (xDistance <= 1 || yDistance <= 1) {
                bomb.activate(time);
            }
        }
    }


}

