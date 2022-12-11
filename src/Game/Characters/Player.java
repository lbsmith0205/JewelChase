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
public class Player extends Thief {
    private int score = 0;
    private String pathToImage = "Sprites/Characters/Player/Player_" + direction.name() + ".png";
    /**
     * Creates an instance of Character.
     *
     * @param position The Tile on which the Character is located.
     */
    public Player(Tile position, Direction direction) {
        super(position, direction);
        refreshImage();
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
            refreshImage();
            position.addObjectToTile(this);
        }
    }

    @Override
    protected void refreshImage() {
        pathToImage = "Sprites/Characters/Player/Player_" + direction.name() + ".png";
        this.image = new Image(pathToImage);
    }

    public int getScore() {
        return this.score;
    }

    public void addScore(int value) {
        score += value;
    }

}
