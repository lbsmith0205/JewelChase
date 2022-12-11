package Game.Characters;

import Game.Board.Board;
import Game.Board.Tile;
import Game.Direction;
import javafx.scene.image.Image;
import javafx.scene.canvas.GraphicsContext;


import java.awt.event.KeyEvent;

/**
 * Player.java
 * Sub class of Character, represents to player character.
 * @author Daniel Baxter, Jack Lewis.
 */

public class Player extends Character {

    private String PLAYER_PATH = "Sprites/Characters/Player/Player_" + direction.name() + ".png";

    /**
     * Creates an instance of Character.
     *
     * @param position The Tile on which the Character is located.
     */
    public Player(Tile position, Direction direction) {
        super(position, direction);
        this.image = new Image(PLAYER_PATH);
    }


    // will eventually relate this tile positions
    public void processKeyEvent(KeyEvent event, Board board) {
        switch (event.getKeyCode()) {
            case KeyEvent.VK_W, KeyEvent.VK_UP -> direction = Direction.UP;
            case KeyEvent.VK_A, KeyEvent.VK_LEFT -> direction = Direction.LEFT;
            case KeyEvent.VK_S, KeyEvent.VK_DOWN -> direction = Direction.DOWN;
            case KeyEvent.VK_D, KeyEvent.VK_RIGHT -> direction = Direction.RIGHT;
        }
        move(board);
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    /**
     * Moves the player character to the desired Tile.
     * @param board
     */
    @Override
    public void move(Board board) {
        Tile destination = board.findAccessibleTile(direction, this.position, 1);
        if (destination != null) {
            position.removeObjectFromTile(this);
            this.position = destination;
            position.addObjectToTile(this);
        }
    }


}
