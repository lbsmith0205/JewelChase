package Game.Characters;

import Game.Board.Board;
import Game.Board.Tile;
import Game.Direction;


import java.awt.event.KeyEvent;

/**
 * Player.java
 * Sub class of Character, represents to player character.
 * @author Daniel Baxter, Jack Lewis.
 */

public class Player extends Character {
    /**
     * Creates an instance of Character.
     *
     * @param position The Tile on which the Character is located.
     */
    public Player(Tile position, Direction direction) {
        super(position, direction);
    }

    // will eventually relate this tile positions
    public void processKeyEvent(KeyEvent event, Board board) {
        switch (event.getKeyCode()) {
            case KeyEvent.VK_W, KeyEvent.VK_UP -> move(board, Direction.UP);
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> move(board, Direction.LEFT);
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> move(board, Direction.DOWN);
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> move(board, Direction.RIGHT);
        }

    }

    /**
     * Returns the next accessible tile in a specified direction, returns null if none available.
     * @param board
     * @param d
     * @param source
     * @return
     */
    private Tile findAccessibleTile(Board board, Direction d, Tile source) {
        try {
            int x = source.getXPosition();
            int y = source.getYPosition();
            switch (d) {
                case UP -> y--;
                case LEFT -> x--;
                case DOWN -> y++;
                case RIGHT -> x++;
            }

            Tile target = board.getTile(x, y);
            if (board.isLegalMove(source, target)) {
                return target;
            } else {
                return findAccessibleTile(board, d, target);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    /**
     * Moves the player character to the desired Tile.
     * @param board
     * @param d
     */
    public void move(Board board, Direction d) {
        Tile destination = findAccessibleTile(board, d, this.position);
        if (destination != null) {
            position.removeObjectFromTile(this);
            this.position = destination;
            position.addObjectToTile(this);
            this.direction = d;
        }
    }
}
